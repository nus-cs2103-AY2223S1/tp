package seedu.guest.model.guest.exceptions;

/**
 * Signals that the operation will result in duplicate Guests (Guests are considered duplicates if they have the same
 * identity).
 */
public class DuplicateGuestException extends RuntimeException {
    public DuplicateGuestException() {
        super("Operation would result in duplicate guests");
    }
}
