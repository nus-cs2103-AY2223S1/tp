package seedu.address.model.task.exceptions;

/**
 * Signals that the operation will result in duplicate Tasks
 * Persons are considered duplicates if they have the same // TODO.
 */
public class DuplicateTaskException extends RuntimeException {
    public DuplicateTaskException() {
        super("Operation would result in duplicate tasks");
    }
}
