package seedu.address.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents Date & Time encapsulation for tasks
 */
public class DateTime {

    private final LocalDateTime dateTime;

    /**
     * Constructs a default Date and Time, one day from the current time.
     */
    public DateTime() {
        dateTime = LocalDateTime.now().plusDays(1);
    }

    /**
     * TODO
     * @param validDateTime
     */
    public DateTime(String validDateTime) {
        assert(isValidDateTime(validDateTime));
        dateTime = LocalDateTime.parse(validDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * TODO
     * @return
     */
    public boolean isToday() {
        LocalDateTime today = LocalDateTime.now();

        return today.getDayOfYear() == dateTime.getDayOfYear()
                && today.getYear() == dateTime.getYear();
    }

    /**
     * TODO
     * @param test
     * @return
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
