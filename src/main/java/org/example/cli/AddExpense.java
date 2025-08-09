package org.example.cli;

import org.example.ExpenseTrackerCLI;
import org.example.service.ExpenseService;
import picocli.CommandLine;

@CommandLine.Command(
        name = "add",
        description = "Add expense"
)
public class AddExpense implements Runnable {

    @CommandLine.ParentCommand
    private ExpenseTrackerCLI parent;

    @CommandLine.Option(names = {"--description"}, description = "Description of the expense to add.", required = true)
    private String description;

    @CommandLine.Option(names = {"--category"}, description = "Category of the expense", required = true)
    private String category;


    @CommandLine.Option(names = {"--amount"}, description = "Amount of the occurred expense", required = true)
    private Float amount;

    @Override
    public void run() {
        parent.expenseService.addExpense(description, category, amount);
    }
}
