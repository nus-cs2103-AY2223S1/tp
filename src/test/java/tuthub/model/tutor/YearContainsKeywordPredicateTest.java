package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import tuthub.testutil.TutorBuilder;

public class YearContainsKeywordPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        YearContainsKeywordsPredicate firstPredicate = new YearContainsKeywordsPredicate(firstPredicateKeywordList);
        YearContainsKeywordsPredicate secondPredicate = new YearContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        YearContainsKeywordsPredicate firstPredicateCopy =
                new YearContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tutor -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_yearContainsKeywords_returnsTrue() {
        // One keyword
        YearContainsKeywordsPredicate predicate =
                new YearContainsKeywordsPredicate(Collections.singletonList("3"));
        assertTrue(predicate.test(new TutorBuilder().withYear("3").build()));
    }

    @Test
    public void test_yearDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        YearContainsKeywordsPredicate predicate =
                new YearContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TutorBuilder().withYear("3").build()));

        // Non-matching keyword
        predicate = new YearContainsKeywordsPredicate(Arrays.asList("3"));
        assertFalse(predicate.test(new TutorBuilder().withYear("2").build()));

        // Keywords match phone and email, but does not match year
        predicate = new YearContainsKeywordsPredicate(Arrays.asList("99999999", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new TutorBuilder().withName("Alice").withPhone("99999999")
                .withEmail("alice@email.com").withYear("3").build()));
    }
}
