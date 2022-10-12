package seedu.address.logic.task.exceptions;

public class DatePastException extends RuntimeException {
    public DatePastException() {
        super("Operation would result in duplicate tasks");
    }
}
