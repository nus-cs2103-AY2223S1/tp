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
        return d1.compareTo(d2);
    }

}
