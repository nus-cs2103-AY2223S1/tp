package seedu.address.model.person.exceptions;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Signals that the operation is unable to find the specified person.
 */
public class LoanOutOfBoundsException extends ParseException {
    public LoanOutOfBoundsException(String message) {
        super(message);
    }
}
