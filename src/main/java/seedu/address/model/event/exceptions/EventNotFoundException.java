package seedu.address.model.event.exceptions;

/**
 * Exception indicates that the operation is unable to find the specified person.
 */
public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
        super("The event you are looking for cannot not found. Please try again.");
    }
}
