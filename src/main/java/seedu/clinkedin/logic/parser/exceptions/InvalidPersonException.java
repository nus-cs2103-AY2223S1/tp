package seedu.clinkedin.logic.parser.exceptions;

/**
 * Signals that the person being created is invalid.
 */
public class InvalidPersonException extends RuntimeException {
    public InvalidPersonException() {
        super("Person couldn't be parsed!");
    }
    public InvalidPersonException(String message) {
        super(message);
    }
}
