package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

class TitleContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TitleContainsKeywordsPredicate firstPredicate = new TitleContainsKeywordsPredicate(firstPredicateKeywordList);
        TitleContainsKeywordsPredicate secondPredicate = new TitleContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TitleContainsKeywordsPredicate firstPredicateCopy =
                new TitleContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different profile -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_titleContainsKeywords_returnsTrue() {
        // One keyword
        TitleContainsKeywordsPredicate predicate =
                new TitleContainsKeywordsPredicate(Collections.singletonList("Formal"));
        assertTrue(predicate.test(new EventBuilder().withTitle("Formal Dinner").build()));

        // Multiple keywords
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Formal", "Dinner"));
        assertTrue(predicate.test(new EventBuilder().withTitle("Formal Dinner").build()));

        // Only one matching keyword
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Formal", "Dinner"));
        assertTrue(predicate.test(new EventBuilder().withTitle("Formal Lunch").build()));

        // Mixed-case keywords
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("foRmAl", "diNneR"));
        assertTrue(predicate.test(new EventBuilder().withTitle("Formal Dinner").build()));
    }

    @Test
    public void test_titleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TitleContainsKeywordsPredicate predicate = new TitleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EventBuilder().withTitle("Formal Dinner").build()));

        // Non-matching keyword
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Lunch"));
        assertFalse(predicate.test(new EventBuilder().withTitle("Formal Dinner").build()));

        // Keywords match dates but does not match title
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("01/09/2022", "02/09/2022"));
        assertFalse(predicate.test(new EventBuilder().withTitle("Formal Dinner")
                .withStartDateTime("01/09/2022").withEndDateTime("02/09/2022").build()));
    }
}
