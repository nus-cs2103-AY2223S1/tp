package seedu.address.model.task.exceptions;


/**
 * Represents an error when the date of a deadline is already past
 */
public class DatePastException extends RuntimeException {
    public DatePastException() {
        super("Operation would result in duplicate tasks");
    }
}
