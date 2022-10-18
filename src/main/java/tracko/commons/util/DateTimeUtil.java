package tracko.commons.util;

import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy <HH:mm>");

    public static DateTimeFormatter getFormat() {
        return dateTimeFormatter;
    }
}
