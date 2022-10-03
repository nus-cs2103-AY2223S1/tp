package bookface.testutil;

import java.util.Set;

import bookface.logic.commands.add.AddCommand;
import bookface.logic.commands.add.AddUserCommand;
import bookface.logic.commands.EditCommand;
import bookface.logic.parser.CliSyntax;
import bookface.model.person.Person;
import bookface.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + AddUserCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(CliSyntax.PREFIX_NAME).append(person.getName().fullName).append(" ");
        sb.append(CliSyntax.PREFIX_PHONE).append(person.getPhone().value).append(" ");
        sb.append(CliSyntax.PREFIX_EMAIL).append(person.getEmail().value).append(" ");
        person.getTags().forEach(
            s -> sb.append(CliSyntax.PREFIX_TAG).append(s.tagName).append(" ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditCommand.EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(CliSyntax.PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(CliSyntax.PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(CliSyntax.PREFIX_EMAIL).append(email.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(CliSyntax.PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(CliSyntax.PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
