package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;

/**
 * Unmarks an appointment for the given patient as incomplete.
 */
public class UnmarkCommand extends SelectAppointmentCommand {
    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unmarks the appointment of the person identified "
            + "by the index numbers used in the displayed person and their corresponding appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX_OF_PERSON (must be a positive integer) "
            + "INDEX_OF_APPOINTMENT (must be a positive integer)";

    public static final String MESSAGE_UNMARK_PERSON_SUCCESS = "Unmarked appointment %1$s for Person: %2$s";
    public static final String MESSAGE_ALREADY_UNMARKED = "This appointment is already unmarked.";

    /**
     * Creates an unmark command containing the index of a person and the index of an appointment.
     *
     * @param indexOfPerson Index of the person in the filtered person list to unmark.
     * @param indexOfAppointment Index of the appointment of the specified person to unmark.
     */
    public UnmarkCommand(Index indexOfPerson, Index indexOfAppointment) {
        super(indexOfPerson, indexOfAppointment);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Appointment appointmentToUnmark = getTargetAppointment(model);

        if (!appointmentToUnmark.isMarked()) {
            throw new CommandException(MESSAGE_ALREADY_UNMARKED);
        }

        appointmentToUnmark.unmark();
        return new CommandResult(String.format(MESSAGE_UNMARK_PERSON_SUCCESS,
                indexOfAppointment.getOneBased(),
                getTargetPerson(model).getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UnmarkCommand)) {
            return false;
        }

        UnmarkCommand otherCommand = (UnmarkCommand) other;
        return hasSameIndexOfPerson(otherCommand) && hasSameIndexOfAppointment(otherCommand);
    }
}
