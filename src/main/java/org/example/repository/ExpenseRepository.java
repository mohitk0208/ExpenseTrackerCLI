package org.example.repository;

import org.example.models.Expense;

import java.util.List;

public interface ExpenseRepository {

    Expense save(Expense expense);
    Expense getExpenseById(String id);
    List<Expense> getAllExpenses();
    List<Expense> getAllExpensesByCategory(String category);
    Void deleteExpense(String id);
    List<String> getAllExpenseCategory();
}
