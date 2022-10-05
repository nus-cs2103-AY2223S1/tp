package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FLOOR_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOSPITAL_WING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WARD_NUMBER;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Medication;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_NEXT_OF_KIN + person.getNextOfKin().value + " ");
        sb.append(PREFIX_PATIENT_TYPE + person.getPatientType().value.name() + " ");
        if (person.getPatientType().isInpatient()) {
            sb.append(PREFIX_HOSPITAL_WING + person.getHospitalWing().get().value + " ");
            sb.append(PREFIX_HOSPITAL_WING + person.getFloorNumber().get().value.toString() + " ");
            sb.append(PREFIX_HOSPITAL_WING + person.getWardNumber().get().value.toString() + " ");
        }
        person.getMedications().stream().forEach(
            s -> sb.append(PREFIX_MEDICATION + s.medicationName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getNextOfKin()
                .ifPresent(nextOfKin -> sb.append(PREFIX_NEXT_OF_KIN).append(nextOfKin.value).append(" "));
        descriptor.getPatientType()
                .ifPresent(patientType -> sb.append(PREFIX_PATIENT_TYPE).append(patientType.value.name()).append(" "));
        descriptor.getHospitalWing().ifPresent(hospitalWing -> sb.append(PREFIX_HOSPITAL_WING)
                .append(hospitalWing.value).append(" "));
        descriptor.getFloorNumber().ifPresent(floorNumber -> sb.append(PREFIX_FLOOR_NUMBER)
                .append(floorNumber.value).append(" "));
        descriptor.getWardNumber().ifPresent(wardNumber -> sb.append(PREFIX_WARD_NUMBER)
                .append(wardNumber.value).append(" "));
        if (descriptor.getMedications().isPresent()) {
            Set<Medication> tags = descriptor.getMedications().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_MEDICATION);
            } else {
                tags.forEach(s -> sb.append(PREFIX_MEDICATION).append(s.medicationName).append(" "));
            }
        }
        return sb.toString();
    }
}
