package org.example.cli;

import org.example.ExpenseTrackerCLI;
import picocli.CommandLine;

@CommandLine.Command(
        name = "export",
        description = "Export the data of expenses."
)
public class ExportExpenses implements Runnable {

    @CommandLine.ParentCommand
    private ExpenseTrackerCLI parent;

    @CommandLine.Option(names = {"--filename"}, description = "Filename of the file in which the data is exported")
    private String filename;

    @CommandLine.Option(names = {"--type"}, description = "type of file to export data to.")
    private String type;

    @Override
    public void run() {
        parent.expenseService.exportToCSV(filename);
        System.out.println("Successfully exported the expenses data.");
    }
}
