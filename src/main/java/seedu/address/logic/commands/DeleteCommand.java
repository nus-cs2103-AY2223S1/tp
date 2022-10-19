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
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a patient identified by the index number used in the displayed patient list,"
            + "or a range of patients to be deleted in the patient list.\n"
            + "Parameters: INDEX [endIndex] (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1"
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
        if (endIndex.getZeroBased() > lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        int start = targetIndex.getZeroBased();
        int end = endIndex.getZeroBased();
        List<Person> personsToDelete = new ArrayList<>(lastShownList.subList(start, end + 1));
        for (Person personToDelete: personsToDelete) {
            model.deletePerson(personToDelete);
            model.deleteAppointments(personToDelete.getAppointments());
        }
        return new CommandResult(Messages.MESSAGE_DELETE_PERSONS_SUCCESS);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
