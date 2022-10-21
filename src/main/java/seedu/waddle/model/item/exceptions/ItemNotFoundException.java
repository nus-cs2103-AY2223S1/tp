package seedu.waddle.model.item.exceptions;

/**
 * Signals that the operation is unable to find the specified item.
 */
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException() {
        super("The item could not be found.");
    }
}
