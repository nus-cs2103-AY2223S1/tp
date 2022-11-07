package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class JobIdContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        JobIdContainsKeywordsPredicate firstPredicate = new JobIdContainsKeywordsPredicate(
                firstPredicateKeywordList);
        JobIdContainsKeywordsPredicate secondPredicate = new JobIdContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        JobIdContainsKeywordsPredicate firstPredicateCopy = new JobIdContainsKeywordsPredicate(
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
    public void test_jobIdContainsKeywords_returnsTrue() {
        // One keyword
        JobIdContainsKeywordsPredicate predicate = new JobIdContainsKeywordsPredicate(
                Collections.singletonList("JID1234"));
        assertTrue(predicate.test(new PersonBuilder().withId("JID1234").build()));

        // Only one matching keyword
        predicate = new JobIdContainsKeywordsPredicate(Arrays.asList("JID1234", "JID5678"));
        assertTrue(predicate.test(new PersonBuilder().withId("JID1234").build()));

        // Keywords are substrings
        predicate = new JobIdContainsKeywordsPredicate(Arrays.asList("1234", "JID"));
        assertTrue(predicate.test(new PersonBuilder().withId("JID1234").build()));

        // Mixed-case keywords
        predicate = new JobIdContainsKeywordsPredicate(Arrays.asList("jiD1234", "jId5678"));
        assertTrue(predicate.test(new PersonBuilder().withId("JID1234").build()));
    }

    @Test
    public void test_jobIdDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        JobIdContainsKeywordsPredicate predicate = new JobIdContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withId("JID1234").build()));

        // Non-matching keywords
        predicate = new JobIdContainsKeywordsPredicate(Arrays.asList("JID5678", "0JID12340"));
        assertFalse(predicate.test(new PersonBuilder().withId("JID1234").build()));


        // Keywords match name, phone, email and address, but does not match JobId
        predicate = new JobIdContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street", "Alice"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withId("JID1234").build()));
    }
}
