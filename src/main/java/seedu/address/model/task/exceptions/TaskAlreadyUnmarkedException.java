package seedu.address.model.task.exceptions;

/**
 * Signals that the operation unmarks a task that is already indicated as not completed.
 */
public class TaskAlreadyUnmarkedException extends RuntimeException {
    public TaskAlreadyUnmarkedException() {
        super("The operation unmarks a task that is already unmarked");
    }
}
