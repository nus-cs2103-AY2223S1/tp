package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;

/**
 * Unmarks an appointment for the given patient as incomplete.
 */
public class UnmarkCommand extends SelectAppointmentCommand {
    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Unmarks an appointment in the appointment list as uncompleted\n"
                    + "Parameters: APPOINTMENT_INDEX (must be a valid appointment index and a positive integer)\n"
                    + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_PERSON_SUCCESS = "Unmarked appointment %1$s.";
    public static final String MESSAGE_ALREADY_UNMARKED = "This appointment is already unmarked.";

    /**
     * Creates an unmark command containing the index of an appointment.
     *
     * @param indexOfAppointment Index of the appointment in the appointmentlist to unmark.
     */
    public UnmarkCommand(Index indexOfAppointment) {
        super(indexOfAppointment);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Person person = getTargetPerson(model);
        Appointment appointmentToUnmark = getTargetAppointment(model);

        if (!appointmentToUnmark.isMarked()) {
            throw new CommandException(MESSAGE_ALREADY_UNMARKED);
        }

        appointmentToUnmark.unmark();
        return new CommandResult(String.format(MESSAGE_UNMARK_PERSON_SUCCESS,
                indexOfAppointment.getOneBased()));
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
        return hasSameIndexOfAppointment(otherCommand);
    }
}
