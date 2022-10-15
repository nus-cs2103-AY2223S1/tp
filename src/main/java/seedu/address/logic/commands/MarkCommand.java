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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the appointment of the person identified "
            + "by the index numbers used in the displayed person and their corresponding appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX_OF_PERSON (must be a positive integer) "
            + "INDEX_OF_APPOINTMENT (must be a positive integer)";

    public static final String MESSAGE_MARK_PERSON_SUCCESS = "Marked appointment %1$s for Person: %2$s";
    public static final String MESSAGE_ALREADY_MARKED = "This appointment is already marked.";

    /**
     * Creates a mark command containing the index of a person and the index of an appointment.
     *
     * @param indexOfPerson Index of the person in the filtered person list to mark.
     * @param indexOfAppointment Index of the appointment of the specified person to mark.
     */
    public MarkCommand(Index indexOfPerson, Index indexOfAppointment) {
        super(indexOfPerson, indexOfAppointment);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Person person = getTargetPerson(model);
        Appointment appointmentToMark = getTargetAppointment(model);
        int size = model.getFilteredAppointmentList().size();

        if (appointmentToMark.isMarked()) {
            throw new CommandException(MESSAGE_ALREADY_MARKED);
        }

        appointmentToMark.mark();
        addRecurringAppointment(model, person, appointmentToMark);
        String addRecurringSuccessMsg = "";
        if (size < model.getFilteredAppointmentList().size()) {
            addRecurringSuccessMsg += "\nA recurring appointment has been automatically added";
        }
        String markSuccessMsg = String.format(MESSAGE_MARK_PERSON_SUCCESS,
                indexOfAppointment.getOneBased(),
                getTargetPerson(model).getName());
        return new CommandResult(markSuccessMsg + addRecurringSuccessMsg);
    }

    private void addRecurringAppointment(Model model, Person person, Appointment appointment) {
        Appointment recurringAppointment = new Appointment(appointment);
        List<Appointment> appointmentList = person.getAppointments();
        if (!hasSameAppointment(appointmentList, recurringAppointment)) {
            appointmentList.add(recurringAppointment);
            model.addAppointment(recurringAppointment);
        }
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
        return hasSameIndexOfPerson(otherCommand) && hasSameIndexOfAppointment(otherCommand);
    }
}
