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
     * Checks if an order status is valid.
     *
     * @param input A string indicating the order status.
     * @return True if the input status is a valid status, false otherwise.
     */
    public static boolean isValidOrderStatus(String input) {
        return Arrays
                .stream(OrderStatus.class.getEnumConstants())
                .anyMatch(x -> x.toString().equals(input));
    }

}
