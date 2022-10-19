package seedu.address.model.event.exceptions;

/**
 * Exception to indicate that the operation will result in duplicate Events.
 * Events will be classified as duplicates if they have the same eventTitle.
 */
public class DuplicateEventException extends RuntimeException {
    public DuplicateEventException() {
        super("This operation would result in duplicate events. Please try again");
    }
}
