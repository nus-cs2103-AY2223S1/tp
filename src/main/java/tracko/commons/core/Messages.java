package tracko.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_ORDERS_FOUND_OVERVIEW = "%1$d order(s) found!";
    public static final String MESSAGE_INVALID_ORDER_DISPLAYED_INDEX = "The order index provided is invalid";
    public static final String MESSAGE_ORDERS_SORTED_OVERVIEW = "%1$d order(s) sorted!";
    public static final String MESSAGE_ITEMS_FOUND_OVERVIEW = "%1$d item(s) found!";
    public static final String MESSAGE_INVALID_ITEM_DISPLAYED_INDEX = "The item index provided is invalid";
    public static final String MESSAGE_ORDER_ALREADY_DELIVERED = "Order has been delivered previously";
    public static final String MESSAGE_ORDER_ALREADY_PAID = "Order has already been paid for";

    public static final String MESSAGE_INSUFFICIENT_STOCK =
            "There is insufficient stock in the inventory to deliver order";
}
