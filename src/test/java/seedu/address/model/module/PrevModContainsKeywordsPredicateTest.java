package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PrevModContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeywordList = "first";
        String secondPredicateKeywordList = "second";

        PrevModContainsKeywordsPredicate firstPredicate =
                new PrevModContainsKeywordsPredicate(firstPredicateKeywordList);
        PrevModContainsKeywordsPredicate secondPredicate =
                new PrevModContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PrevModContainsKeywordsPredicate firstPredicateCopy =
                new PrevModContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_prevModContainsKeywords_returnsTrue() {
        // One module
        PrevModContainsKeywordsPredicate predicate = new PrevModContainsKeywordsPredicate("CS2100");
        assertTrue(predicate.test(new PersonBuilder().withPreviousModules("CS2100").build()));

        // Multiple modules
        predicate = new PrevModContainsKeywordsPredicate("CS2100");
        assertTrue(predicate.test(new PersonBuilder().withPreviousModules("CS2100", "CS2101").build()));
    }

    @Test
    public void test_prevModDoesNotContainKeywords_returnsFalse() {
        // Zero modules
        PrevModContainsKeywordsPredicate predicate = new PrevModContainsKeywordsPredicate("");
        assertFalse(predicate.test(new PersonBuilder().withPreviousModules("CS2100").build()));

        // Non-matching module
        predicate = new PrevModContainsKeywordsPredicate("CS2101");
        assertFalse(predicate.test(new PersonBuilder().withPreviousModules("CS2100").build()));

        // Invalid Module
        predicate = new PrevModContainsKeywordsPredicate("cs2101");
        assertFalse(predicate.test(new PersonBuilder().withPreviousModules("CS2100").build()));
    }
}
