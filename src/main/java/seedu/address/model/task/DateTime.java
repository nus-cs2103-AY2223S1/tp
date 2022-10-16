package seedu.address.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents Date & Time encapsulation for tasks
 */
public class DateTime {

    public static final String MESSAGE_CONSTRAINTS = "Date and time should be in the format of: "
            + "YYYY-MM-DDTHH:mm:ss i.e 2022-10-16T10:15:30";

    public final LocalDateTime dateTime;

    /**
     * Constructs a default Date and Time, one day from the current time.
     */
    public DateTime() {
        dateTime = LocalDateTime.now().plusDays(1);
    }

    /**
     * Constructs a {@code DateTime} with the given {@code validDateTime}.
     */
    public DateTime(String validDateTime) {
        assert(isValidDateTime(validDateTime));
        dateTime = LocalDateTime.parse(validDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * returns if the Date is today's date.
     */
    public boolean isToday() {
        LocalDateTime today = LocalDateTime.now();

        return today.getDayOfYear() == dateTime.getDayOfYear()
                && today.getYear() == dateTime.getYear();
    }

    /**
     * returns if the {@code test} is a valid date time string.
     */
    public static boolean isValidDateTime(String test) {
        try {
            DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(test);
            return true;
        } catch (DateTimeParseException dtpe) {
            return false;
        }
    }

    @Override
    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern("d MMM uuuu hh mm a"));
    }
}
