package bookface.testutil;

import static bookface.commons.util.Date.DATE_FORMAT;

import java.text.ParseException;
import java.util.Date;

/**
 * A utility class containing a typical date {@code Date} object to be used in tests.
 */
public class TypicalDates {
    public static final String TYPICAL_DATE_STRING = "2022-08-08";
    public static final Date TYPICAL_DATE;

    static {
        try {
            TYPICAL_DATE = DATE_FORMAT.parse(TYPICAL_DATE_STRING);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
