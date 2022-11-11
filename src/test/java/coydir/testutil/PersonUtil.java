package coydir.testutil;

import static coydir.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static coydir.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static coydir.logic.parser.CliSyntax.PREFIX_EMAIL;
import static coydir.logic.parser.CliSyntax.PREFIX_NAME;
import static coydir.logic.parser.CliSyntax.PREFIX_PHONE;
import static coydir.logic.parser.CliSyntax.PREFIX_POSITION;
import static coydir.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Set;

import coydir.logic.commands.AddCommand;
import coydir.logic.commands.EditCommand.EditPersonDescriptor;
import coydir.model.person.Person;
import coydir.model.tag.Tag;

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
        sb.append(PREFIX_POSITION + person.getPosition().value + " ");
        sb.append(PREFIX_DEPARTMENT + person.getDepartment().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
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
        descriptor.getPosition().ifPresent(position -> sb.append(PREFIX_POSITION).append(position.value).append(" "));
        descriptor.getDepartment().ifPresent(
                department -> sb.append(PREFIX_DEPARTMENT).append(department.value).append(" "));
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

    /**
     * Returns the part of command string for the given keywords for the {@code FindCommand}.
     */
    public static String getFindCommand(List<String> keywords) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("n/%s ", keywords.get(0)));
        sb.append(String.format("j/%s ", keywords.get(1)));
        sb.append(String.format("d/%s ", keywords.get(2)));
        return sb.toString();
    }
}
