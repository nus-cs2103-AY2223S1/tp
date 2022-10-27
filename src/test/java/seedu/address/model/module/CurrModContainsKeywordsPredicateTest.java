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
        // One keyword
        CurrModContainsKeywordsPredicate predicate = new CurrModContainsKeywordsPredicate("Alice");
        assertTrue(predicate.test(new PersonBuilder().withCurrentModules("Alice").build()));

        // Multiple keywords
        predicate = new CurrModContainsKeywordsPredicate("Alice");
        assertTrue(predicate.test(new PersonBuilder().withCurrentModules("Alice", "Bob").build()));

        // Only one matching keyword
        predicate = new CurrModContainsKeywordsPredicate("Carol");
        assertTrue(predicate.test(new PersonBuilder().withCurrentModules("Bob", "Carol").build()));

        // Mixed-case keywords
        predicate = new CurrModContainsKeywordsPredicate("ALIce");
        assertTrue(predicate.test(new PersonBuilder().withCurrentModules("Alice", "Bob").build()));
    }

    @Test
    public void test_currModDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CurrModContainsKeywordsPredicate predicate = new CurrModContainsKeywordsPredicate("");
        assertFalse(predicate.test(new PersonBuilder().withCurrentModules("Alice").build()));

        // Non-matching keyword
        predicate = new CurrModContainsKeywordsPredicate("Carol");
        assertFalse(predicate.test(new PersonBuilder().withCurrentModules("Alice").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new CurrModContainsKeywordsPredicate("Alice");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withGithub("alice").withTags("Friend")
                .withCurrentModules("Hello", "Bye").build()));
    }
}
