package bookface.commons.util;

import java.util.concurrent.TimeUnit;

/**
 * A container for Date-specific utility functions
 */
public class Date {
    public static java.util.Date getFourteenDaysLaterDate() {
        return new java.util.Date(new java.util.Date().getTime() + TimeUnit.DAYS.toMillis(14));
    }
}
