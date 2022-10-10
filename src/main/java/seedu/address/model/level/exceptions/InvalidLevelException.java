package seedu.address.model.level.exceptions;

/**
 * Signals that the operation takes in an invalid string input for Level.
 */
public class InvalidLevelException extends RuntimeException {

    /**
     * Constructor for an InvalidLevelException.
     */
    public InvalidLevelException() {
        super("Please give the level in the correct format: [Primary/Secondary][1-6]\n" + "e.g. Primary1");
    }
}
