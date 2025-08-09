package org.example.cli;

import org.example.ExpenseTrackerCLI;
import org.example.models.Expense;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(
        name = "list",
        description = "List all the expenses"
)
public class ListExpenses implements Runnable {

    @CommandLine.ParentCommand
    private ExpenseTrackerCLI parent;

    @CommandLine.Option(names = "--category", description = "Category by which to filter the expenses list")
    private String category;

    @Override
    public void run() {
        List<Expense> expenses;
        if (category != null) {
            expenses = parent.expenseService.getAllExpenseByCategory(category);
        } else {
            expenses = parent.expenseService.getAllExpenses();
        }

        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }
}
