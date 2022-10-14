package seedu.address.model.datetime;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a datetime of various objects in the ModQuik.
 * Guarantees: immutable
 */

public class Datetime {
    public static final String MESSAGE_CONSTRAINTS =
            "Date and time should be in yyyy-MM-dd HH:mm format, e.g. 2022-01-01 08:00";
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public final LocalDateTime datetime;


    /**
     * Constructs a {@code Datetime}.
     *
     * @param datetime A valid datetime that is formatted appropriately.
     */
    public Datetime(String datetime) {
        requireNonNull(datetime);
        checkArgument(isValidDatetime(datetime), MESSAGE_CONSTRAINTS);
        this.datetime = LocalDateTime.parse(datetime, DATE_TIME_FORMAT);
    }

    public static boolean isValidDatetime(String datetimeString) {
        try {
            LocalDateTime.parse(datetimeString, DATE_TIME_FORMAT);
        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;
    }

    /**
     * Returns a formatted datetime.
     *
     * @return Formatted datetime
     */
    public String toFormatted() {
        return datetime.format(DATE_TIME_FORMAT);
    }

    @Override
    public String toString() {
        return datetime.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Datetime // instanceof handles nulls
                && datetime.equals(((Datetime) other).datetime)); // state check
    }

    @Override
    public int hashCode() {
        return datetime.hashCode();
    }

}
