package seedu.address.model.order;

import java.util.Arrays;

/**
 * Represents that current status of an order.
 */
public enum OrderStatus {
    PENDING("Pending"),
    NEGOTIATING("Negotiating"),
    DELIVERING("Delivering");

    public static final String MESSAGE_CONSTRAINTS =
            "Order status should be either 'Pending', 'Negotiating', or 'Delivering'.";
    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }

    /**
     * Checks whether a string represents one of the constants of this enum.
     *
     * @param input The string to be checked.
     * @return True iff the string does represent one constant.
     */
    public static boolean isValidOrderStatus(String input) {
        return Arrays
                .stream(OrderStatus.class.getEnumConstants())
                .anyMatch(x -> x.toString().equals(input));
    }

}
