package seedu.address.model.person;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * Represents a comparator to compare date and time.
 */
public class DateTimeComparator implements Comparator<DateTime> {

    /**
     * Compare the home visits' date and time.
     * @param dateTime1 the first object to be compared.
     * @param dateTime2 the second object to be compared.
     */
    public int compare(DateTime dateTime1, DateTime dateTime2) {
        LocalDateTime d1 = dateTime1.dateTime;
        LocalDateTime d2 = dateTime2.dateTime;
        if (d1.isBefore(d2)) {
            return -1;
        } else if (d1.isAfter(d2)) {
            return 1;
        } else {
            return 0;
        }
    }

}


