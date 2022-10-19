package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;

/**
 * A class that encapsulates the functionality of cancelling a patient's appointment.
 */
public class CancelCommand extends SelectAppointmentCommand {
    public static final String COMMAND_WORD = "cancel";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Cancels an appointment in the appointment list."
            + "Parameters: APPOINTMENT_INDEX (must be a valid appointment index and positive integer)\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_CANCEL_APPOINTMENT_SUCCESS = "Cancelled appointment %1$s for: ";


    /**
     * Creates a cancel command that specifies the patient and appointment index.
     * @param apptIndex The index of the appointment we want to cancel for in the appointment list.
     */
    public CancelCommand(Index apptIndex) {
        super(apptIndex);
    }

    /**
     * Removes the appointment in the specified index for the specified patient.
     * @param model {@code Model} which the command should operate on.
     * @return Feedback to the user in the form of a success message.
     * @throws CommandException If the input index is out of valid range.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person patientToCancelAppt = getTargetPerson(model);
        Appointment toBeCancelledAppt = getTargetAppointment(model);

        int index = patientToCancelAppt.getAppointments().indexOf(toBeCancelledAppt) + 1;
        cancelAppointment(patientToCancelAppt.getAppointments(), toBeCancelledAppt);
        model.deleteAppointment(toBeCancelledAppt);

        return new CommandResult(String.format(MESSAGE_CANCEL_APPOINTMENT_SUCCESS, index)
                + patientToCancelAppt.getName());
    }

    private void cancelAppointment(List<Appointment> appointmentList, Appointment appointment) {
        appointmentList.remove(appointment);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CancelCommand)) {
            return false;
        }

        CancelCommand otherCommand = (CancelCommand) other;
        return hasSameIndexOfAppointment(otherCommand);
    }
}
