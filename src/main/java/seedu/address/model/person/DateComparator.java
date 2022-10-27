package seedu.address.model.person;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Represents a comparator to compare date.
 */
public class DateComparator implements Comparator<Date> {
    /**
     * Compare the date.
     *
     * @param date1 the first object to be compared.
     * @param date2 the second object to be compared.
     */

    public int compare(Date date1, Date date2) {
        LocalDate d1 = date2.date;
        LocalDate d2 = date1.date;
        if (d1.isBefore(d2)) {
            return -1;
        } else if (d1.isAfter(d2)) {
            return 1;
        } else {
            return 0;
        }
    }

}
