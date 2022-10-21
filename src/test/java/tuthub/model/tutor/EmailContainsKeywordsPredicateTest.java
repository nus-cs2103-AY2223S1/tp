package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import tuthub.testutil.TutorBuilder;

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
        EmailContainsKeywordsPredicate firstPredicateCopy =
                new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tutor -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One keyword
        EmailContainsKeywordsPredicate predicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("john@example.com"));
        assertTrue(predicate.test(new TutorBuilder().withEmail("john@example.com").build()));

        // Mixed case keywords
        predicate = new EmailContainsKeywordsPredicate(Collections.singletonList("jOhn@eXample.cOm"));
        assertTrue(predicate.test(new TutorBuilder().withEmail("john@example.com").build()));

        // Partial keyword
        predicate = new EmailContainsKeywordsPredicate(Collections.singletonList("jOhn"));
        assertTrue(predicate.test(new TutorBuilder().withEmail("john@example.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnFalse() {
        // Zero keywords
        EmailContainsKeywordsPredicate predicate =
                new EmailContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TutorBuilder().withEmail("john@example.com").build()));

        // Non-matching keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("tom@example.com"));
        assertFalse(predicate.test(new TutorBuilder().withEmail("john@example.com").build()));

        // Keywords match phone and year, but does not match email
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("99999999", "3", "Main", "Street"));
        assertFalse(predicate.test(new TutorBuilder().withPhone("99999999")
                .withEmail("alice@email.com").withYear("3").build()));
    }
}
