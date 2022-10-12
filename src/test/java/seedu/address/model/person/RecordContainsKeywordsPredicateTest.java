package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class RecordContainsKeywordsPredicateTest {

    private Record recordWithKeywords = new Record("10-10-2000 1400", "Covid-19 Flu SARS");

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        RecordContainsKeywordsPredicate firstPredicate = new RecordContainsKeywordsPredicate(firstPredicateKeywordList);
        RecordContainsKeywordsPredicate secondPredicate = new RecordContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RecordContainsKeywordsPredicate firstPredicateCopy = new RecordContainsKeywordsPredicate(
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
    public void test_recordContainsKeywords_returnsTrue() {
        // One keyword
        RecordContainsKeywordsPredicate predicate = new RecordContainsKeywordsPredicate(
                Collections.singletonList("Flu"));
        assertTrue(predicate.test(recordWithKeywords));

        // Multiple keywords
        predicate = new RecordContainsKeywordsPredicate(Arrays.asList("Covid-19", "Flu"));
        assertTrue(predicate.test(recordWithKeywords));

        // Only one matching keyword
        predicate = new RecordContainsKeywordsPredicate(Arrays.asList("Covid-19", "H1N1"));
        assertTrue(predicate.test(recordWithKeywords));

        // Mixed-case keywords
        predicate = new RecordContainsKeywordsPredicate(Arrays.asList("cOVid-19", "h1n1"));
        assertTrue(predicate.test(recordWithKeywords));
    }

    @Test
    public void test_recordDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        RecordContainsKeywordsPredicate predicate = new RecordContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(recordWithKeywords));

        // Non-matching keyword
        predicate = new RecordContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(recordWithKeywords));
    }
}
