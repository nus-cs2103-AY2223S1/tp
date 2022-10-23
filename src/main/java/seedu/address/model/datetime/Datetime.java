package seedu.address.model.datetime;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.datetime.DatetimeCommonUtils.DATETIME_FORMATTER;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a datetime of various objects in the ModQuik.
 * Guarantees: immutable
 */

public class Datetime {
    public final LocalDateTime datetime;


    /**
     * Constructs a {@code Datetime}.
     *
     * @param datetime A valid datetime that is formatted appropriately.
     */
    public Datetime(LocalDateTime datetime) {
        requireNonNull(datetime);
        this.datetime = datetime;
    }



    /**
     * Checks whether datetime string is of valid format.
     *
     * @param datetimeString A datetime string
     * @return Whether format is valid
     */
    public static boolean isValidDatetime(String datetimeString) {
        try {
            LocalDateTime.parse(datetimeString, DATETIME_FORMATTER);
        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;
    }

    public static Datetime fromFormattedString(String datetimeString) {
        LocalDateTime datetime = LocalDateTime.parse(datetimeString, DATETIME_FORMATTER);
        return new Datetime(datetime);
    }

    /**
     * Returns a formatted datetime.
     *
     * @return Formatted datetime
     */
    public String toFormatted() {
        return datetime.format(DATETIME_FORMATTER);
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
