package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SortByTeachingNominationComparatorTest {

    @Test
    public void equals() {
        String firstComparatorOrder = "a";
        String secondComparatorOrder = "d";

        SortByTeachingNominationComparator firstComparator =
                new SortByTeachingNominationComparator(firstComparatorOrder);
        SortByTeachingNominationComparator secondComparator =
                new SortByTeachingNominationComparator(secondComparatorOrder);

        // same object -> returns true
        assertTrue(firstComparator.equals(firstComparator));

        // same values -> return true
        SortByTeachingNominationComparator firstComparatorCopy =
                new SortByTeachingNominationComparator(firstComparatorOrder);
        assertTrue(firstComparator.equals(firstComparatorCopy));

        // different types -> returns false
        assertFalse(firstComparator.equals(1));

        // null -> returns false
        assertFalse(firstComparator.equals(null));

        // different order -> returns false
        assertFalse(firstComparator.equals(secondComparator));
    }

}
