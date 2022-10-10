package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate tasks (Tasks are considered duplicates if they have the same
 * identity).
 */
public class DuplicateTaskException extends RuntimeException {
    public DuplicateTaskException() {
        super("Operation would result in duplicate tasks");
    }
}
