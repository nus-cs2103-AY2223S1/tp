package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;

/**
 * Marks an appointment for the given patient as complete.
 */
public class MarkCommand extends SelectAppointmentCommand {
    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Marks an appointment in the appointment list as complete\n"
                    + "Parameters: APPOINTMENT_INDEX (must be a valid appointment index and a positive integer)\n"
                    + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_PERSON_SUCCESS = "Marked appointment %1$s.";
    public static final String MESSAGE_ALREADY_MARKED = "This appointment is already marked.";

    /**
     * Creates a mark command containing the index of an appointment.
     *
     * @param indexOfAppointment Index of the appointment in the appointment list to mark.
     */
    public MarkCommand(Index indexOfAppointment) {
        super(indexOfAppointment);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Appointment appointmentToMark = getTargetAppointment(model);
        Person person = getTargetPerson(model);

        if (appointmentToMark.isMarked()) {
            throw new CommandException(MESSAGE_ALREADY_MARKED);
        }
        appointmentToMark.mark();
        String addRecurringSuccessMsg = addRecurringAppointment(model, person, appointmentToMark);
        String markSuccessMsg = String.format(MESSAGE_MARK_PERSON_SUCCESS,
                indexOfAppointment.getOneBased());
        return new CommandResult(markSuccessMsg + addRecurringSuccessMsg);
    }

    private String addRecurringAppointment(Model model, Person person, Appointment appointment) {
        if (appointment.getTimePeriod().stream().allMatch(x -> x.equals(0))) {
            return "";
        }

        String str = "\nNo recurring appointment has been added due to time clashes";
        Appointment recurringAppointment = new Appointment(appointment);
        List<Appointment> appointmentList = person.getAppointments();
        if (!hasSameAppointment(appointmentList, recurringAppointment)) {
            appointmentList.add(recurringAppointment);
            model.addAppointment(recurringAppointment);
            str = "\nA recurring appointment has been automatically added";
        }
        return str;
    }

    private boolean hasSameAppointment(List<Appointment> appointments, Appointment appointment) {
        return appointments.stream().anyMatch(x -> x.isSameTime(appointment));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MarkCommand)) {
            return false;
        }

        MarkCommand otherCommand = (MarkCommand) other;
        return hasSameIndexOfAppointment(otherCommand);
    }
}
