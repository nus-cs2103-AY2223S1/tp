package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

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

        // different internship -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_companyContainsKeywords_returnsTrue() {
        // One keyword
        CompanyContainsKeywordsPredicate predicate =
                new CompanyContainsKeywordsPredicate(Collections.singletonList("Google"));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Google Tiktok").build()));

        // Multiple keywords
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("Google", "Tiktok"));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Google Tiktok").build()));

        // Only one matching keyword
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("Tiktok", "Meta"));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Google Meta").build()));

        // Mixed-case keywords
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("GOOgle", "tIKtok"));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Google Tiktok").build()));
    }

    @Test
    public void test_companyDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CompanyContainsKeywordsPredicate predicate = new CompanyContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new InternshipBuilder().withCompany("Google").build()));

        // Non-matching keyword
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("Meta"));
        assertFalse(predicate.test(new InternshipBuilder().withCompany("Google Tiktok").build()));

        // Keywords match link, description and applied date, but does not match company
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("careers.google.com",
                "Frontend", "23/10/2022"));
        assertFalse(predicate.test(new InternshipBuilder().withCompany("Google").withLink("careers.google.com")
                .withDescription("Frontend").withAppliedDate("23/10/2022").build()));

    }
}
