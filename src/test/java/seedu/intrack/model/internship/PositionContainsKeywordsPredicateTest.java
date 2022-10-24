package seedu.intrack.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.intrack.testutil.InternshipBuilder;

public class PositionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PositionContainsKeywordsPredicate firstPredicate =
                new PositionContainsKeywordsPredicate(firstPredicateKeywordList);
        PositionContainsKeywordsPredicate secondPredicate =
                new PositionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PositionContainsKeywordsPredicate firstPredicateCopy =
                new PositionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different internship -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_positionContainsKeywords_returnsTrue() {
        // One keyword
        PositionContainsKeywordsPredicate predicate =
                new PositionContainsKeywordsPredicate(Collections.singletonList("SWE"));
        assertTrue(predicate.test(new InternshipBuilder().withPosition("Junior SWE").build()));

        // Multiple keywords
        predicate = new PositionContainsKeywordsPredicate(Arrays.asList("SWE", "Junior"));
        assertTrue(predicate.test(new InternshipBuilder().withPosition("Junior SWE").build()));

        // Only one matching keyword
        predicate = new PositionContainsKeywordsPredicate(Arrays.asList("SWE", "Senior"));
        assertTrue(predicate.test(new InternshipBuilder().withPosition("Junior SWE").build()));

        // Mixed-case keywords
        predicate = new PositionContainsKeywordsPredicate(Arrays.asList("sWe", "juNiOR"));
        assertTrue(predicate.test(new InternshipBuilder().withPosition("Junior SWE").build()));
    }

    @Test
    public void test_positionDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PositionContainsKeywordsPredicate predicate = new PositionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new InternshipBuilder().withPosition("Data analyst").build()));

        // Non-matching keyword
        predicate = new PositionContainsKeywordsPredicate(Arrays.asList("Security"));
        assertFalse(predicate.test(new InternshipBuilder().withPosition("Data analyst").build()));

        // Keywords match phone, email and address, but does not match position
        predicate = new PositionContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new InternshipBuilder().withPosition("SWE").withPhone("12345")
                .withEmail("alice@email.com").withWebsite("https://careers.google.com/").build()));
    }
}
