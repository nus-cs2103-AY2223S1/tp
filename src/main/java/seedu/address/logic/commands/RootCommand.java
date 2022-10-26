package seedu.address.logic.commands;

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
    HelpCommand.class
})
public class RootCommand {
}
