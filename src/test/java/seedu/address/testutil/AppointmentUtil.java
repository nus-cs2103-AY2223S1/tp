package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_TEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLOT;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class for Appointment.
 */
public class AppointmentUtil {

    /**
     * Returns an add command string for adding the {@code appointment}.
     */
    public static String getAddCommand(Appointment appointment) {
        return AddAppointmentCommand.COMMAND_WORD + " " + getAppointmentDetails(appointment);
    }

    /**
     * Returns the part of command string for the given {@code appointment}'s details.
     */
    public static String getAppointmentDetails(Appointment appointment) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + appointment.getName().fullName + " ");
        sb.append(PREFIX_DOCTOR + appointment.getDoctor().doctorName + " ");
        sb.append(PREFIX_MEDICAL_TEST + appointment.getMedicalTest().medicalTestName + " ");
        sb.append(PREFIX_SLOT + appointment.getSlot().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditAppointmentDescriptor}'s details.
     */
    public static String getEditAppointmentDescriptorDetails(EditAppointmentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getDoctor().ifPresent(doctor -> sb.append(PREFIX_DOCTOR).append(doctor.doctorName).append(" "));
        descriptor.getSlot().ifPresent(slot -> sb.append(PREFIX_SLOT).append(slot).append(" "));
        descriptor.getTest().ifPresent(test -> sb.append(PREFIX_MEDICAL_TEST).append(test.medicalTestName).append(" "));
        return sb.toString();
    }
}
