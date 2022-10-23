package seedu.address.model.tag.exceptions;

/**
 * DeadlineTagDoesNotExist is an exception which is thrown when the deadline tag
 * does not exist.
 */
public class DeadlineTagDoesNotExist extends RuntimeException {
    /**
     * The constructor of DeadlineTagDoesNotExist. Sets the message that
     * the deadline tag does not exist.
     */
    public DeadlineTagDoesNotExist() {
        super("The deadline tag does not exist.");
    }
}
