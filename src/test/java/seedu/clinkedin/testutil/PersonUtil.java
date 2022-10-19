package seedu.clinkedin.testutil;

import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.clinkedin.logic.commands.AddCommand;
import seedu.clinkedin.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.UniqueTagTypeMap;


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
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        // to be added.
        //        person.getTags().stream().forEach(
        //            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        //        );
        //        to be implemented later.
        //        person.getTags().stream().forEach(
        //            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        //        );
        sb.append(PREFIX_STATUS + person.getStatus().status + " ");
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
        if (descriptor.getNewTagTypeMap().get() != null && !descriptor.getNewTagTypeMap().get().isEmpty()) {
            UniqueTagTypeMap tags = descriptor.getNewTagTypeMap().get();
            sb.append(tags);
        }
        descriptor.getStatus().ifPresent(status -> sb.append(PREFIX_STATUS).append(status.status).append(" "));
        descriptor.getNote().ifPresent(note -> sb.append(PREFIX_NOTE).append(note.value).append(" "));
        return sb.toString();
    }
}
