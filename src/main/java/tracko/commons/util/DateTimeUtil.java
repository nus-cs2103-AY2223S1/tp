package tracko.commons.util;

import java.time.format.DateTimeFormatter;

/**
 * A class for formatting LocalDateTime.
 */
public class DateTimeUtil {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy <HH:mm>");

    public static DateTimeFormatter getFormat() {
        return dateTimeFormatter;
    }
}
