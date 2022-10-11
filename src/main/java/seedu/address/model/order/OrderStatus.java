package seedu.address.model.order;

public class OrderStatus {

    private static final String PENDING = "pending";
    private static final String NEGOTIATING = "negotiating";
    private static final String DELIVERING = "delivering";

    private final String status;

    public OrderStatus(String status) {
        this.status = status;
    }

    public static OrderStatus getPendingStatus() {
        return new OrderStatus(PENDING);
    }

    public static OrderStatus getNegotiatingStatus() {
        return new OrderStatus(NEGOTIATING);
    }

    public static OrderStatus getDeliveringStatus() {
        return new OrderStatus(DELIVERING);
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof OrderStatus) {
            OrderStatus otherStatus = (OrderStatus) other;
            return status.equals(otherStatus.status);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }

    @Override
    public String toString() {
        return status;
    }

}
