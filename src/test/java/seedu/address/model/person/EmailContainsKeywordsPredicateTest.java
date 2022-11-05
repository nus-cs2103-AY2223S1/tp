package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;


class EmailContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        EmailContainsKeywordsPredicate firstPredicate =
                new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        EmailContainsKeywordsPredicate secondPredicate =
                new EmailContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailContainsKeywordsPredicate firstPredicateCopy =
                new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        EmailContainsKeywordsPredicate predicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("alice"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("alice@gmail.com").build()));

        // Multiple keywords
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("alice", "bob"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("alice@gmail.com").build()));

        // Only one matching keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("bob", "carol"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("carol@gmail.com").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EmailContainsKeywordsPredicate predicate =
                 new EmailContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withEmail("alice@gmail.com").build()));

        // Non-matching keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withEmail("alice@gmail.com").build()));

        // Keywords match phone and address, but does not match email
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("12345", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
