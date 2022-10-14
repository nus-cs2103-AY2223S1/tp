package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.model.application.Application;

/**
 * A utility class for Application.
 */
public class ApplicationUtil {

    /**
     * Returns an add command string for adding the {@code application}.
     */
    public static String getAddCommand(Application application) {
        return AddCommand.COMMAND_WORD + " " + getApplicationDetails(application);
    }

    /**
     * Returns the part of command string for the given {@code application}'s details.
     */
    public static String getApplicationDetails(Application application) {
        return PREFIX_COMPANY + application.getCompany().company + " "
                + PREFIX_CONTACT + application.getContact().value + " "
                + PREFIX_EMAIL + application.getEmail().value + " "
                + PREFIX_POSITION + application.getPosition().value + " "
                + PREFIX_DATE + application.getDate().toCommandString();
    }

    /**
     * Returns the part of command string for the given {@code EditApplicationDescriptor}'s details.
     */
    public static String getEditApplicationDescriptorDetails(EditApplicationDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getCompany().ifPresent(company -> sb.append(PREFIX_COMPANY).append(company.company).append(" "));
        descriptor.getContact().ifPresent(contact -> sb.append(PREFIX_CONTACT).append(contact.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getPosition().ifPresent(position -> sb.append(PREFIX_POSITION).append(position.value).append(" "));
        descriptor.getDate().ifPresent(date -> sb.append(PREFIX_DATE).append(date.toCommandString()).append(" "));
        return sb.toString();
    }
}
