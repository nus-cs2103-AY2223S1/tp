package seedu.address.model.tag.exceptions;

/**
 * DeadlineTagUnchangedException is an exception which is thrown when the
 * newly provided deadline tag is the same as the existing deadline tag.
 */
public class DeadlineTagUnchangedException extends RuntimeException {
    /**
     * The constructor of the DeadlineTagUnchangedException. Sets the message for the
     * exception.
     */
    public DeadlineTagUnchangedException() {
        super("The deadline provided is"
                + " the same as the current deadline for the task.");
    }
}
