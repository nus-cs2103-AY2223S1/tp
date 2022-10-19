package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SortByRatingComparatorTest {

    @Test
    public void equals() {
        String firstComparatorOrder = "a";
        String secondComparatorOrder = "d";

        SortByRatingComparator firstComparator =
                new SortByRatingComparator(firstComparatorOrder);
        SortByRatingComparator secondComparator =
                new SortByRatingComparator(secondComparatorOrder);

        // same object -> returns true
        assertTrue(firstComparator.equals(firstComparator));

        // same values -> return true
        SortByRatingComparator firstComparatorCopy =
                new SortByRatingComparator(firstComparatorOrder);
        assertTrue(firstComparator.equals(firstComparatorCopy));

        // different types -> returns false
        assertFalse(firstComparator.equals(1));

        // null -> returns false
        assertFalse(firstComparator.equals(null));

        // different order -> returns false
        assertFalse(firstComparator.equals(secondComparator));
    }

}
