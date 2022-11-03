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
    public static final String DEFAULT_DATE_PATTERN = "dd/MM/uuuu";
    public static final String MESSAGE_CONSTRAINTS_INVALID = "Date is non-existent. Please input a valid date.";
    public static final String MESSAGE_CONSTRAINTS_FORMAT = "Date input format is invalid." + "\n"
            + "Accepted Formats: \n"
            + "DAY/MONTH/YEAR or YEAR/MONTH/DAY\n"
            + "DAY-MONTH-YEAR or YEAR-MONTH-DAY\n"
            + "DAY MONTH YEAR or YEAR MONTH DAY\n\n"
            + "DAY: 1 or 2 digits allowed\n"
            + "MONTH: 1 or 2 digits allowed\n"
            + "YEAR: 4 digits allowed\n";

    public static final FormatterList FORMATTER_LIST = FormatterList.getFormatterList();
    public static final RegexList REGEX_LIST = RegexList.getRegexList();
    /**
     * The formatter uses uuuu instead of yyyy to ensure a stricter formatting restriction for the year.
     * The strict ResolverStyle is used to prevent non-existent dates from being added.
     */
    private static final DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN)
            .withResolverStyle(ResolverStyle.STRICT);

    public final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A date with valid format.
     */
    public Date(String date) {
        requireNonNull(date);
        String standardDate = standardizeDate(date);
        checkArgument(isValidDateByRegex(standardDate), MESSAGE_CONSTRAINTS_FORMAT);
        LocalDate parsedDate = FORMATTER_LIST.validateDateWithFormatters(standardDate);
        if (parsedDate == null) {
            // may change to DateTimeParseException
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS_FORMAT);
        }
        this.date = parsedDate;
    }

    /**
     * Constructs a Date object that is initialized to today's date.
     *
     * This is used when the user did not input any date.
     */
    public Date() {
        LocalDate today = LocalDate.now();
        String todayText = today.format(defaultFormatter);
        this.date = LocalDate.parse(todayText, defaultFormatter);;
    }

    /**
     * Returns a string that replaces all whitespaces within a date string to a single whitespace.
     * @param date date string
     * @return date string that has been standardized
     */
    private String standardizeDate(String date) {
        // Remove trailing whitespaces or multiple whitespaces between chars to a single whitespace
        return date.trim().replaceAll("\\s+", " ");
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDateByRegex(String test) {
        return REGEX_LIST.isValidDateByRegex(test);
    }

    /**
     * Obtains the day of week.
     * Example: 21/08/2022 is a Friday
     * @return string representing the day of week
     */
    public String getDayString() {
        // Convert "SATURDAY" to "Saturday"
        String dayString = date.getDayOfWeek().toString();
        dayString = dayString.charAt(0) + dayString.substring(1).toLowerCase();
        return dayString;
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

    /**
     * Computes and returns the Date that is {@code days} days before this Date.
     * @param days the number of days
     * @return {@code Date} with date value of {@code days} days before the current date value.
     */
    public Date getPreviousDaysDate(int days) {
        String dateString = this.date.minusDays(days).format(defaultFormatter);
        return new Date(dateString);
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
        return date.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN));
    }

}
