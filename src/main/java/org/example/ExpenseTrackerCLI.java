package org.example;

import org.example.cli.*;
import org.example.repository.ExpenseRepository;
import org.example.repository.JsonExpenseRepository;
import org.example.service.ExpenseService;
import org.example.service.ExpenseServiceImpl;
import picocli.CommandLine;

@CommandLine.Command(
        name = "expense-tracker",
        description = "CLI application for tracking expenses.",
        mixinStandardHelpOptions = true,
        version = "Expense Tracker CLI 1.0",
        subcommands = {AddExpense.class, ListExpenses.class, UpdateExpense.class, DeleteExpense.class, Summary.class}
)
public class ExpenseTrackerCLI implements Runnable {

    public ExpenseService expenseService;

    public ExpenseTrackerCLI() {
        ExpenseRepository expenseRepository = new JsonExpenseRepository("data/expenses.json");
        this.expenseService = new ExpenseServiceImpl(expenseRepository);
    }

    public static void main(String[] args) {
        CommandLine cmd = new CommandLine(new ExpenseTrackerCLI());
        int exitCode = cmd.execute(args);

        System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.println("Please use the subcommands to interact with the application.");
    }
}
