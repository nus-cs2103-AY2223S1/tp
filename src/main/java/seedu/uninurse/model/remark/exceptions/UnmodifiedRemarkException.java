package seedu.uninurse.model.remark.exceptions;

/**
 * Signals that the operation did not modify an existing duplicate Remark.
 * Remark are considered duplicates if they have the same description.
 */
public class UnmodifiedRemarkException extends RuntimeException {
    public UnmodifiedRemarkException() {
        super("Operation did not modify existing remark");
    }
}
