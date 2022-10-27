package seedu.address.model.person;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * Represents a comparator to compare home visit time.
 */
public class HomeVisitComparator implements Comparator<HomeVisit> {

    /**
     * Compare the home visit time.
     *
     * @param homeVisit1 the first object to be compared.
     * @param homeVisit2 the second object to be compared.
     */
    public int compare(HomeVisit homeVisit1, HomeVisit homeVisit2) {
        LocalDateTime d1 = homeVisit1.homeVisitDateSlot.dateSlotTime;
        LocalDateTime d2 = homeVisit2.homeVisitDateSlot.dateSlotTime;

        if (d1.isBefore(d2)) {
            return -1;
        } else if (d1.isAfter(d2)) {
            return 1;
        } else {
            return 0;
        }

    }

}
