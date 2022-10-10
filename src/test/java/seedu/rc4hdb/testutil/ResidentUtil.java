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

import seedu.rc4hdb.logic.commands.modelcommands.AddCommand;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentDescriptor;
import seedu.rc4hdb.model.tag.Tag;

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
        sb.append(PREFIX_NAME + resident.getName().fullName).append(" ");
        sb.append(PREFIX_PHONE + resident.getPhone().value).append(" ");
        sb.append(PREFIX_EMAIL + resident.getEmail().value).append(" ");
        sb.append(PREFIX_ROOM + resident.getRoom().room).append(" ");
        sb.append(PREFIX_GENDER + resident.getGender().gender).append(" ");
        sb.append(PREFIX_HOUSE + resident.getHouse().house).append(" ");
        sb.append(PREFIX_MATRIC_NUMBER + resident.getMatricNumber().matricNumber).append(" ");
        resident.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code ResidentDescriptor}'s details.
     */
    public static String getResidentDescriptorDetails(ResidentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getRoom().ifPresent(room -> sb.append(PREFIX_ROOM).append(room.room).append(" "));
        descriptor.getGender().ifPresent(gender -> sb.append(PREFIX_GENDER).append(gender.gender).append(" "));
        descriptor.getHouse().ifPresent(house -> sb.append(PREFIX_HOUSE).append(house.house).append(" "));
        descriptor.getMatricNumber().ifPresent(matricNumber ->
                sb.append(PREFIX_MATRIC_NUMBER).append(matricNumber.matricNumber).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
