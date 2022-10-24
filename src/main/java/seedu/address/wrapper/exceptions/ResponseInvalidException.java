package seedu.address.wrapper.exceptions;

/**
 * Represents an error which occurs when getting a link.
 */
public class ResponseInvalidException extends RuntimeException {

    public ResponseInvalidException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ResponseInvalidException} with the specified detail {@code message} and {@code cause}.
     */
    public ResponseInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
