package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

//import java.util.Set;
import seedu.address.logic.commands.client.AddClientCommand;
//import seedu.address.logic.commands.client.EditClientCommand.EditPersonDescriptor;
import seedu.address.model.client.Person;
//import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code client}.
     */
    public static String getAddCommand(Person person) {
        return AddClientCommand.COMMAND_WORD + " " + AddClientCommand.COMMAND_FLAG + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code client}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().toString() + " ");
        sb.append(PREFIX_PHONE + person.getPhone().toString() + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().toString() + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    //    /**
    //     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
    //     */
    //    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
    //        StringBuilder sb = new StringBuilder();
    //        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.toString()).append(" "));
    //        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.toString()).append(" "));
    //        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.toString()).append(" "));
    //        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
    //        if (descriptor.getTags().isPresent()) {
    //            Set<Tag> tags = descriptor.getTags().get();
    //            if (tags.isEmpty()) {
    //                sb.append(PREFIX_TAG);
    //            } else {
    //                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
    //            }
    //        }
    //        return sb.toString();
    //    }
}
