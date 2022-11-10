package seedu.codeconnect.testutil;

import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Set;

import seedu.codeconnect.logic.commands.AddContactCommand;
import seedu.codeconnect.logic.commands.EditContactCommand.EditPersonDescriptor;
import seedu.codeconnect.model.module.Module;
import seedu.codeconnect.model.person.Person;
import seedu.codeconnect.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddContactCommand(Person person) {
        return AddContactCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        person.getModules().stream().forEach(
                s -> sb.append(PREFIX_MODULE + s.moduleName + " ")
        );
        sb.append(PREFIX_GITHUB + person.getGithub().value + " ");
        sb.append(PREFIX_TELEGRAM + person.getTelegram().telehandle + " ");
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
        descriptor.getGithub().ifPresent(gh -> sb.append(PREFIX_GITHUB).append(gh.value).append(" "));
        descriptor.getTelegram().ifPresent(tele -> sb.append(PREFIX_TELEGRAM).append(tele.telehandle).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getMods().isPresent()) {
            Set<Module> modules = descriptor.getMods().get();
            if (modules.isEmpty()) {
                sb.append(PREFIX_MODULE);
            } else {
                modules.forEach(s -> sb.append(PREFIX_MODULE).append(s.moduleName).append(" "));
            }
        }
        return sb.toString();
    }
}
