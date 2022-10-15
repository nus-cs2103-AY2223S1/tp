package longtimenosee.model.event.exceptions;

/**
 * Encapsulates exception where adding an event overlaps with another pre-existing one
 */
public class OverlapEventException extends RuntimeException {
    public OverlapEventException() {
        super("Operation would result in duplicate persons");
    }
}
