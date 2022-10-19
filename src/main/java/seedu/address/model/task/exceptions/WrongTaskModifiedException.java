package seedu.address.model.task.exceptions;

/**
 * Signals that the operation modifies the wrong task.
 */
public class WrongTaskModifiedException extends RuntimeException {
    public WrongTaskModifiedException() {
        super("Operation modifies the wrong task");
    }
}
