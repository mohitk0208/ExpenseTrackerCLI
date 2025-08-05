package org.example;

import picocli.CommandLine;

@CommandLine.Command(
        name = "expense-tracker",
        description = "CLI application for tracking expenses.",
        mixinStandardHelpOptions = true,
        version = "Expense Tracker CLI 1.0",
        subcommands = {}
)
public class ExpenseTrackerCLI implements Runnable{

    public static void main(String[] args) {
        System.out.println("Welcome to Expense Tracker CLI application.");
    }

    @Override
    public void run() {
        System.out.println("Please use the subcommands to interact with the application.");
    }
}
