package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.models.Expense;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JsonExpenseRepository implements ExpenseRepository {

    private final ObjectMapper mapper;
    private final File databaseFile;

    public JsonExpenseRepository(String filePath) {
        this.databaseFile = new File(filePath);
        this.mapper = new ObjectMapper();

        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            if (!databaseFile.exists()) {
                boolean _ = databaseFile.getParentFile().mkdirs();
                boolean __ = databaseFile.createNewFile();
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Could not create the Database file, filePath: (%s)%n", filePath));
        }
    }

    @Override
    public Expense save(Expense expense) {
        if (expense.getId() != null && !expense.getId().isBlank()) {
            writeExpensesToJsonFile(getAllExpenses().stream()
                    .map(expense1 -> {
                        if (expense1.getId().equals(expense.getId())) {
                            return expense;
                        }
                        return expense;
                    }).toList());
        } else {
            List<Expense> updatedExpenses = new ArrayList<>(getAllExpenses());
            updatedExpenses.add(expense);
            writeExpensesToJsonFile(updatedExpenses);
        }

        return expense;
    }

    @Override
    public Optional<Expense> getExpenseById(String id) {
        return getAllExpenses().stream()
                .filter(expense -> expense.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Expense> getAllExpenses() {
        try {
            return this.mapper.readValue(this.databaseFile, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch expenses.");
        }
    }

    @Override
    public List<Expense> getAllExpensesByCategory(String category) {
        return getAllExpenses().stream()
                .filter(expense -> expense.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    @Override
    public void deleteExpense(String id) {
        Optional<Expense> expenseOptional = getExpenseById(id);

        if (expenseOptional.isPresent()) {
            writeExpensesToJsonFile(
                    getAllExpenses().stream()
                            .filter(expense -> !expense.getId().equals(id))
                            .toList()
            );
        } else {
            throw new IllegalArgumentException(String.format("Could not find Expense with id: (%s)%n", id));
        }
    }

    @Override
    public List<String> getAllExpenseCategory() {
        return getAllExpenses().stream()
                .map(Expense::getCategory)
                .distinct()
                .toList();
    }

    private void writeExpensesToJsonFile(List<Expense> expenses) {
        try {
            this.mapper.writeValue(this.databaseFile, expenses);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
