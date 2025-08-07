package org.example.repository;

import org.example.models.Expense;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {

    Expense save(Expense expense);
    Optional<Expense> getExpenseById(String id);
    List<Expense> getAllExpenses();
    List<Expense> getAllExpensesByCategory(String category);
    void deleteExpense(String id);
    List<String> getAllExpenseCategory();
}
