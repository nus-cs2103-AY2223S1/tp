package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

public class InternshipContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        InternshipContainsKeywordsPredicate firstPredicate = new InternshipContainsKeywordsPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        InternshipContainsKeywordsPredicate secondPredicate = new InternshipContainsKeywordsPredicate(
                secondPredicateKeywordList,
                secondPredicateKeywordList,
                secondPredicateKeywordList,
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        InternshipContainsKeywordsPredicate firstPredicateCopy = new InternshipContainsKeywordsPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different internship -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        InternshipContainsKeywordsPredicate predicate = new InternshipContainsKeywordsPredicate(
                Collections.singletonList("Alice"),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("Alice Bob").build()));

        // Multiple keywords
        predicate = new InternshipContainsKeywordsPredicate(
                Arrays.asList("Alice", "Bob"),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new InternshipContainsKeywordsPredicate(
                Arrays.asList("Bob", "Carol"),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new InternshipContainsKeywordsPredicate(
                Arrays.asList("aLIce", "bOB"),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsTrue() {
        // Zero keywords
        InternshipContainsKeywordsPredicate predicate = new InternshipContainsKeywordsPredicate(
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("Alice").build()));

        // Non-matching keyword
        predicate = new InternshipContainsKeywordsPredicate(
                Arrays.asList("Carol"),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());
        assertFalse(predicate.test(new InternshipBuilder().withCompanyName("Alice Bob").build()));

        // Keywords match role and status, but does not match name
        predicate = new InternshipContainsKeywordsPredicate(
                Arrays.asList("12345", "Cleaner", "ACCEPTED"),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());
        assertFalse(predicate.test(new InternshipBuilder().withCompanyName("Alice").withRole("Cleaner")
                .withStatus("ACCEPTED").build()));
    }
}
