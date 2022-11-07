package soconnect.model.todo.exceptions;

/**
 * Signals that the operation will result in duplicate {@code Todo}s.
 */
public class DuplicateTodoException extends RuntimeException {
    public DuplicateTodoException() {
        super("Operation would result in duplicate todos!");
    }
}
