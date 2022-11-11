package tracko.testutil;

import static tracko.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static tracko.logic.parser.CliSyntax.PREFIX_EMAIL;
import static tracko.logic.parser.CliSyntax.PREFIX_ITEM;
import static tracko.logic.parser.CliSyntax.PREFIX_NAME;
import static tracko.logic.parser.CliSyntax.PREFIX_PHONE;
import static tracko.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.ArrayList;
import java.util.List;

import tracko.logic.commands.order.AddOrderCommand;
import tracko.logic.commands.order.EditOrderCommand.EditOrderDescriptor;
import tracko.model.order.ItemQuantityPair;
import tracko.model.order.Order;

/**
 * A utility class for Order.
 */
public class OrderUtil {

    /**
     * Returns an add command string for adding the {@code order}.
     */
    public static String getBaseAddOrderCommand(Order order) {
        return AddOrderCommand.COMMAND_WORD + " " + getBaseOrderDetails(order);
    }

    /**
     * Returns the part of command string for initiating the given {@code order}'s details.
     */
    public static String getBaseOrderDetails(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + order.getName().fullName + " ");
        sb.append(PREFIX_PHONE + order.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + order.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + order.getAddress().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of the command string that adds items to the {@code order}'s details.
     */
    public static List<String> getItemQuantityPairDetails(Order order) {
        List<String> details = new ArrayList<>();
        for (ItemQuantityPair itemQuantityPair: order.getItemList()) {
            StringBuilder sb = new StringBuilder();
            sb.append(PREFIX_ITEM).append(itemQuantityPair.getItemName()).append(" ");
            sb.append(PREFIX_QUANTITY + itemQuantityPair.getQuantity().toString());
            details.add(sb.toString());
        }
        return details;
    }

    /**
     * Returns the part of command string for the given {@code EditOrderDescriptor}'s details.
     */
    public static String getEditOrderDescriptorDetails(EditOrderDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getUnlinkedItemToEdit().ifPresent(itemToEdit -> sb.append(PREFIX_ITEM).append(itemToEdit.getKey())
                .append(PREFIX_QUANTITY).append(itemToEdit.getValue()));
        return sb.toString();
    }
}
