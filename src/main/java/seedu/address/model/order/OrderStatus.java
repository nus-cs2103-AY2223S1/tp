package seedu.address.model.order;

import java.util.Arrays;

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

    public static boolean isValidOrderStatus(String input) {
        return Arrays
                .stream(OrderStatus.class.getEnumConstants())
                .anyMatch(x -> x.toString().equals(input));
    }

}
