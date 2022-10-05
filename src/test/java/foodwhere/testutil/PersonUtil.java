package foodwhere.testutil;

import java.util.Set;

import foodwhere.logic.commands.AddCommand;
import foodwhere.logic.commands.EditCommand;
import foodwhere.logic.parser.CliSyntax;
import foodwhere.model.detail.Detail;
import foodwhere.model.person.Person;

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
        sb.append(CliSyntax.PREFIX_NAME + person.getName().fullName + " ");
        sb.append(CliSyntax.PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(CliSyntax.PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getDetails().stream().forEach(
            s -> sb.append(CliSyntax.PREFIX_DETAIL + s.detail + " ")
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
        descriptor.getAddress().ifPresent(address ->
                sb.append(CliSyntax.PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getDetails().isPresent()) {
            Set<Detail> details = descriptor.getDetails().get();
            if (details.isEmpty()) {
                sb.append(CliSyntax.PREFIX_DETAIL);
            } else {
                details.forEach(s -> sb.append(CliSyntax.PREFIX_DETAIL).append(s.detail).append(" "));
            }
        }
        return sb.toString();
    }
}
