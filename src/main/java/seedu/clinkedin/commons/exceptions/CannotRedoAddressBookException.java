package seedu.clinkedin.commons.exceptions;

/**
 * Signals that undo operation is not possible.
 */
public class CannotRedoAddressBookException extends Exception {
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public CannotRedoAddressBookException(String message) {
        super(message);
    }

    /**
     * @param message should contain relevant information on the failed constraint(s)
     * @param cause of the main exception
     */
    public CannotRedoAddressBookException(String message, Throwable cause) {
        super(message, cause);
    }
}
