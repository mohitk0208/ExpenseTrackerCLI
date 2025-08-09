package org.example.cli;


import org.example.ExpenseTrackerCLI;
import picocli.CommandLine;


@CommandLine.Command(
        name = "update",
        description = "update the details of a expense"
)
public class UpdateExpense implements Runnable {

    @CommandLine.ParentCommand
    private ExpenseTrackerCLI parent;

    @CommandLine.Option(names = "--id", description = "Id of the expense to update", required = true)
    private String id;

    @CommandLine.Option(names = "--description", description = "Update description of the expense ")
    private String description;

    @CommandLine.Option(names = "--category", description = "Updated category of the expense")
    private String category;

    @CommandLine.Option(names = "--amount", description = "Updated amount of the expense ")
    private Float amount;

    @Override
    public void run() {
        parent.expenseService.updateExpense(id, description, category, amount);
        System.out.printf("Successfully updated expense with id: (%s)", id);
    }
}
