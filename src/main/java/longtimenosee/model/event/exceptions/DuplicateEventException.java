package longtimenosee.model.event.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Events are considered duplicates if they have the same
 * identity).
 */
public class DuplicateEventException extends RuntimeException {
    public DuplicateEventException() {
        super("Operation would result in duplicate events");
    }
}
