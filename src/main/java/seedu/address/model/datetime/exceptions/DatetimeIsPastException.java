package seedu.address.model.datetime.exceptions;

import java.time.LocalDateTime;

public class DatetimeIsPastException extends RuntimeException {
    public DatetimeIsPastException() {
        super("Please input a date and time that is after the current time. The current date and time as of now is " 
                + LocalDateTime.now());
    }
}
