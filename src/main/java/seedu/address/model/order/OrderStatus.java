package seedu.address.model.order;

public enum OrderStatus {
    PENDING("Pending"),
    NEGOTIATING("Negotiating"),
    DELIVERING("Delivering");

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

}
