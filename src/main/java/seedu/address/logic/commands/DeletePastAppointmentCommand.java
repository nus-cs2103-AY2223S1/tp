package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a patient's most recent past appointment.
 */
public class DeletePastAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "delappt";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the patient's most recent past appointment.\n"
            + "Parameters: INDEX (Must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";
    public static final String MESSAGE_SUCCESS = "Past appointment deleted for %1$s.\n";
    public static final String INVALID_DELETE_MESSAGE = "No past appointments to delete.";
    private final Index index;

    /**
     * Creates a command to deletes past appointments from a patient's record.
     * @param index of the patient in the filtered patient list to edit
     */
    public DeletePastAppointmentCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person patient = lastShownList.get(index.getZeroBased());
        if (patient.getPastAppointmentCount() <= 0) {
            throw new CommandException(INVALID_DELETE_MESSAGE);
        }
        patient.deleteMostRecentPastAppointment();
        return new CommandResult(String.format(MESSAGE_SUCCESS, patient.getName()));
    }
}
