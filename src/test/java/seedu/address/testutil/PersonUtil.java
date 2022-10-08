package seedu.address.testutil;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.person.Social;
import seedu.address.model.server.Server;
import seedu.address.model.tag.Tag;

import static seedu.address.logic.parser.CliSyntax.*;

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
        sb.append(PREFIX_MINECRAFT_NAME + person.getMinecraftName().username + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getSocials().stream().forEach(s -> sb.append(PREFIX_SOCIAL + s.toString() + " ")
        );
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        person.getServers().stream().forEach(
                s -> sb.append(PREFIX_MINECRAFT_SERVER + s.getServerName() + " ")
        );
        sb.append(PREFIX_TIMEZONE + person.getTimeZone().getOffsetInString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getMinecraftName().ifPresent(name -> sb.append(PREFIX_MINECRAFT_NAME)
                .append(name.username).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getSocials().isPresent()) {
            Set<Social> socials = descriptor.getSocials().get();
            if (socials.isEmpty()) {
                sb.append(PREFIX_SOCIAL);
            } else {
                socials.forEach(s -> sb.append(PREFIX_SOCIAL).append(s.toString()).append(" "));
            }
        }
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getServers().isPresent()) {
            Set<Server> servers = descriptor.getServers().get();
            if (servers.isEmpty()) {
                sb.append(PREFIX_SOCIAL);
            } else {
                servers.forEach(s -> sb.append(PREFIX_MINECRAFT_SERVER).append(s.getServerName()).append(" "));
            }
        }
        descriptor.getTimeZone().ifPresent(timeZone -> sb.append(PREFIX_TIMEZONE)
                .append(timeZone.getOffsetInString()).append(" "));
        return sb.toString();
    }
}
