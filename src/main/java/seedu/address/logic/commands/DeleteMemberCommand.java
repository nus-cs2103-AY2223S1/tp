package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a member identified using it's displayed index from the member list.
 */
public class DeleteMemberCommand extends Command {

    public static final String COMMAND_WORD = "delete_member";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the members list.\n"
            + ": Run `list_members` to see members in your current team.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Member: %1$s";

    private final Index targetIndex;

    public DeleteMemberCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> teamMembers = model.getTeam().getTeamMembers();

        if (targetIndex.getZeroBased() >= teamMembers.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = teamMembers.get(targetIndex.getZeroBased());
        model.getTeam().removeMember(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteMemberCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteMemberCommand) other).targetIndex)); // state check
    }
}
