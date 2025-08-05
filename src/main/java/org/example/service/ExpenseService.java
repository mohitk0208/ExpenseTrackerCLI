package org.example.service;

import org.example.models.Expense;

import java.util.List;

public interface ExpenseService {
    Expense addExpense(String description, String category, Float amount);
    Expense getExpenseById(String id);
    List<Expense> getAllExpenses();
    List<Expense> getAllExpenseByCategory(String category);
    Expense updateExpense(String id, String description, String category, Float amount);
    Void deleteExpenseById(String id);
    String getExpenseSummary();
    String getExpenseSummaryByMonth(Integer month);
}
