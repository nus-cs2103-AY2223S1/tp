package seedu.clinkedin.commons.exceptions;

/**
 * Signals that undo operation is not possible.
 */
public class CannotUndoAddressBookException extends Exception {
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public CannotUndoAddressBookException(String message) {
        super(message);
    }

    /**
     * @param message should contain relevant information on the failed constraint(s)
     * @param cause of the main exception
     */
    public CannotUndoAddressBookException(String message, Throwable cause) {
        super(message, cause);
    }
}
