package seedu.application.model.application.exceptions;

/**
 * Signals that the operation will result in duplicate Applications (Applications are considered duplicates
 * if they have the same identity).
 */
public class DuplicateApplicationException extends RuntimeException {
    public DuplicateApplicationException() {
        super("Operation would result in duplicate applications");
    }
}
