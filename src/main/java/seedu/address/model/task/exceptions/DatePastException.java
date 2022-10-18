package seedu.address.model.task.exceptions;


/**
 * Represents an error when the date of a deadline is already past
 */
public class DatePastException extends RuntimeException {
    public DatePastException() {
        super("Deadline must be a date in the future");
    }
}
