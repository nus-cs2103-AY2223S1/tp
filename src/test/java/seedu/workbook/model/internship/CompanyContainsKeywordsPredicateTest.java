package seedu.workbook.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.workbook.testutil.InternshipBuilder;

public class CompanyContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        CompanyContainsKeywordsPredicate firstPredicate = new CompanyContainsKeywordsPredicate(
                firstPredicateKeywordList);
        CompanyContainsKeywordsPredicate secondPredicate = new CompanyContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CompanyContainsKeywordsPredicate firstPredicateCopy = new CompanyContainsKeywordsPredicate(
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
    public void test_companyContainsKeywords_returnsTrue() {
        // One keyword
        CompanyContainsKeywordsPredicate predicate = new CompanyContainsKeywordsPredicate(
                Collections.singletonList("Jane"));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Jane Street").build()));

        // Multiple keywords
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("Jane", "Street"));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Jane Street").build()));


        // Mixed-case keywords
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("jAnE", "sTreeT"));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Jane Street").build()));
    }

    @Test
    public void test_companyDoesNotContainKeywords_returnsFalse() {


        // Non-matching keyword
        CompanyContainsKeywordsPredicate predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("Fairprice"));
        assertFalse(predicate.test(new InternshipBuilder().withCompany("Jane Street").build()));

        // Keywords match phone, email and address, but does not match company
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com"));
        assertFalse(predicate.test(new InternshipBuilder().withCompany("Meta")
                .withEmail("alice@email.com").build()));
    }
}
