package seedu.clinkedin.model.person.exceptions;

/**
 * Signals that the operation is trying to add multiple ratings for a person which is not allowed.
 */
public class RatingAlreadyExistsException extends RuntimeException {
    public RatingAlreadyExistsException() {
        super("Rating for this person already exists");
    }
}
