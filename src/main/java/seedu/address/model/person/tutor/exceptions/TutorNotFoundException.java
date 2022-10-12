package seedu.address.model.person.tutor.exceptions;

/**
 * Signals that the operation is unable to find the specified tutor.
 */
public class TutorNotFoundException extends RuntimeException {
    public TutorNotFoundException() {
        super("Operation could not find the specified tutor");
    }
}
