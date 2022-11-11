package tracko.commons.util;

import java.time.format.DateTimeFormatter;

/**
 * A class for formatting LocalDateTime.
 */
public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy <HH:mm>");

    public static DateTimeFormatter getFormat() {
        return DATE_TIME_FORMATTER;
    }
}
