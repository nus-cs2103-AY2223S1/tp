package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMEZONE;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.SetCommand.SetPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.person.contact.ContactType;
import seedu.address.model.tag.Tag;

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
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        sb.append(PREFIX_ROLE + person.getRole().role + " ");
        return sb.toString();
    }

    /**
     * Returns a CLI input that will cause the SetCommandParser to make a given descriptor.
     */
    public static String getSetPersonDescriptorDetails(SetPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getRole().ifPresent(role -> sb.append(PREFIX_ROLE).append(role.role).append(" "));
        descriptor.getTimezone().ifPresent(timezone -> sb.append(PREFIX_TIMEZONE)
                                                         .append(timezone.timezone)
                                                         .append(" "));

        for (ContactType key : descriptor.getContacts().keySet()) {
            switch (key) {
            case TELEGRAM:
                sb.append(PREFIX_TELEGRAM);
                break;
            case EMAIL:
                sb.append(PREFIX_EMAIL);
                break;
            case PHONE:
                sb.append(PREFIX_PHONE);
                break;
            case SLACK:
                sb.append(PREFIX_SLACK);
                break;
            default:
                // do nothing
            }
            sb.append(descriptor.getContacts().get(key).getValue()).append(" ");
        }

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
