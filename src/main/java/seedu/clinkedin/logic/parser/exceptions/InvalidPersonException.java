package seedu.clinkedin.logic.parser.exceptions;

public class InvalidPersonException extends RuntimeException {
    public InvalidPersonException() {
        super("Person couldn't be parsed!");
    }
    public InvalidPersonException(String message) {
        super(message);
    }
}
