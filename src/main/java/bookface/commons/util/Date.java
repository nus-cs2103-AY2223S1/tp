package bookface.commons.util;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * A container for Date-specific utility functions
 */
public class Date {
    /**
     * The date format to use for {@code Storage}
     */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    static {
        DATE_FORMAT.setLenient(false);
    }

    /**
     * Returns the date fourteen days from now
     * @return the {@code Date} fourteen days later
     */
    public static java.util.Date getFourteenDaysLaterDate() {
        return new java.util.Date(new java.util.Date().getTime() + TimeUnit.DAYS.toMillis(14));
    }
}
