package seedu.workbook.model.internship;

import java.util.Comparator;

/**
 * Comparator used for sorting the Internship list by Date.
 */
public class InternshipComparator implements Comparator<Internship> {

    @Override
    public int compare(Internship o1, Internship o2) {
        DateTime o1DateTime = o1.getDateTime();
        DateTime o2DateTime = o2.getDateTime();

        if (o1DateTime.equals(DateTime.EMPTY_DATETIME)) {
            return o2DateTime.equals(DateTime.EMPTY_DATETIME)
                    ? 0
                    : o2DateTime.isPast()
                            ? -1
                            : 1;
        }

        if (o1DateTime.isPast()) {
            return o2DateTime.equals(DateTime.EMPTY_DATETIME) || o2DateTime.isAfter(o1DateTime)
                    ? 1
                    : -1;
        }

        return (o2DateTime.equals(DateTime.EMPTY_DATETIME) || o2DateTime.isPast())
                    || o2DateTime.isAfter(o1DateTime)
                ? -1
                : 1;
    }
}
