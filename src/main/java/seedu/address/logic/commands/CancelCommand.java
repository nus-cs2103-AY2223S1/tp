package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;


/**
 * A class that encapsulates the functionality of cancelling a patient's appointment.
 */
public class CancelCommand extends Command {
    public static final String COMMAND_WORD = "cancel";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Cancels an appointment for the patient. "
            + "Parameters: patientIndex (must be a positive integer)\n"
            + "apptIndex (must be a valid appointment index)"
            + "Example: " + COMMAND_WORD + " 3"
            + " 2";

    public static final String MESSAGE_CANCEL_APPOINTMENT_SUCCESS = "Cancelled appointment for: ";
    private final Index patientIndex;
    private final Index apptIndex;

    /**
     * Creates a cancel command that specifies the patient and appointment index.
     * @param patientIndex The index of the patient that we want to cancel the appointment.
     * @param apptIndex The index of the appointment we want to cancel for that particular patient.
     */
    public CancelCommand(Index patientIndex, Index apptIndex) {
        this.patientIndex = patientIndex;
        this.apptIndex = apptIndex;
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
        List<Person> lastShownList = model.getFilteredPersonList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person patientToCancelAppt = lastShownList.get(patientIndex.getZeroBased());

        if (patientToCancelAppt.getAppointments().size() <= apptIndex.getZeroBased()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }
        Appointment cancelledAppt = patientToCancelAppt.cancelAppointment(apptIndex.getZeroBased());
        model.refreshPerson(patientToCancelAppt);

        return new CommandResult(MESSAGE_CANCEL_APPOINTMENT_SUCCESS + patientToCancelAppt.getName());
    }
}
