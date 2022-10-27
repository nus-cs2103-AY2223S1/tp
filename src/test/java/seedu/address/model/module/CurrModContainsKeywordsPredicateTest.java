package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class CurrModContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeywordList = "first";
        String secondPredicateKeywordList = "second";

        CurrModContainsKeywordsPredicate firstPredicate =
                new CurrModContainsKeywordsPredicate(firstPredicateKeywordList);
        CurrModContainsKeywordsPredicate secondPredicate =
                new CurrModContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CurrModContainsKeywordsPredicate firstPredicateCopy =
                new CurrModContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_currModContainsKeywords_returnsTrue() {
        // One module
        CurrModContainsKeywordsPredicate predicate = new CurrModContainsKeywordsPredicate("CS2100");
        assertTrue(predicate.test(new PersonBuilder().withCurrentModules("CS2100").build()));

        // Multiple modules
        predicate = new CurrModContainsKeywordsPredicate("CS2100");
        assertTrue(predicate.test(new PersonBuilder().withCurrentModules("CS2100", "CS2101").build()));
    }

    @Test
    public void test_currModDoesNotContainKeywords_returnsFalse() {
        // Zero modules
        CurrModContainsKeywordsPredicate predicate = new CurrModContainsKeywordsPredicate("");
        assertFalse(predicate.test(new PersonBuilder().withCurrentModules("CS2100").build()));

        // Non-matching module
        predicate = new CurrModContainsKeywordsPredicate("CS2101");
        assertFalse(predicate.test(new PersonBuilder().withCurrentModules("CS2100").build()));

        // Invalid Module
        predicate = new CurrModContainsKeywordsPredicate("cs2101");
        assertFalse(predicate.test(new PersonBuilder().withCurrentModules("CS2100").build()));
    }
}
