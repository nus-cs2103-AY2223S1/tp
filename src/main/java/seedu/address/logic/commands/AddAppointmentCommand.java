package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_TEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLOT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Adds an appointment to the HealthContact.
 */
public class AddAppointmentCommand extends Command {

    public static final CommandWord COMMAND_WORD = new CommandWord("addappointment", "aa");

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an appointment to HealthContact.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_MEDICAL_TEST + "MEDICAL TEST "
            + PREFIX_SLOT + "SLOT<yyyy-MM-dd HH:mm> "
            + PREFIX_DOCTOR + "DOCTOR\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_MEDICAL_TEST + "Computed Tomography "
            + PREFIX_SLOT + "2022-11-12 12:34 "
            + PREFIX_DOCTOR + "Muhammad Wong";
    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the HealthContact";
    public static final String MESSAGE_PATIENT_NOT_EXIST = "This patient does not exist in the HealthContact";
    public static final String MESSAGE_PATIENT_NAME_CASE_UNMATCHED = 
            "The case of the patient's name is wrong, a strict match is required.";

    private final Appointment toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Patient}
     */
    public AddAppointmentCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPatient(toAdd.getName())) {
            throw new CommandException(MESSAGE_PATIENT_NOT_EXIST);
        }

        if (!model.hasPatientWithExactlySameName(toAdd.getName())) {
            throw new CommandException(MESSAGE_PATIENT_NAME_CASE_UNMATCHED);
        }

        if (model.hasAppointment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }



        model.addAppointment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAppointmentCommand // instanceof handles nulls
                && toAdd.equals(((AddAppointmentCommand) other).toAdd));
    }
}
