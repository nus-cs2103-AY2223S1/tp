package gim.model.date;

import static gim.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Represents a Date of an Exercise in Gim.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDateByRegex(String)}
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS = "Date input is invalid or non-existent." + "\n"
            + "Date should be valid and in DD/MM/YYYY format (e.g. 15/05/2002)";
    public static final String DATE_PATTERN = "dd/MM/uuuu";
    /**
     * Input string format for the date should be DD/MM/YYYY (e.g. 25/01/2022)
     */
    public static final String VALIDATION_REGEX = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])[0-9][0-9]$";

    /**
     * The formatter uses uuuu instead of yyyy to ensure a stricter formatting restriction for the year.
     * The strict ResolverStyle is used to prevent invalid or non-existent dates from being added.
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN)
            .withResolverStyle(ResolverStyle.STRICT);

    public final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A date with valid format.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDateByRegex(date), MESSAGE_CONSTRAINTS);
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        this.date = parsedDate;
    }

    /**
     * Constructs a Date object that is initialized to today's date.
     *
     * This is used when the user did not input any date.
     */
    public Date() {
        LocalDate today = LocalDate.now();
        String todayText = today.format(formatter);
        LocalDate todayFormatted = LocalDate.parse(todayText, formatter);
        this.date = todayFormatted;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDateByRegex(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Obtains the day of week.
     * Example: 21/08/2022 is a Friday
     * @return string representing the day of week
     */
    public String getDayString() {
        return date.getDayOfWeek().toString();
    }

    /**
     * Checks whether this Date instance is within a given range.
     * Note that this method includes both start and end parameters.
     * @param start starting date (inclusive)
     * @param end ending date (inclusive)
     * @return true or false depending on the check
     */
    public boolean checkWithinRange(Date start, Date end) {
        return !this.date.isBefore(start.date) && !this.date.isAfter(end.date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

}
