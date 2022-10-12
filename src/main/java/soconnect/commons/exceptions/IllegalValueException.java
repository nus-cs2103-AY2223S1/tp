package soconnect.commons.exceptions;

/**
 * Signals that some given data does not fulfill some constraints.
 */
public class IllegalValueException extends Exception {
    /**
     * @param message should contain relevant information on the failed constraint(s).
     */
    public IllegalValueException(String message) {
        super(message);
    }

    /**
     * @param message Should contain relevant information on the failed constraint(s).
     * @param cause Cause of the main exception.
     */
    public IllegalValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
