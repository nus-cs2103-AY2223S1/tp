package seedu.application.logic.parser.exceptions;

/**
 * Represents a parse error caused by attempting to parse argument with
 * the pattern (space)(alphanumeric strings)(forward slash).
 */
public class ParsePrefixException extends ParseException {

    public ParsePrefixException(String message) {
        super(message);
    }
}
