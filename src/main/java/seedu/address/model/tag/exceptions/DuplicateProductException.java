package seedu.address.model.tag.exceptions;

/**
 * Signals that the operation will result in duplicate Products.
 */
public class DuplicateProductException extends RuntimeException {
    public DuplicateProductException() {
        super("Operation would result in duplicate products");
    }
}
