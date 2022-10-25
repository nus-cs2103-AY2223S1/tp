package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.TeamPredicate;


/**
 * Lists all members of the current team.
 */
public class ListMembersCommand extends Command {
    public static final String COMMAND_WORD = "list_members";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Lists all the members of the current team.\n"
        + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

//        TeamPredicate predicate = new TeamPredicate(model.getTeam());

        model.updateFilteredMembersList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(
            String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredMemberList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ListMembersCommand); // instanceof handles nulls
    }
}
