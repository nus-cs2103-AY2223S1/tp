package seedu.workbook.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.workbook.testutil.InternshipBuilder;

public class StageContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        StageContainsKeywordsPredicate firstPredicate = new StageContainsKeywordsPredicate(
                firstPredicateKeywordList);
        StageContainsKeywordsPredicate secondPredicate = new StageContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StageContainsKeywordsPredicate firstPredicateCopy = new StageContainsKeywordsPredicate(
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
    public void test_stageContainsKeywords_returnsTrue() {
        // One keyword
        StageContainsKeywordsPredicate predicate = new StageContainsKeywordsPredicate(
                Collections.singletonList("Interview"));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Technical Interview").build()));

        // Multiple keywords
        predicate = new StageContainsKeywordsPredicate(Arrays.asList("Technical", "Interview"));
        assertTrue(predicate.test(new InternshipBuilder().withStage("Technical Interview").build()));

        // Mixed-case keywords
        predicate = new StageContainsKeywordsPredicate(Arrays.asList("tEcHnicAl", "inTeRviEw"));
        assertTrue(predicate.test(new InternshipBuilder().withStage("Technical Interview").build()));
    }

    @Test
    public void test_companyDoesNotContainKeywords_returnsFalse() {

        // Non-matching keyword
        StageContainsKeywordsPredicate predicate = new StageContainsKeywordsPredicate(Arrays.asList("Assessment"));
        assertFalse(predicate.test(new InternshipBuilder().withStage("Phone Interview").build()));

        // Keywords match phone, email and address, but does not match company
        predicate = new StageContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com"));
        assertFalse(predicate.test(new InternshipBuilder().withStage("Interview")
                .withEmail("alice@email.com").build()));
    }
}
