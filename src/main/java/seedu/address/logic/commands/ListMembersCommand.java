package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Lists all members of the current team.
 */
@CommandLine.Command(name = ListMembersCommand.COMMAND_WORD,
        aliases = {ListMembersCommand.ALIAS}, mixinStandardHelpOptions = true)
public class ListMembersCommand extends Command {
    public static final String COMMAND_WORD = "members";
    public static final String ALIAS = "m";
    public static final String FULL_COMMAND = ListCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = FULL_COMMAND
            + ": Lists all the members of the current team.\n"
            + "Example: " + FULL_COMMAND;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    public ListMembersCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);

        model.updateFilteredMembersList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredMemberList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof ListMembersCommand); // instanceof handles nulls
    }

}
