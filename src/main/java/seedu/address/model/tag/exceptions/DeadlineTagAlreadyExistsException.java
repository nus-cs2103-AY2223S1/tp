package seedu.address.model.tag.exceptions;

/**
 * DeadlineTagAlreadyExistsException is an exception which is
 * thrown when the task already contains the deadline.
 */
public class DeadlineTagAlreadyExistsException extends RuntimeException {
    public DeadlineTagAlreadyExistsException() {
        super("There is already a deadline for the task.");
    }
}
