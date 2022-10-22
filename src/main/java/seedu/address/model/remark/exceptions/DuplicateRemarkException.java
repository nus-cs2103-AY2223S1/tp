package seedu.address.model.remark.exceptions;

/**
 * Signals that the operation will result in duplicate Companies
 * (Companies are considered duplicates if they have the same identity).
 */
public class DuplicateRemarkException extends RuntimeException {
    public DuplicateRemarkException() {
        super("Operation would result in duplicate Remark");
    }
}
