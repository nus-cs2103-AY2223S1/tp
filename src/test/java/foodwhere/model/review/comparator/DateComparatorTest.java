package foodwhere.model.review.comparator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foodwhere.model.review.Review;
import foodwhere.model.review.ReviewBuilder;
import foodwhere.testutil.TypicalReviews;

public class DateComparatorTest {

    final DateComparator testComparator = new DateComparator();

    @Test
    public void compare_generalTesting_success() {
        // A < B -> return < 0
        Review reviewA = new ReviewBuilder().withDate("21/10/2022").build();
        Review reviewB = new ReviewBuilder().withDate("25/10/2022").build();
        int test1 = testComparator.compare(reviewA, reviewB);
        assertTrue(test1 < 0);

        // A = B -> return 0
        int test2 = testComparator.compare(reviewA, reviewA);
        assertTrue(test2 == 0);

        // A > B -> return > 0
        int test3 = testComparator.compare(reviewB, reviewA);
        assertTrue(test3 > 0);

        // testing reversed comparator
        // A < B -> return > 0
        int test4 = testComparator.reversed().compare(reviewA, reviewB);
        assertTrue(test4 > 0);

        // A = B -> return 0
        int test5 = testComparator.reversed().compare(reviewB, reviewB);
        assertTrue(test5 == 0);

        // A > B -> return < 0
        int test6 = testComparator.reversed().compare(reviewB, reviewA);
        assertTrue(test6 < 0);
    }

    @Test
    public void compare_nullReviews_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> testComparator.compare(null, null));
        assertThrows(NullPointerException.class, () -> testComparator.compare(TypicalReviews.ALICE, null));
        assertThrows(NullPointerException.class, () -> testComparator.compare(null, TypicalReviews.ALICE));
    }

    @Test
    public void equals_generalTesting_success() {
        // same object -> return true
        assertTrue(testComparator.equals(testComparator));

        // null -> return false
        assertFalse(testComparator.equals(null));

        // different object -> return false;
        assertFalse(testComparator.equals(2));

        // same type of object -> return true;
        DateComparator secondComparator = new DateComparator();
        assertTrue(testComparator.equals(secondComparator));
    }
}
