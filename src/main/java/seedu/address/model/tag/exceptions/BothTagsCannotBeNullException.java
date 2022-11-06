package seedu.address.model.tag.exceptions;

/**
 * BothTagsCannotBeNullException is an exception which is thrown when both priority tag
 * and the deadline tag are null in AddTagCommand method
 */
public class BothTagsCannotBeNullException extends RuntimeException {
    public BothTagsCannotBeNullException() {
        super("Both the priority tag and the deadline tag cannot be null!");
    }
}
