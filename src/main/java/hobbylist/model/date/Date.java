package hobbylist.model.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import hobbylist.commons.util.AppUtil;

/**
 * Represents a Date of an activity.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDateString(String)}
 */
public class Date {
    public static final String MESSAGE_EXCEPTION =
            "Invalid date! The input format should be like yyyy-mm-dd, eg 1921-04-12\n"
            + "The value of year should be greater or equal than 1000!\n";
    public static final String VALIDATION_YEAR_REGEX = "[0-9][0-9][0-9][0-9]";
    public static final String VALIDATION_MONTH_REGEX = "[0-9][0-9][0-9][0-9]-[0-9][0-9]";
    public static final String VALIDATION_DATE_REGEX =
            "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]";
    private java.time.LocalDate date;
    private String originalString;
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
                this.originalString = date;
            } catch (DateTimeParseException dateTimeParseException) {
                throw dateTimeParseException;
            }
        }
    }
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns origin description of the date (yyyy-mm-dd)
     */
    public String getOriginalString() {
        return originalString;
    }

    /**
     * Check if the given string is a valid year.
     * @param testString String input from the user.
     * @return true if the given string is a valid year.
     */
    public static boolean isValidYearString(String testString) {
        if (!testString.matches(VALIDATION_YEAR_REGEX)) {
            return false;
        }
        return Integer.parseInt(testString) != 0;
    }

    /**
     * Check if the given string is a valid month.
     * @param testString String input from the user.
     * @return true if the given string is a valid year.
     */
    public static boolean isValidMonthString(String testString) {
        if (!testString.matches(VALIDATION_MONTH_REGEX)) {
            return false;
        }
        return Integer.parseInt(testString.substring(5, 7)) >= 1 && Integer.parseInt(testString.substring(5, 7)) <= 12;
    }

    /**
     * Check if the given string is a valid date.
     */
    public static boolean isValidDateString(String test) {
        if (!test.matches(VALIDATION_DATE_REGEX)) {
            return false;
        }
        return Integer.parseInt(test.split("-")[0]) != 0;
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

    @Override
    public String toString() {
        return originalString;
    }

    /**
     * Format state as text for viewing.
     */
    public String toViewString() {
        return '[' + toDisplayedString() + ']';

    }

    /**
     * Return yyyy-mm format of this date.
     */
    public String yearMonthDescription() {
        String[] temp = this.getOriginalString().split("-");
        return temp[0] + "-" + temp[1];
    }

    /**
     * Return yyyy format of this date.
     */
    public String yearDescription() {
        return this.getOriginalString().split("-")[0];
    }

    public String toDisplayedString() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

}
