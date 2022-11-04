package seedu.address.model.task.exceptions;

/**
 * Signals that the operation marks a task that is already indicated as completed.
 */
public class TaskAlreadyMarkedException extends RuntimeException {
    public TaskAlreadyMarkedException() {
        super("The operation marks a task that is already unmarked");
    }

}
