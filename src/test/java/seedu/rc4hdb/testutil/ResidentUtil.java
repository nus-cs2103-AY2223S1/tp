package seedu.rc4hdb.testutil;

import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.rc4hdb.logic.commands.modelcommands.AddCommand;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentDescriptor;
import seedu.rc4hdb.model.person.Person;
import seedu.rc4hdb.model.tag.Tag;

/**
 * A utility class for Resident.
 * //work in progress
 */
public class ResidentUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Resident resident) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(resident);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Resident resident) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditResidentDescriptorDetails(ResidentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getRoom().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
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
