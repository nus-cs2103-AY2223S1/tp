package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class EmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        EmailContainsKeywordsPredicate firstPredicate = new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        EmailContainsKeywordsPredicate secondPredicate = new EmailContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailContainsKeywordsPredicate firstPredicateCopy = new EmailContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // one full matching keyword
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(
                Collections.singletonList("alice@example.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("alice@example.com").build()));

        // one matching substrings
        predicate = new EmailContainsKeywordsPredicate(Collections.singletonList("alice")); // local
        assertTrue(predicate.test(new PersonBuilder().withEmail("alice@example.com").build()));
        predicate = new EmailContainsKeywordsPredicate(Collections.singletonList("example")); // domain
        assertTrue(predicate.test(new PersonBuilder().withEmail("alice@example.com").build()));
        predicate = new EmailContainsKeywordsPredicate(Collections.singletonList("ce@ex")); // cross over
        assertTrue(predicate.test(new PersonBuilder().withEmail("alice@example.com").build()));

        // Multiple matching substring
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("alice", "example", ".com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("alice@example.com").build()));

        // Only one matching keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("bob", "example", ".edu"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("alice@example.com").build()));

        // Mixed-case keywords
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("bOb", "eXaMple", ".edU"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("alice@example.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withEmail("alice@example.com").build()));

        // Non-matching keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("nus.edu.com"));
        assertFalse(predicate.test(new PersonBuilder().withEmail("alice@example.com").build()));

        // Keywords match phone, address and name, but does not match email
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("12345", "Main", "Street", "Alice"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("ecila@email.com").withAddress("Main Street").build()));
    }
}
