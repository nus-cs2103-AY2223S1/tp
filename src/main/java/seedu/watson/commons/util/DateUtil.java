package seedu.watson.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * @author {e1010101}-reused
 *          Utility class containing DateTime related methods
 */
public class DateUtil {

    private static final DateTimeFormatter parseFormatter =
        new DateTimeFormatterBuilder().appendPattern("[dd-MM-yyyy HH:mm]")
                                      .appendPattern("[dd/MM/yyyy HH:mm]")
                                      .appendPattern("[dd-MM-yyyy h:mm a]")
                                      .appendPattern("[dd MMM yyyy HH:mm]")
                                      .toFormatter();

    /**
     * Checks if a given String can be parsed into a DateTime object
     * @param test the String to be checked
     * @return true if the String can be parsed into a DateTime object, otherwise false
     */
    public static boolean isValidDateString(String test) {
        try {
            LocalDateTime.parse(test, parseFormatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isDateValid(LocalDateTime dateTime) {
        return !dateTime.isBefore(LocalDateTime.now());
    }
}
