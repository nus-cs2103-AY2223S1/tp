package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Lists all members of the current team.
 */
@CommandLine.Command(name = "members")
public class ListMembersCommand extends Command {
    public static final String COMMAND_WORD = "list members";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all the members of the current team.\n"
            + "Example: " + COMMAND_WORD;

    public ListMembersCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredMembersList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof ListMembersCommand); // instanceof handles nulls
    }

}
