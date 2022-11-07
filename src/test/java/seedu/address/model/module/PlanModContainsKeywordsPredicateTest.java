package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PlanModContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeywordList = "first";
        String secondPredicateKeywordList = "second";

        PlanModContainsKeywordsPredicate firstPredicate =
                new PlanModContainsKeywordsPredicate(firstPredicateKeywordList);
        PlanModContainsKeywordsPredicate secondPredicate =
                new PlanModContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PlanModContainsKeywordsPredicate firstPredicateCopy =
                new PlanModContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_planModContainsKeywords_returnsTrue() {
        // One module
        PlanModContainsKeywordsPredicate predicate = new PlanModContainsKeywordsPredicate("CS2100");
        assertTrue(predicate.test(new PersonBuilder().withPlannedModules("CS2100").build()));

        // Multiple modules
        predicate = new PlanModContainsKeywordsPredicate("CS2100");
        assertTrue(predicate.test(new PersonBuilder().withPlannedModules("CS2100", "CS2101").build()));
    }

    @Test
    public void test_planModDoesNotContainKeywords_returnsFalse() {
        // Zero modules
        PlanModContainsKeywordsPredicate predicate = new PlanModContainsKeywordsPredicate("");
        assertFalse(predicate.test(new PersonBuilder().withPlannedModules("CS2100").build()));

        // Non-matching module
        predicate = new PlanModContainsKeywordsPredicate("CS2101");
        assertFalse(predicate.test(new PersonBuilder().withPlannedModules("CS2100").build()));

        // Invalid Module
        predicate = new PlanModContainsKeywordsPredicate("cs2101");
        assertFalse(predicate.test(new PersonBuilder().withPlannedModules("CS2100").build()));
    }
}
