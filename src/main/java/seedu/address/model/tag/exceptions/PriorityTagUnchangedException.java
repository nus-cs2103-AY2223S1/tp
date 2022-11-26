package seedu.address.model.tag.exceptions;

/**
 * PriorityTagUnchangedException is an exception which is thrown when the
 * newly provided priority tag is the same as the current priority tag.
 */
public class PriorityTagUnchangedException extends RuntimeException {
    /**
     * The constructor of the PriorityTagUnchangedException. Sets the message for the
     * exception.
     */
    public PriorityTagUnchangedException() {
        super("The priority status provided is "
                + "the same as the current priority status for the task.");
    }
}
