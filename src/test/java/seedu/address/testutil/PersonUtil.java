package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NETWORTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.CreateCommand;
import seedu.address.logic.commands.UpdateCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;



/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getCreateCommand(Person person) {
        return CreateCommand.COMMAND_WORD + " " + getCreatedPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().getFullName() + " ");
        sb.append(PREFIX_PHONE + person.getPhone().getValue() + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().getValue() + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().getValue() + " ");
        sb.append(PREFIX_DESCRIPTION + person.getDescription().getValue() + " ");
        sb.append(PREFIX_NETWORTH + person.getNetWorth().getValue() + " ");
        person.getMeetingTimes().stream().forEach(
                s -> sb.append(PREFIX_MEETING_TIME + s.value + " ")
        );
        sb.append(PREFIX_FILEPATH + person.getFilePath().getValue() + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    public static String getCreatedPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().getFullName() + " ");
        sb.append(PREFIX_PHONE + person.getPhone().getValue() + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().getValue() + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().getValue() + " ");
        sb.append(PREFIX_DESCRIPTION + person.getDescription().getValue() + " ");
        sb.append(PREFIX_NETWORTH + person.getNetWorth().getValue() + " ");
        person.getMeetingTimes().stream().forEach(
                s -> sb.append(PREFIX_MEETING_TIME + s.value + " ")
        );
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
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.getFullName()).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.getValue()).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.getValue()).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.getValue()).append(" "));
        descriptor.getDescription().ifPresent(description
                -> sb.append(PREFIX_DESCRIPTION).append(description.getValue()).append(" "));
        descriptor.getNetWorth().ifPresent(netWorth
                -> sb.append(PREFIX_NETWORTH).append(netWorth.getValue()).append(" "));
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
