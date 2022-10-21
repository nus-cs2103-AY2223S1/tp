package tracko.model.order.exceptions;

/**
 * Represents an error when user tries to mark an already paid order as paid again
 */
public class OrderAlreadyPaidException extends RuntimeException {
    public OrderAlreadyPaidException() {
        super("Order is already paid for");
    }
}
