package seedu.address.logic.commands;

import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * Main command used to set up subcommands being used in TruthTable.
 */
@Command(name = "", subcommands = {AddCommand.class})
public class MainCommand implements Runnable {
    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

    public void run() {
        throw new CommandLine.ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
