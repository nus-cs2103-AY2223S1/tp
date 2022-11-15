package seedu.waddle.model.item.exceptions;

/**
 * Signals that the operation will result in duplicate Items.
 */
public class DuplicateItemException extends RuntimeException {
    public DuplicateItemException() {
        super("Operation would result in duplicate items");
    }
}
