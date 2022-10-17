package seedu.address.model.bill.exceptions;

/**
 * Signals that the operation will result in duplicate Bills
 * (Appointments are considered duplicates if they have the same identity).
 */
public class DuplicateBillException extends RuntimeException {
    public DuplicateBillException() {
        super("Operation would result in duplicate bills");
    }
}
