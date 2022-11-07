package eatwhere.foodguide.model.eatery;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.testutil.EateryBuilder;

public class LocationContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("firstLocation");
        List<String> secondPredicateKeywordList = Arrays.asList("firstLocation", "secondLocation");

        LocationContainsKeywordsPredicate firstPredicate =
                new LocationContainsKeywordsPredicate(firstPredicateKeywordList);
        LocationContainsKeywordsPredicate secondPredicate =
                new LocationContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        LocationContainsKeywordsPredicate firstPredicateCopy =
                new LocationContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different eatery -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_locationContainsKeywords_returnsTrue() {
        // One keyword
        LocationContainsKeywordsPredicate predicate =
                new LocationContainsKeywordsPredicate(Collections.singletonList("NUS"));
        assertTrue(predicate.test(new EateryBuilder().withLocation("NUS Computing").build()));

        // Multiple keywords
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("NUS", "Computing"));
        assertTrue(predicate.test(new EateryBuilder().withLocation("NUS Computing").build()));

        // Only one matching keyword
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Computing", "Science"));
        assertTrue(predicate.test(new EateryBuilder().withLocation("NUS Science").build()));

        // Mixed-case keywords
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Nus", "COMPUTING"));
        assertTrue(predicate.test(new EateryBuilder().withLocation("NUS Computing").build()));
    }

    @Test
    public void test_locationDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        LocationContainsKeywordsPredicate predicate = new LocationContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EateryBuilder().withLocation("NUS").build()));

        // Non-matching keyword
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Science"));
        assertFalse(predicate.test(new EateryBuilder().withLocation("NUS Computing").build()));

        // Keywords match other fields, but does not match location
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("$$", "Chinese", "Eatery"));
        assertFalse(predicate.test(new EateryBuilder().withName("Eatery").withPrice("$$")
                .withCuisine("Chinese").withLocation("NUS").build()));
    }
}
