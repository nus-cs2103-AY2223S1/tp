package seedu.address.model.tag.exceptions;

/**
 * PriorityTagDoesNotExistException is an exception which is thrown when the priority
 * tag does not exist.
 */
public class PriorityTagDoesNotExistException extends RuntimeException {
    /**
     * The constructor of PriorityTagDoesNotExist. Sets the message of priority tag
     * does not exist.
     */
    public PriorityTagDoesNotExistException() {
        super("The priority tag does not exist.");
    }
}
