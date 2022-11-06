package seedu.uninurse.commons.exceptions;

/**
 * Signals that some given data does not fulfill some constraints.
 */
public class IllegalValueException extends Exception {
    /**
     * Creates an IllegalValueException with the given message.
     *
     * @param message should contain relevant information on the failed constraint(s)
     */
    public IllegalValueException(String message) {
        super(message);
    }

    /**
     * Creates an IllegalValueException with the given message and the cause of the exception.
     *
     * @param message should contain relevant information on the failed constraint(s)
     * @param cause of the main exception
     */
    public IllegalValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
