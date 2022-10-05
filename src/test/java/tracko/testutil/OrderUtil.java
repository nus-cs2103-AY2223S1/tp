package tracko.testutil;

import java.util.Set;

import tracko.logic.commands.order.AddOrderCommand;
import tracko.logic.commands.order.EditOrderCommand.EditPersonDescriptor;
import tracko.model.order.ItemQuantityPair;
import tracko.model.order.Order;
import tracko.model.tag.Tag;

import static tracko.logic.parser.CliSyntax.*;

/**
 * A utility class for Person.
 */
public class OrderUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddOrderCommand(Order order) {
        return AddOrderCommand.COMMAND_WORD + " " + getOrderDetails(order);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getOrderDetails(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + order.getName().fullName + " ");
        sb.append(PREFIX_PHONE + order.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + order.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + order.getAddress().value + " ");
        for (ItemQuantityPair itemQuantityPair: order.getItemList()) {
            sb.append(PREFIX_ITEM + itemQuantityPair.getItem());
            sb.append(PREFIX_QUANTITY + itemQuantityPair.getQuantity().toString());
        }
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
