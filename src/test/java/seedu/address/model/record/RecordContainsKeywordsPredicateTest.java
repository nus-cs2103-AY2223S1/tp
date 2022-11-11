package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class RecordContainsKeywordsPredicateTest {

    private Record recordWithKeywords = new Record(LocalDateTime.parse("10-10-2000 1400",
            Record.DATE_FORMAT), "Covid-19 Flu SARS");

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        RecordContainsKeywordsPredicate firstPredicate = new RecordContainsKeywordsPredicate(
                firstPredicateKeywordList, firstPredicateKeywordList, "10-2022");
        RecordContainsKeywordsPredicate secondPredicate = new RecordContainsKeywordsPredicate(
                secondPredicateKeywordList, secondPredicateKeywordList, "12-2012");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RecordContainsKeywordsPredicate firstPredicateCopy = new RecordContainsKeywordsPredicate(
                firstPredicateKeywordList, firstPredicateKeywordList, "10-2022");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different record -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_recordFieldsContainsKeywords_returnsTrue() {
        List<String> nonSpecifiedKeyword = Arrays.asList();
        String nonSpecifiedDate = "";

        // One keyword
        RecordContainsKeywordsPredicate predicate = new RecordContainsKeywordsPredicate(
                Collections.singletonList("Flu"), nonSpecifiedKeyword, nonSpecifiedDate);
        assertTrue(predicate.test(recordWithKeywords));

        // Multiple keywords
        predicate = new RecordContainsKeywordsPredicate(
                Arrays.asList("Covid-19", "Flu"), nonSpecifiedKeyword, nonSpecifiedDate);
        assertTrue(predicate.test(recordWithKeywords));

        // Only one matching keyword
        predicate = new RecordContainsKeywordsPredicate(
                Arrays.asList("Covid-19", "H1N1"), nonSpecifiedKeyword, nonSpecifiedDate);
        assertTrue(predicate.test(recordWithKeywords));

        // Mixed-case keywords
        predicate = new RecordContainsKeywordsPredicate(
                Arrays.asList("cOVid-19", "h1n1"), nonSpecifiedKeyword, nonSpecifiedDate);
        assertTrue(predicate.test(recordWithKeywords));

        // Correct find date format
        predicate = new RecordContainsKeywordsPredicate(
                nonSpecifiedKeyword, nonSpecifiedKeyword, "10-2000");
        assertTrue(predicate.test(recordWithKeywords));
    }
}
