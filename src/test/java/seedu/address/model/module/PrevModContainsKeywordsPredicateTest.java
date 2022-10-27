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
        // One keyword
        PrevModContainsKeywordsPredicate predicate = new PrevModContainsKeywordsPredicate("Alice");
        assertTrue(predicate.test(new PersonBuilder().withPreviousModules("Alice").build()));

        // Multiple keywords
        predicate = new PrevModContainsKeywordsPredicate("Alice");
        assertTrue(predicate.test(new PersonBuilder().withPreviousModules("Alice", "Bob").build()));

        // Only one matching keyword
        predicate = new PrevModContainsKeywordsPredicate("Carol");
        assertTrue(predicate.test(new PersonBuilder().withPreviousModules("Bob", "Carol").build()));

        // Mixed-case keywords
        predicate = new PrevModContainsKeywordsPredicate("ALIce");
        assertTrue(predicate.test(new PersonBuilder().withPreviousModules("Alice", "Bob").build()));
    }

    @Test
    public void test_prevModDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PrevModContainsKeywordsPredicate predicate = new PrevModContainsKeywordsPredicate("");
        assertFalse(predicate.test(new PersonBuilder().withPreviousModules("Alice").build()));

        // Non-matching keyword
        predicate = new PrevModContainsKeywordsPredicate("Carol");
        assertFalse(predicate.test(new PersonBuilder().withPreviousModules("Alice").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new PrevModContainsKeywordsPredicate("Alice");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withGithub("alice").withTags("Friend")
                .withPreviousModules("Hello", "Bye").build()));
    }
}
