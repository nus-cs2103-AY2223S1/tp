package seedu.address.model.task.exceptions;

/**
 * Signals that the operation will modify the identity of the task.
 */
public class TaskIdentityModifiedException extends RuntimeException {
    public TaskIdentityModifiedException() {
        super("Operation would change task identity");
    }
}
