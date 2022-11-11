package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Records (Records are considered duplicates if they have the same
 * date and data).
 */
public class DuplicateRecordException extends RuntimeException {
    public DuplicateRecordException() {
        super("Operation would result in duplicate records");
    }
}
