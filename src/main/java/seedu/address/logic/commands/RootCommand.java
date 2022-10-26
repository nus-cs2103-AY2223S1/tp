package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * An empty command which instantiates all possible first-level commands.
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
    HelpCommand.class,
    ThemeCommand.class
})
public class RootCommand implements Runnable {
    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

    /**
     * Throws a {@code ParameterException} when called, which should only occur when no subcommands are found.
     */
    public void run() {
        throw new CommandLine.ParameterException(spec.commandLine(), MESSAGE_UNKNOWN_COMMAND);
    }
}
