package seedu.address.logic.task.exceptions;

/**
 * Signals that the operation will result in duplicate Tasks (Tasks are considered duplicates if they have the same
 * fields).
 */
public class DuplicateTaskException extends RuntimeException {
    public DuplicateTaskException() {
        super("Operation would result in duplicate tasks");
    }
}
