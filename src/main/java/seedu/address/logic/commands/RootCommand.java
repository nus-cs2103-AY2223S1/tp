package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_WITH_HELP_COMMAND;

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
    TasksOfCommand.class,
    ClearCommand.class,
    ExitCommand.class,
    FindCommand.class,
    SortCommand.class,
    MarkCommand.class,
    UnmarkCommand.class,
    HelpCommand.class,
    ThemeCommand.class
})
public class RootCommand implements Runnable {
    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    @Override
    public void run() {
        throw new CommandLine.ParameterException(commandSpec.commandLine(), MESSAGE_INVALID_COMMAND_WITH_HELP_COMMAND);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RootCommand); // instanceof handles nulls
    }
}
