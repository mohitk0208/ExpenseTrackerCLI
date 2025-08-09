package org.example.service;

import org.example.models.Expense;

import java.util.List;

public interface ExpenseService {
    Expense addExpense(String description, String category, Float amount);
    Expense getExpenseById(String id);
    List<Expense> getAllExpenses();
    List<Expense> getAllExpenseByCategory(String category);
    Expense updateExpense(String id, String description, String category, Float amount);
    void deleteExpenseById(String id);
    Float getTotalExpense();
    Float getTotalExpense(String category);
    Float getTotalExpense(int month);
}
