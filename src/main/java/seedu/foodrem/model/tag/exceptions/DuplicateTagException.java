package seedu.foodrem.model.tag.exceptions;

/**
 * Signals that the operation will result in duplicate Tags (Tags are considered duplicates if they have the same
 * name).
 */
public class DuplicateTagException extends RuntimeException {
    public DuplicateTagException() {
        super("Operation would result in duplicate tags");
    }
}
