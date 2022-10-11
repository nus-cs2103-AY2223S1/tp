package seedu.address.model.order.exceptions;

public class DuplicateOrderException extends RuntimeException {
    public DuplicateOrderException() {
        super("Operation would result in duplicate orders");
    }
}
