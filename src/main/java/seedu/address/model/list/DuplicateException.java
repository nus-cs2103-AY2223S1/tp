package seedu.address.model.list;

/**
 * Signals that the operation will result in duplicate Projects
 * (Projects are considered duplicates if they have the same identity).
 */
public class DuplicateException extends RuntimeException {
    public DuplicateException() {
        super("Operation would result in duplicate items");
    }
}
