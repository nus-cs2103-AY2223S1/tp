package seedu.uninurse.model.task;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents Date & Time encapsulation for tasks
 */
public class DateTime {

    public static final String DATE_TIME_PATTERN = "dd-MM-yyyy HHmm";
    public static final String MESSAGE_CONSTRAINTS = "Date and time should be in the format of: "
            + DATE_TIME_PATTERN + " i.e 16-10-2022 1015";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

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
        requireNonNull(validDateTime);
        assert(isValidDateTime(validDateTime));
        dateTime = LocalDateTime.parse(validDateTime, FORMATTER);
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
        requireNonNull(test);
        try {
            FORMATTER.parse(test);
            return true;
        } catch (DateTimeParseException dtpe) {
            return false;
        }
    }

    @Override
    public String toString() {
        return dateTime.format(FORMATTER);
    }
}
