package org.example.service;

import org.example.models.Expense;
import org.example.repository.ExpenseRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public String getExpenseSummary() {
        return "";
    }

    @Override
    public String getExpenseSummaryByMonth(Integer month) {
        return "";
    }
}
