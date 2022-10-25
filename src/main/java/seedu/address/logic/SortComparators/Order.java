package seedu.address.logic.SortComparators;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

public class Order {
    
    public enum OrderType {
        ASC,
        DESC
    }

    public static final String MESSAGE_CONSTRAINTS = "Order should be ASC or DESC.";

    private final OrderType orderType;

    /**
     * Constructs an {@code Order}.
     *
     * @param specifiedOrder A valid order.
     */
    public Order(String specifiedOrder) {
        requireNonNull(specifiedOrder);
        specifiedOrder = specifiedOrder.toUpperCase();
        checkArgument(isValidOrder(specifiedOrder), MESSAGE_CONSTRAINTS);
        orderType = OrderType.valueOf(specifiedOrder);
    }

    /**
     * Returns true if a given string is a valid order.
     */
    public static boolean isValidOrder(String test) {
        return Arrays.stream(OrderType.values()).anyMatch(p -> test.equalsIgnoreCase(p.name()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Order // instanceof handles nulls
                && orderType.equals(((Order) other).orderType)); // state check
    }

    @Override
    public int hashCode() {
        return orderType.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return orderType.toString();
    }
}

