package seedu.address.model.tag.exceptions;

/**
 * PriorityTagAlreadyExistsException is an exception which is
 * thrown when the task already contains the priority status.
 */
public class PriorityTagAlreadyExistsException extends RuntimeException {
    public PriorityTagAlreadyExistsException() {
        super("The priority status already exists for the task.");
    }
}
