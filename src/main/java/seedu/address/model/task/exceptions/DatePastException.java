package seedu.address.model.task.exceptions;

public class DatePastException extends RuntimeException {
    public DatePastException() {
        super("Operation would result in duplicate tasks");
    }
}
