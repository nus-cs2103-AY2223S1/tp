package foodwhere.model.review.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ReviewsComparatorListTest {

    @Test
    public void getCriteria_generalTesting_success() {
        String nameCriteria = "name (0 to 9, then A to Z)";
        assertEquals(nameCriteria, ReviewsComparatorList.NAME.getCriteria());

        String reversedCriteria = "name (Z to A, then 9 to 0)";
        assertEquals(reversedCriteria, ReviewsComparatorList.REVERSEDNAME.getCriteria());

        String dateCriteria = "date (Oldest to Newest)";
        assertEquals(dateCriteria, ReviewsComparatorList.DATE.getCriteria());

        String reversedDateCriteria = "date (Newest to Oldest)";
        assertEquals(reversedDateCriteria, ReviewsComparatorList.REVERSEDDATE.getCriteria());

        String ratingCriteria = "rating (Lowest to Highest)";
        assertEquals(ratingCriteria, ReviewsComparatorList.RATING.getCriteria());

        String reversedRatingCriteria = "rating (Highest to Lowest)";
        assertEquals(reversedRatingCriteria, ReviewsComparatorList.REVERSEDRATING.getCriteria());
    }

    @Test
    public void getComparator_generalTesting_success() {
        assertTrue(new NameComparator().equals(ReviewsComparatorList.NAME.getComparator()));
        assertTrue(new NameComparator().reversed().equals(ReviewsComparatorList.REVERSEDNAME.getComparator()));
        assertTrue(new DateComparator().equals(ReviewsComparatorList.DATE.getComparator()));
        assertTrue(new DateComparator().reversed().equals(ReviewsComparatorList.REVERSEDDATE.getComparator()));
        assertTrue(new RatingComparator().equals(ReviewsComparatorList.RATING.getComparator()));
        assertTrue(new RatingComparator().reversed().equals(ReviewsComparatorList.REVERSEDRATING.getComparator()));

        assertFalse(ReviewsComparatorList.NAME.getComparator().equals(new RatingComparator()));
        assertFalse(ReviewsComparatorList.DATE.getComparator().equals(new NameComparator()));
        assertFalse(ReviewsComparatorList.RATING.getComparator().equals(new DateComparator()));
    }
}
