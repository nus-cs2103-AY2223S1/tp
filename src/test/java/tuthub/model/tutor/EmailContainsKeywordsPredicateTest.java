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
        // Small letter keyword
        EmailContainsKeywordsPredicate predicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("e1234567"));
        assertTrue(predicate.test(new TutorBuilder().withEmail("e1234567").build()));

        // Capital letter keyword
        predicate = new EmailContainsKeywordsPredicate(Collections.singletonList("E1234567"));
        assertTrue(predicate.test(new TutorBuilder().withEmail("E1234567").build()));

        // Partial keyword
        predicate = new EmailContainsKeywordsPredicate(Collections.singletonList("1234"));
        assertTrue(predicate.test(new TutorBuilder().withEmail("e1234567").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnFalse() {
        // Zero keywords
        EmailContainsKeywordsPredicate predicate =
                new EmailContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TutorBuilder().withEmail("e1234567").build()));

        // Non-matching keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("e7654321"));
        assertFalse(predicate.test(new TutorBuilder().withEmail("e1234567").build()));

        // Keywords match phone and year, but does not match email
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("99999999", "3", "Main", "Street"));
        assertFalse(predicate.test(new TutorBuilder().withPhone("99999999")
                .withEmail("e0000000").withYear("3").build()));
    }
}
