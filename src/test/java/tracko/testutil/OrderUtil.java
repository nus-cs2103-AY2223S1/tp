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
}
