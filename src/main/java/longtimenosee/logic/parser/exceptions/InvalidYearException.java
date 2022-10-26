package longtimenosee.logic.parser.exceptions;

/**
 * Represents a parse error encountered by a parser when user inputs an invalid year.
 */
public class InvalidYearException extends Exception {

    public InvalidYearException(String message) {
        super(message);
    }
}
