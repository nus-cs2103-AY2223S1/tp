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
        // One keyword
        PlanModContainsKeywordsPredicate predicate = new PlanModContainsKeywordsPredicate("Alice");
        assertTrue(predicate.test(new PersonBuilder().withPlannedModules("Alice").build()));

        // Multiple keywords
        predicate = new PlanModContainsKeywordsPredicate("Alice");
        assertTrue(predicate.test(new PersonBuilder().withPlannedModules("Alice", "Bob").build()));

        // Only one matching keyword
        predicate = new PlanModContainsKeywordsPredicate("Carol");
        assertTrue(predicate.test(new PersonBuilder().withPlannedModules("Bob", "Carol").build()));

        // Mixed-case keywords
        predicate = new PlanModContainsKeywordsPredicate("ALIce");
        assertTrue(predicate.test(new PersonBuilder().withPlannedModules("Alice", "Bob").build()));
    }

    @Test
    public void test_planModDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PlanModContainsKeywordsPredicate predicate = new PlanModContainsKeywordsPredicate("");
        assertFalse(predicate.test(new PersonBuilder().withPlannedModules("Alice").build()));

        // Non-matching keyword
        predicate = new PlanModContainsKeywordsPredicate("Carol");
        assertFalse(predicate.test(new PersonBuilder().withPlannedModules("Alice").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new PlanModContainsKeywordsPredicate("Alice");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withGithub("alice").withTags("Friend")
                .withPlannedModules("Hello", "Bye").build()));
    }
}
