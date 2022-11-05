package seedu.application.logic.parser.exceptions;

/**
 * Represents a parse error caused by attempting to parse argument with
 * the pattern (space)(alphanumeric strings)(forward slash).
 */
public class ParseUnknownPrefixFoundException extends ParseException {

    public ParseUnknownPrefixFoundException(String message) {
        super(message);
    }
}
