package seedu.travelr.logic.parser.exceptions;

import seedu.travelr.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException {

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        if (this.getCause() == null) {
            return super.getMessage();
        } else {
            return this.getCause().getMessage() + "\n\n" + super.getMessage();
        }
    }
}
