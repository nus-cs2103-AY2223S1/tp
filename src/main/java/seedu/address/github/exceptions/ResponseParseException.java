package seedu.address.github.exceptions;

/**
 * Represents an error which occurs during parsing of a response.
 */
public class ResponseParseException extends RuntimeException {

    public ResponseParseException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ResponseParseException} with the specified detail {@code message} and {@code cause}.
     */
    public ResponseParseException(String message, Throwable cause) {
        super(message, cause);
    }
}


