package seedu.uninurse.model.task.exceptions;

/**
 * Signals that the operation will result in duplicate Tasks.
 * Tasks are considered based on their equality (implemented by the different types of Tasks).
 */
public class DuplicateTaskException extends RuntimeException {
    public DuplicateTaskException() {
        super("Operation would result in duplicate Task");
    }
}
