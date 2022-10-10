package swift.model.task.exceptions;

/**
 * Signals that the operation will result in duplicate Tasks (Taskss are
 * considered duplicates if they have the same
 * name and owner).
 */
public class DuplicateTaskException extends RuntimeException {
    public DuplicateTaskException() {
        super("Operation would result in duplicate tasks");
    }
}
