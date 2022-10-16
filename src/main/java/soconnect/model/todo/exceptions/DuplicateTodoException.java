package soconnect.model.todo.exceptions;

/**
 * Signals that the operation will result in duplicate Todos (Todos are considered duplicates if they have the same
 * description).
 */
public class DuplicateTodoException extends RuntimeException {
    public DuplicateTodoException() {
        super("Operation would result in duplicate todos");
    }
}
