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
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        CompanyContainsKeywordsPredicate predicate =
                new CompanyContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Alice Bob").build()));

        // Multiple keywords
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Alice Bob").build()));

        // Only one matching keyword
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CompanyContainsKeywordsPredicate predicate = new CompanyContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new InternshipBuilder().withCompany("Alice").build()));

        // Non-matching keyword
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new InternshipBuilder().withCompany("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new InternshipBuilder().withCompany("Alice").withLink("12345")
                .withDescription("alice@email.com").withAppliedDate("5 Oct 2022").build()));

    }
}
