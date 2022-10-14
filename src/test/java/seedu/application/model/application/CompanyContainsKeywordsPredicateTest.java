package seedu.application.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.application.testutil.ApplicationBuilder;

public class CompanyContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        CompanyContainsKeywordsPredicate firstPredicate =
                new CompanyContainsKeywordsPredicate(firstPredicateKeywordList);
        CompanyContainsKeywordsPredicate secondPredicate =
                new CompanyContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CompanyContainsKeywordsPredicate firstPredicateCopy =
                new CompanyContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different Application -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_companyContainsKeywords_returnsTrue() {
        // One keyword
        CompanyContainsKeywordsPredicate predicate =
                new CompanyContainsKeywordsPredicate(Collections.singletonList("Jane"));
        assertTrue(predicate.test(new ApplicationBuilder().withCompany("Jane Street").build()));

        // Multiple keywords
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("Jane", "Street"));
        assertTrue(predicate.test(new ApplicationBuilder().withCompany("Jane Street").build()));

        // Only one matching keyword
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("Street", "Meta"));
        assertTrue(predicate.test(new ApplicationBuilder().withCompany("Jane Street").build()));

        // Mixed-case keywords
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("sTreEt", "jaNE"));
        assertTrue(predicate.test(new ApplicationBuilder().withCompany("Jane Street").build()));
    }

    @Test
    public void test_companyDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CompanyContainsKeywordsPredicate predicate = new CompanyContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ApplicationBuilder().withCompany("Jane Street").build()));

        // Non-matching keyword
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("Jack"));
        assertFalse(predicate.test(new ApplicationBuilder().withCompany("Jane Street").build()));

        // Keywords match contact, position, email and date, but does not match company
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("87654321", "jane.street@email.com",
                "software", "engineer", "2020-12-31"));
        assertFalse(predicate.test(new ApplicationBuilder().withCompany("Jane Street").withContact("87654321")
                .withEmail("jane.street@email.com").withPosition("software engineer").withDate("2020-12-31").build()));
    }
}
