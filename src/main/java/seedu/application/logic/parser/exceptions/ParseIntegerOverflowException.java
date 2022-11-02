package seedu.application.logic.parser.exceptions;

/**
 * Represents a parse error caused by attempting to parse an integer that does not fit in an int.
 */
public class ParseIntegerOverflowException extends ParseException {
    public ParseIntegerOverflowException(String message) {
        super(message);
    }
}
