package org.example.cli;

import org.example.ExpenseTrackerCLI;
import picocli.CommandLine;

@CommandLine.Command(
        name = "delete",
        description = "Delete a expense by id."
)
public class DeleteExpense implements Runnable {

    @CommandLine.ParentCommand
    private ExpenseTrackerCLI parent;

    @CommandLine.Option(names = {"--id"}, description = "Id of the expense to be deleted.", required = true)
    private String id;

    @Override
    public void run() {
        parent.expenseService.deleteExpenseById(id);
        System.out.printf("Expense with id: ({%s}) deleted successfully.%n", id);
    }
}
