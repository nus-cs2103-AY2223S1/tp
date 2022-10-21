package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Converts a String to a LocalDate object and vice versa.
 */
public class DateUtil {
    // Regex pattern adapted from: https://www.baeldung.com/java-date-regular-expressions
    /**
     * This pattern checks that every day, month, year is valid.
     */
    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
                    + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$");

    // Regex pattern adapted from: https://www.baeldung.com/java-date-regular-expressions
    /**
     * This pattern checks that given string is of generic {@code LocalDate} pattern.
     */
    private static final Pattern GENERIC_PATTERN = Pattern.compile(
            "^\\d{4}-\\d{2}-\\d{2}$");

    /**
     * Checks if {@code dateString} can be parsed as a {@code LocalDate} object.
     */
    public static boolean isLocalDateString(String dateString) {
        return DATE_PATTERN.matcher(dateString).matches();
    }

    /**
     * Checks if {@code dateString} is of a correct generic {@code LocalDate} format, but may not be parsable.
     */
    public static boolean isGenericLocalDateString(String dateString) {
        return GENERIC_PATTERN.matcher(dateString).matches();
    }

    /**
     * Parses a {@code dateString} into a {@code LocalDate} object.
     */
    public static LocalDate getLocalDate(String dateString) {
        requireNonNull(dateString);

        return LocalDate.parse(dateString);
    }

    /**
     * Checks if the given {@code dateToCheck} is equal to today's system date and after today.
     */
    public static boolean dateIsEqualAndAfterToday(LocalDate dateToCheck) {
        requireNonNull(dateToCheck);

        return LocalDate.now().isEqual(dateToCheck) || LocalDate.now().isBefore(dateToCheck);
    }

    /**
     * Checks if the given {@code dateToCheck} is before today's date.
     */
    public static boolean dateIsBeforeToday(LocalDate dateToCheck) {
        requireNonNull(dateToCheck);

        return LocalDate.now().isAfter(dateToCheck);
    }

    /**
     * Returns a {@code LocalDate} object in {@code String} format.
     */
    public static String getLocalDateString(LocalDate date) {
        requireNonNull(date);

        return date.toString();
    }
}
