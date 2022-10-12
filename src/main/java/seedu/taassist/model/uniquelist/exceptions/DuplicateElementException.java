package seedu.taassist.model.uniquelist.exceptions;

/**
 * Signals that the operation will result in duplicate elements (Elements are considered duplicates if they have
 * the same identity).
 */
public class DuplicateElementException extends RuntimeException {

    public DuplicateElementException() {
        super("Operation would result in duplicate elements");
    }
}
