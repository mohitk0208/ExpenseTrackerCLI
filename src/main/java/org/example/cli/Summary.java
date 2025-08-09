package org.example.cli;

import org.example.ExpenseTrackerCLI;
import picocli.CommandLine;

@CommandLine.Command(
        name = "summary",
        description = "Give the summary of all the expenses"
)
public class Summary implements Runnable {

    @CommandLine.ParentCommand
    private ExpenseTrackerCLI parent;

    @CommandLine.Option(names = "--month", description = "Summary of the expenses by expense create month.")
    private Integer month;

    @CommandLine.Option(names = "--category", description = "Get expense summary by category")
    private String category;

    @Override
    public void run() {
        Float totalExpense;
        if (month != null) {
            totalExpense = parent.expenseService.getTotalExpense(month);
        } else if (category != null) {
            totalExpense = parent.expenseService.getTotalExpense(category);
        } else {
            totalExpense = parent.expenseService.getTotalExpense();
        }

        System.out.printf("Total Expenses: %f", totalExpense);
    }
}
