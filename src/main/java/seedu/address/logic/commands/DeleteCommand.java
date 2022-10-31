package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from idENTify.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a patient identified by the index number used in the displayed patient list\n"
            + "or a range (inclusive) of patients to be deleted in the patient list.\n"
            + "Patient's appointments are automatically removed after the patient is deleted.\n"
            + "Parameters: INDEX [END_INDEX] (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1\n"
            + "Example: " + COMMAND_WORD + " 2 5";

    private final Index targetIndex;
    private Index endIndex = null;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Overloaded constructor to allow deletion of patients in a range.
     * @param startIndex Patient index to start deletion.
     * @param endIndex Index of last patient to be deleted.
     */
    public DeleteCommand(Index startIndex, Index endIndex) {
        this.targetIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (endIndex == null) {
            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deleteAppointments(personToDelete.getAppointments());
            model.deletePerson(personToDelete);
            return new CommandResult(String.format(Messages.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
        }
        int start = targetIndex.getZeroBased();
        int end = endIndex.getZeroBased();
        checkValidIndices(start, end, lastShownList);
        List<Person> personsToDelete = new ArrayList<>(lastShownList.subList(start, end + 1));
        for (Person personToDelete: personsToDelete) {
            model.deletePerson(personToDelete);
            model.deleteAppointments(personToDelete.getAppointments());
        }
        return new CommandResult(Messages.MESSAGE_DELETE_PERSONS_SUCCESS);
    }

    /**
     * Checks whether the given indices are valid (start <= end and within list size)
     * @throws CommandException if the indices given are not valid for deletion in the patient list.
     */
    public void checkValidIndices(int start, int end, List<Person> currList) throws CommandException {
        if (start > end || end > currList.size() - 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
