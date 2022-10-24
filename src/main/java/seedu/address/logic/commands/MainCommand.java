package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * Main command used to set up subcommands being used in TruthTable.
 */
@Command(name = "", subcommands = {
    AddCommand.class,
    EditCommand.class,
    DeleteCommand.class,
    ListCommand.class,
    AssignCommand.class,
    SetCommand.class,
    TasksSummaryCommand.class,
    ClearCommand.class,
    ExitCommand.class,
    FindCommand.class,
    MarkCommand.class,
    UnmarkCommand.class,
    HelpCommand.class
})
public class MainCommand implements Runnable {
    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

    public void run() {
        throw new CommandLine.ParameterException(spec.commandLine(), MESSAGE_UNKNOWN_COMMAND);
    }
}
