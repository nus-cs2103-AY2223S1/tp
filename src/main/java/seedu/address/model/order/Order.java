package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a sorting order.
 * Guarantees: immutable; name is valid as declared in {@link #isValidOrderName(String)}
 */
public class Order {

    public static final String MESSAGE_CONSTRAINTS = "Order can only be asc for ascending or dsc for descending";
//    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String orderName;

    /**
     * Constructs a {@code Order}.
     *
     * @param orderName A valid order.
     */
    public Order(String orderName) {
        requireNonNull(orderName);
        checkArgument(isValidOrderName(orderName), MESSAGE_CONSTRAINTS);
        this.orderName = orderName;
    }

    /**
     * Returns true if a given string is a valid order name.
     */
    public static boolean isValidOrderName(String test) {
        return test.matches("dsc") || test.matches("asc");
    }

    /**
     * Returns true if a given order is descending order.
     */
    public boolean isDescending() {
        return this.orderName.matches("dsc");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.order.Order // instanceof handles nulls
                && orderName.equals(((seedu.address.model.order.Order) other).orderName)); // state check
    }

    @Override
    public int hashCode() {
        return orderName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + orderName + ']';
    }

}
