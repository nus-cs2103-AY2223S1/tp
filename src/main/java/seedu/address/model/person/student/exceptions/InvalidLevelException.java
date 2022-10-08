package seedu.address.model.person.student.exceptions;

/**
 * Signals that the operation takes in an invalid string input for Level.
 */
public class InvalidLevelException extends RuntimeException {
    public InvalidLevelException() {
        super("Please give the level in the correct format: [Primary/Secondary][1-6]\n" + "e.g. Primary1");
    }
}
