package seedu.address.model.task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameOrTagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameOrTagContainsKeywordsPredicate firstPredicate = new NameOrTagContainsKeywordsPredicate(firstPredicateKeywordList);
        NameOrTagContainsKeywordsPredicate secondPredicate = new NameOrTagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameOrTagContainsKeywordsPredicate firstPredicateCopy = new NameOrTagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameOrTagContainsKeywords_returnsTrue() {
        // One keyword
        NameOrTagContainsKeywordsPredicate predicate = new NameOrTagContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // One keyword tag
        predicate = new NameOrTagContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Alice", "Bob").build()));

        // Multiple keywords tag
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Alice", "Bob").build()));

        // Only one matching keyword tag
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Alice", "Carol").build()));

        // Mixed-case keywords tag
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Alice", "Bob").build()));
    }

    @Test
    public void test_nameOrTagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameOrTagContainsKeywordsPredicate predicate = new NameOrTagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withModule("12345").build()));

        // Zero keywords
        predicate = new NameOrTagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("Alice").build()));

        // Non-matching keyword
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withTags("Alice", "Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withTags("Alice").withModule("12345").build()));
    }

    @Test
    public void toString_validInputs_correctResult() {
        // Zero keyword predicate
        NameOrTagContainsKeywordsPredicate predicate = new NameOrTagContainsKeywordsPredicate(Collections.emptyList());
        assertEquals("Names or tags containing ''", predicate.toString());
        // Regular test case
        predicate = new NameOrTagContainsKeywordsPredicate(Arrays.asList("taskName", "task2"));
        assertEquals("Names or tags containing [taskName, task2]", predicate.toString());
    }
}
