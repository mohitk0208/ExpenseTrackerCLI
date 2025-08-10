package org.example.service;

import org.example.models.Expense;
import org.example.repository.ExpenseRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Expense addExpense(String description, String category, Float amount) {
        Expense expense = new Expense(
                UUID.randomUUID().toString(),
                description,
                amount,
                category,
                new Date().getTime(),
                new Date().getTime()
        );

        return expenseRepository.save(expense);
    }

    @Override
    public Expense getExpenseById(String id) {
        Optional<Expense> expenseOptional = expenseRepository.getExpenseById(id);

        if (expenseOptional.isPresent()) {
            return expenseOptional.get();
        }

        throw new IllegalArgumentException(String.format("Could not find Expense with id: (%s)%n", id));
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.getAllExpenses();
    }

    @Override
    public List<Expense> getAllExpenseByCategory(String category) {
        return expenseRepository.getAllExpensesByCategory(category);
    }

    @Override
    public Expense updateExpense(String id, String description, String category, Float amount) {
        Optional<Expense> expenseOptional = expenseRepository.getExpenseById(id);

        if (expenseOptional.isPresent()) {
            Expense expense = expenseOptional.get();
            if (description != null) {
                expense.setDescription(description);
            }
            if (category != null) {
                expense.setCategory(category);
            }
            if (amount != null) {
                expense.setAmount(amount);
            }

            expense.setUpdatedAt(new Date().getTime());

            return expenseRepository.save(expense);
        }

        throw new IllegalArgumentException(String.format("Could not find Expense with id: (%s)%n", id));
    }

    @Override
    public void deleteExpenseById(String id) {
        expenseRepository.deleteExpense(id);
    }

    @Override
    public Float getTotalExpense() {
        return getAllExpenses().stream()
                .map(Expense::getAmount)
                .reduce(0f, Float::sum);
    }

    @Override
    public Float getTotalExpense(String category) {
        return getAllExpenseByCategory(category).stream()
                .map(Expense::getAmount)
                .reduce(0f, Float::sum);
    }

    @Override
    public Float getTotalExpense(int month) {
        return getAllExpenses().stream()
                .filter(expense -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(expense.getCreatedAt());

                    return calendar.get(Calendar.MONTH) + 1 == month;
                })
                .map(Expense::getAmount)
                .reduce(0f, Float::sum);
    }

    @Override
    public void exportToCSV(String filename) {
        if (filename == null || filename.isBlank()) {
            filename = "expenses.csv";
        } else {
            filename = filename + ".csv";
        }

        List<Expense> expenses = getAllExpenses();

        try(FileWriter writer = new FileWriter(filename)){
            // write header
            writer.append("Id,Description,Amount,Category,CreatedAt,UpdatedAt\n");

            //write rows
            for (Expense expense:expenses) {
                writer.append(expense.getId()).append(",");
                writer.append(expense.getDescription()).append(",");
                writer.append(expense.getCategory()).append(",");
                writer.append(expense.getAmount().toString()).append(",");
                writer.append(expense.getCreatedAt().toString()).append(",");
                writer.append(expense.getUpdatedAt().toString()).append("\n");
            }

        } catch (IOException e) {
            System.out.println("Failed to export expenses data to CSV.");
            throw new RuntimeException(e);
        }
    }

}
