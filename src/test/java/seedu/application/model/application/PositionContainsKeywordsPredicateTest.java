package seedu.application.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.application.testutil.ApplicationBuilder;

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

        // different Application -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_positionContainsKeywords_returnsTrue() {
        // One keyword
        PositionContainsKeywordsPredicate predicate =
                new PositionContainsKeywordsPredicate(Collections.singletonList("Software"));
        assertTrue(predicate.test(new ApplicationBuilder().withPosition("Software engineer").build()));

        // Multiple keywords
        predicate = new PositionContainsKeywordsPredicate(Arrays.asList("Software", "engineer"));
        assertTrue(predicate.test(new ApplicationBuilder().withPosition("Software engineer").build()));

        // Only one matching keyword
        predicate = new PositionContainsKeywordsPredicate(Arrays.asList("engineer", "AI"));
        assertTrue(predicate.test(new ApplicationBuilder().withPosition("Software engineer").build()));

        // Mixed-case keywords
        predicate = new PositionContainsKeywordsPredicate(Arrays.asList("eNGinEer", "sOfTWarE"));
        assertTrue(predicate.test(new ApplicationBuilder().withPosition("Software engineer").build()));
    }

    @Test
    public void test_positionDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PositionContainsKeywordsPredicate predicate = new PositionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ApplicationBuilder().withPosition("Software engineer").build()));

        // Non-matching keyword
        predicate = new PositionContainsKeywordsPredicate(Arrays.asList("mechanical"));
        assertFalse(predicate.test(new ApplicationBuilder().withPosition("Software engineer").build()));

        // Keywords match contact, company, email and date, but does not match position
        predicate = new PositionContainsKeywordsPredicate(Arrays.asList("87654321", "jane.street@email.com", "jane",
                "street", "2020-12-31"));
        assertFalse(predicate.test(new ApplicationBuilder().withCompany("Jane Street").withContact("87654321")
                .withEmail("jane.street@email.com").withPosition("software engineer").withDate("2020-12-31").build()));
    }
}
