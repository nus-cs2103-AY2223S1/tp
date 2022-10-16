package hobbylist.model.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import hobbylist.commons.util.AppUtil;

/**
 * Represents a Date of an activity.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Date {

    public static final String MESSAGE_EXCEPTION =
            "Sorry, the input format should be like yyyy-mm-dd, eg 1921-04-12";
    public static final String VALIDATION_REGEX =
            "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]";
    private java.time.LocalDate date;
    private String orginString;
    /**
     * Constructs a localDate.
     *
     * @param date With format yyyy-mm-dd
     */
    public Date(String date) throws DateTimeParseException {
        if (date != null) {
            AppUtil.checkArgument(isValidDateString(date), MESSAGE_EXCEPTION);
            try {
                this.date = LocalDate.parse(date);
                this.orginString = date;
            } catch (DateTimeParseException dateTimeParseException) {
                throw dateTimeParseException;
            }
        }
    }
    public LocalDate getDate() {
        return this.date;
    }

    public String getOrginString() {
        return orginString;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidDateString(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && this.date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ']';

    }

}
