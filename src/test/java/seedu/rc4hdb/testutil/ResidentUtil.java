package seedu.rc4hdb.testutil;

import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_HOUSE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_MATRIC_NUMBER;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.rc4hdb.logic.commands.residentcommands.AddCommand;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentDescriptor;
import seedu.rc4hdb.model.resident.ResidentStringDescriptor;
import seedu.rc4hdb.model.resident.fields.Tag;

/**
 * A utility class for Resident.
 */
public class ResidentUtil {

    /**
     * Returns an add command string for adding the {@code resident}.
     */
    public static String getAddCommand(Resident resident) {
        return AddCommand.COMMAND_WORD + " " + getResidentDetails(resident);
    }

    /**
     * Returns the part of command string for the given {@code resident}'s details.
     */
    public static String getResidentDetails(Resident resident) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + resident.getName().value).append(" ");
        sb.append(PREFIX_PHONE + resident.getPhone().value).append(" ");
        sb.append(PREFIX_EMAIL + resident.getEmail().value).append(" ");
        sb.append(PREFIX_ROOM + resident.getRoom().value).append(" ");
        sb.append(PREFIX_GENDER + resident.getGender().value).append(" ");
        sb.append(PREFIX_HOUSE + resident.getHouse().value).append(" ");
        sb.append(PREFIX_MATRIC_NUMBER + resident.getMatricNumber().value).append(" ");
        resident.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.value + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code ResidentDescriptor}'s details.
     */
    public static String getResidentDescriptorDetails(ResidentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.value).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getRoom().ifPresent(room -> sb.append(PREFIX_ROOM).append(room.value).append(" "));
        descriptor.getGender().ifPresent(gender -> sb.append(PREFIX_GENDER).append(gender.value).append(" "));
        descriptor.getHouse().ifPresent(house -> sb.append(PREFIX_HOUSE).append(house.value).append(" "));
        descriptor.getMatricNumber().ifPresent(matricNumber ->
                sb.append(PREFIX_MATRIC_NUMBER).append(matricNumber.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.value).append(" "));
            }
        }
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code ResidentStringDescriptor}'s details.
     */
    public static String getResidentDescriptorDetails(ResidentStringDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email).append(" "));
        descriptor.getRoom().ifPresent(room -> sb.append(PREFIX_ROOM).append(room).append(" "));
        descriptor.getGender().ifPresent(gender -> sb.append(PREFIX_GENDER).append(gender).append(" "));
        descriptor.getHouse().ifPresent(house -> sb.append(PREFIX_HOUSE).append(house).append(" "));
        descriptor.getMatricNumber().ifPresent(matricNumber ->
                sb.append(PREFIX_MATRIC_NUMBER).append(matricNumber).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<String> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s).append(" "));
            }
        }
        return sb.toString();
    }
}
