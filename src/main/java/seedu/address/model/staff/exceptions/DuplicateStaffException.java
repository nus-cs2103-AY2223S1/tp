package seedu.address.model.staff.exceptions;

/**
 * Signals that the operation will result in duplicate Staffs
 * (Staff are considered duplicates if they have the same
 * identity).
 */
public class DuplicateStaffException extends RuntimeException {
    public DuplicateStaffException() {
        super("Operation would result in duplicate staff");
    }
}
