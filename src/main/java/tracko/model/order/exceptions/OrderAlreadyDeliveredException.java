package tracko.model.order.exceptions;

/**
 * Represents an error when user tries to mark an already delivered order as delivered again
 */
public class OrderAlreadyDeliveredException extends RuntimeException {
    public OrderAlreadyDeliveredException() {
        super("Order has been delivered previously");
    }
}
