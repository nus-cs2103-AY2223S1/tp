package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCustomerCommand;
import seedu.address.logic.commands.EditCustomerCommand.EditCustomerDescriptor;
import seedu.address.model.customer.Customer;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Customer.
 */
public class CustomerUtil {

    /**
     * Returns an add command string for adding the {@code customer}.
     */
    public static String getAddCustomerCommand(Customer customer) {
        return AddCustomerCommand.COMMAND_WORD + " " + getCustomerDetails(customer);
    }

    /**
     * Returns the part of command string for the given {@code customer}'s details.
     */
    public static String getCustomerDetails(Customer customer) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + customer.getName().fullName + " ");
        sb.append(PREFIX_PHONE + customer.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + customer.getEmail().value + " ");
        if (!customer.getAddress().isEmpty()) {
            sb.append(PREFIX_ADDRESS + customer.getAddress().get().value + " ");
        }
        customer.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditCustomerDescriptor}'s details.
     */
    public static String getEditCustomerDescriptorDetails(EditCustomerDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
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
}
