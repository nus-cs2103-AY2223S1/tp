package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import tuthub.testutil.TutorBuilder;

public class RatingContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        RatingContainsKeywordsPredicate firstPredicate =
                new RatingContainsKeywordsPredicate(firstPredicateKeywordList);
        RatingContainsKeywordsPredicate secondPredicate =
                new RatingContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RatingContainsKeywordsPredicate firstPredicateCopy =
                new RatingContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tutor -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_ratingContainsKeywords_returnsTrue() {
        // One keyword
        RatingContainsKeywordsPredicate predicate =
                new RatingContainsKeywordsPredicate(Collections.singletonList("2.5"));
        assertTrue(predicate.test(new TutorBuilder().withRating("2.5").build()));

        // Partial keyword
        predicate = new RatingContainsKeywordsPredicate(Collections.singletonList("2."));
        assertTrue(predicate.test(new TutorBuilder().withRating("2.5").build()));
    }

    @Test
    public void test_ratingDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        RatingContainsKeywordsPredicate predicate = new RatingContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TutorBuilder().withRating("2.5").build()));

        // Non-matching keyword
        predicate = new RatingContainsKeywordsPredicate(Arrays.asList("3.0"));
        assertFalse(predicate.test(new TutorBuilder().withRating("2.5").build()));
    }
}
