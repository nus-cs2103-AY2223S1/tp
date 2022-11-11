package eatwhere.foodguide.model.eatery;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.testutil.EateryBuilder;

public class PriceContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("$");
        List<String> secondPredicateKeywordList = Arrays.asList("$", "$$");

        PriceContainsKeywordsPredicate firstPredicate = new PriceContainsKeywordsPredicate(firstPredicateKeywordList);
        PriceContainsKeywordsPredicate secondPredicate =
                new PriceContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PriceContainsKeywordsPredicate firstPredicateCopy =
                new PriceContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different eatery -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        PriceContainsKeywordsPredicate predicate = new PriceContainsKeywordsPredicate(Collections.singletonList("$$"));
        assertTrue(predicate.test(new EateryBuilder().withPrice("$$").build()));

        // Multiple keywords
        predicate = new PriceContainsKeywordsPredicate(Arrays.asList("$$", "$"));
        assertTrue(predicate.test(new EateryBuilder().withPrice("$$").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PriceContainsKeywordsPredicate predicate = new PriceContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EateryBuilder().withPrice("$").build()));

        // Non-matching keyword
        predicate = new PriceContainsKeywordsPredicate(Arrays.asList("$$"));
        assertFalse(predicate.test(new EateryBuilder().withPrice("$").build()));

        // Keywords match all other fields, but does not match price
        predicate = new PriceContainsKeywordsPredicate(Arrays.asList("Chinese", "Eatery", "NUS"));
        assertFalse(predicate.test(new EateryBuilder().withName("Eatery").withPrice("$$")
                .withCuisine("Chinese").withLocation("NUS").build()));
    }
}
