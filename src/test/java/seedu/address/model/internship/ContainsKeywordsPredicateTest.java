package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

public class ContainsKeywordsPredicateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ContainsKeywordsPredicate(null));
    }

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ContainsKeywordsPredicate firstPredicate =
                new ContainsKeywordsPredicate(firstPredicateKeywordList);
        ContainsKeywordsPredicate secondPredicate =
                new ContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ContainsKeywordsPredicate firstPredicateCopy =
                new ContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_containsKeywords_returnsTrue() {
        // One keyword
        ContainsKeywordsPredicate predicate =
                new ContainsKeywordsPredicate(Collections.singletonList("Google"));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Google Tiktok").build()));
        assertTrue(predicate.test(new InternshipBuilder().withTags("Google Tiktok").build()));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Google Tiktok").withTags(
                "Google Tiktok").build()));

        // Multiple keywords
        predicate = new ContainsKeywordsPredicate(Arrays.asList("Google", "Tiktok"));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Google Tiktok").build()));
        assertTrue(predicate.test(new InternshipBuilder().withTags("Google Tiktok").build()));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Tiktok").withTags("Google").build()));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Google").withTags("Tiktok").build()));

        // Only one matching keyword
        predicate = new ContainsKeywordsPredicate(Arrays.asList("Tiktok", "Meta"));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Google Meta").build()));
        assertTrue(predicate.test(new InternshipBuilder().withTags("Google Meta").build()));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Meta").withTags("Google").build()));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Google").withTags("Meta").build()));

        // Mixed-case keywords
        predicate = new ContainsKeywordsPredicate(Arrays.asList("GOOgle", "tIKtok"));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Google Tiktok").build()));
        assertTrue(predicate.test(new InternshipBuilder().withTags("Google Tiktok").build()));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Google Tiktok").withTags(
                "Google Tiktok").build()));

        // Partial keyword
        predicate = new ContainsKeywordsPredicate(Arrays.asList("GOO"));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Google Tiktok").build()));
        assertTrue(predicate.test(new InternshipBuilder().withTags("Google Tiktok").build()));
        assertTrue(predicate.test(new InternshipBuilder().withCompany("Google Tiktok").withTags(
                "Google Tiktok").build()));

    }

    @Test
    public void test_doesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new InternshipBuilder().withCompany("Google").build()));
        assertFalse(predicate.test(new InternshipBuilder().withTags("frontend").build()));
        assertFalse(predicate.test(new InternshipBuilder().withCompany("Google").withTags("frontend").build()));

        // Non-matching keyword
        predicate = new ContainsKeywordsPredicate(Arrays.asList("Meta"));
        assertFalse(predicate.test(new InternshipBuilder().withCompany("Google Tiktok").build()));
        assertFalse(predicate.test(new InternshipBuilder().withTags("Google Tiktok").build()));
        assertFalse(predicate.test(new InternshipBuilder().withCompany("Google").withTags("Tiktok").build()));

        // Keywords match link, description and applied date, but does not match company or tags
        predicate = new ContainsKeywordsPredicate(Arrays.asList("careers.google.com",
                "Internship", "23/10/2022"));
        assertFalse(predicate.test(new InternshipBuilder().withCompany("Google").withLink("careers.google.com")
                .withDescription("Internship").withAppliedDate("23/10/2022").withTags("frontend").build()));

    }
}
