package seedu.address.model.tag.exceptions;

/**
 * PriorityTagDoesNotExist is an exception which is thrown when the priority
 * tag does not exist.
 */
public class PriorityTagDoesNotExist extends RuntimeException {
    /**
     * The constructor of PriorityTagDoesNotExist. Sets the message of priority tag
     * does not exist.
     */
    public PriorityTagDoesNotExist() {
        super("The priority tag does not exist.");
    }
}
