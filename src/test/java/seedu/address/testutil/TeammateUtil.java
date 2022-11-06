package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditTeammateDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.teammate.Teammate;

/**
 * A utility class for Teammate.
 */
public class TeammateUtil {

    /**
     * Returns an add command string for adding the {@code teammate}.
     */
    public static String getAddCommand(Teammate teammate) {
        return AddCommand.COMMAND_WORD + " " + getTeammateDetails(teammate);
    }

    /**
     * Returns the part of command string for the given {@code teammate}'s details.
     */
    public static String getTeammateDetails(Teammate teammate) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + teammate.getName().fullName + " ");
        sb.append(PREFIX_PHONE + teammate.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + teammate.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + teammate.getAddress().value + " ");
        teammate.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditTeammateDescriptor}'s details.
     */
    public static String getEditTeammateDescriptorDetails(EditTeammateDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
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
