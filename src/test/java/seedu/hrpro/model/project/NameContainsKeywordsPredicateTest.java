package seedu.hrpro.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.hrpro.testutil.ProjectBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ProjectNameContainsKeywordsPredicate firstPredicate =
                new ProjectNameContainsKeywordsPredicate(firstPredicateKeywordList);
        ProjectNameContainsKeywordsPredicate secondPredicate =
                new ProjectNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ProjectNameContainsKeywordsPredicate firstPredicateCopy =
                new ProjectNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different project -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        ProjectNameContainsKeywordsPredicate predicate =
                new ProjectNameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new ProjectBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new ProjectNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new ProjectBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new ProjectNameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new ProjectBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new ProjectNameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new ProjectBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ProjectNameContainsKeywordsPredicate predicate =
                new ProjectNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ProjectBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new ProjectNameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new ProjectBuilder().withName("Alice Bob").build()));

        // Keywords match budget and deadline, but does not match name
        predicate = new ProjectNameContainsKeywordsPredicate(Arrays.asList("12345", "2022-01-01", "Main", "Street"));
        assertFalse(predicate.test(new ProjectBuilder().withName("Alice").withBudget("12345")
                .withDeadline("2022-01-01").build()));
    }
}
