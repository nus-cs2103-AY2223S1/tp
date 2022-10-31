package seedu.address.logic.commands.persons;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends PersonCommand {

    public static final String SUBCOMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = getFullCommand(SUBCOMMAND_WORD)
        + ": Deletes the person identified by the index number used in the displayed person list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + getFullCommand(SUBCOMMAND_WORD) + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (person == null && targetIndex == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        if (person == null) {
            person = model.getFromFilteredPerson(targetIndex);
        }

        model.deletePerson(person);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, person));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DeleteCommand)) {
            return false;
        }
        if (this == other) {
            return true;
        }
        DeleteCommand c = (DeleteCommand) other;
        if (targetIndex == null) {
            if (c.targetIndex != null) {
                return false;
            }
        } else if (!targetIndex.equals(c.targetIndex)) {
            return false;
        }

        if (person == null) {
            if (c.person != null) {
                return false;
            }
        } else if (!person.equals(c.person)) {
            return false;
        }

        return true;
    }
}
