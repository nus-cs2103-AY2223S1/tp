package seedu.address.model.tuitionclass.exceptions;

/**
 * Signals that the operation takes in an invalid string input for Day.
 */
public class InvalidDayException extends RuntimeException {

    /**
     * Constructor for an InvalidDayException.
     */
    public InvalidDayException() {
        super("Please give the day spelt out in full.\n"
                + "e.g. Monday");
    }
}
