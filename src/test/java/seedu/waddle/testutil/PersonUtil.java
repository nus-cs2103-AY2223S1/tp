package seedu.waddle.testutil;

import static seedu.waddle.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.waddle.logic.commands.AddCommand;
import seedu.waddle.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Itinerary itinerary) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(itinerary);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Itinerary itinerary) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + itinerary.getName().fullName + " ");
        sb.append(PREFIX_PHONE + itinerary.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + itinerary.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + itinerary.getAddress().value + " ");
        itinerary.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
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
