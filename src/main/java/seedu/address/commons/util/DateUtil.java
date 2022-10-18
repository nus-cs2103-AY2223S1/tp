package seedu.address.commons.util;

import java.util.regex.Pattern;

/**
 * Converts a String to a LocalDate object and vice versa.
 */
public class DateUtil {
    // Regex pattern adapted from: https://www.baeldung.com/java-date-regular-expressions
    private static Pattern DATE_PATTERN = Pattern.compile(
            "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
            + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$");

    /**
     * Checks if {@dateString} can be parsed as a {@LocalDate} object.
     */
    public static boolean isLocalDateString(String dateString) {
        return dateString.matches(String.valueOf(DATE_PATTERN));
    }
}
