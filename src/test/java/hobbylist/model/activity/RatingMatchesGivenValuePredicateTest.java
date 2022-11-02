package hobbylist.model.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import hobbylist.testutil.ActivityBuilder;

public class RatingMatchesGivenValuePredicateTest {

    @Test
    public void equals() {
        int firstPredicateRatingValue = 1;
        int secondPredicateRatingValue = 2;

        RatingMatchesGivenValuePredicate firstPredicate =
                new RatingMatchesGivenValuePredicate(firstPredicateRatingValue);
        RatingMatchesGivenValuePredicate secondPredicate =
                new RatingMatchesGivenValuePredicate(secondPredicateRatingValue);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        RatingMatchesGivenValuePredicate firstPredicateCopy =
                new RatingMatchesGivenValuePredicate(firstPredicateRatingValue);
        assertEquals(firstPredicate, firstPredicateCopy);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different activity -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_ratingMatchesGivenValue_returnsTrue() {
        // Lower bound
        RatingMatchesGivenValuePredicate predicate =
                new RatingMatchesGivenValuePredicate(1);
        assertTrue(predicate.test(new ActivityBuilder().withRating(1).build()));

        // Upper bound
        predicate = new RatingMatchesGivenValuePredicate(5);
        assertTrue(predicate.test(new ActivityBuilder().withRating(5).build()));
    }

    @Test
    public void test_ratingMatchesGivenValue_returnsFalse() {
        // Non-matching rating
        RatingMatchesGivenValuePredicate predicate =
                new RatingMatchesGivenValuePredicate(1);
        assertFalse(predicate.test(new ActivityBuilder().withRating(2).build()));

        // Not rated activity
        predicate = new RatingMatchesGivenValuePredicate(1);
        assertFalse(predicate.test(new ActivityBuilder().withName("Battlefield 4").build()));

        // Default value -> always returns false
        predicate = new RatingMatchesGivenValuePredicate(-1);
        assertFalse(predicate.test(new ActivityBuilder().withRating(2).build()));

        predicate = new RatingMatchesGivenValuePredicate(-1);
        assertFalse(predicate.test(new ActivityBuilder().withName("Battlefield 4").build()));
    }
}
