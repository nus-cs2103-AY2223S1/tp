package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;

import java.util.Set;

import seedu.address.logic.commands.AddClientCommand;
import seedu.address.logic.commands.EditClientCommand.EditClientDescriptor;
import seedu.address.model.client.Client;
import seedu.address.model.product.Product;

/**
 * A utility class for Client.
 */
public class ClientUtil {

    /**
     * Returns an add command string for adding the {@code client}.
     */
    public static String getAddCommand(Client client) {
        return AddClientCommand.COMMAND_WORD + " " + getClientDetails(client);
    }

    /**
     * Returns the part of command string for the given {@code client}'s details.
     */
    public static String getClientDetails(Client client) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + client.getName().fullName + " ");
        sb.append(PREFIX_PHONE + client.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + client.getEmail().get().toString() + " ");
        sb.append(PREFIX_ADDRESS + client.getAddress().get().toString() + " ");
        client.getProducts().stream().forEach(
            s -> sb.append(PREFIX_PRODUCT + s.productName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditClientDescriptor}'s details.
     */
    public static String getEditClientDescriptorDetails(EditClientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getProducts().isPresent()) {
            Set<Product> products = descriptor.getProducts().get();
            if (products.isEmpty()) {
                sb.append(PREFIX_PRODUCT);
            } else {
                products.forEach(s -> sb.append(PREFIX_PRODUCT).append(s.productName).append(" "));
            }
        }
        return sb.toString();
    }
}
