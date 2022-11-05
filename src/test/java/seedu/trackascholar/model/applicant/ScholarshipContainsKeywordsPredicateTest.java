package seedu.trackascholar.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.testutil.ApplicantBuilder;

public class ScholarshipContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ScholarshipContainsKeywordsPredicate firstPredicate =
                new ScholarshipContainsKeywordsPredicate(firstPredicateKeywordList);
        ScholarshipContainsKeywordsPredicate secondPredicate =
                new ScholarshipContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ScholarshipContainsKeywordsPredicate firstPredicateCopy =
                new ScholarshipContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different applicant -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_scholarshipContainsKeywords_returnsTrue() {
        // One keyword
        ScholarshipContainsKeywordsPredicate predicate =
                new ScholarshipContainsKeywordsPredicate(Collections.singletonList("Sports"));
        assertTrue(predicate.test(new ApplicantBuilder().withScholarship("NUS Sports Scholarship").build()));

        // Multiple keywords
        predicate = new ScholarshipContainsKeywordsPredicate(Arrays.asList("Faculty", "Engineering"));
        assertTrue(predicate.test(new ApplicantBuilder()
                .withScholarship("Faculty of Engineering Scholarships").build()));

        // Only one matching keyword
        predicate = new ScholarshipContainsKeywordsPredicate(Arrays.asList("Sports", "Engineering"));
        assertTrue(predicate.test(new ApplicantBuilder()
                .withScholarship("Faculty of Engineering Scholarships").build()));

        // Mixed-case keywords
        predicate = new ScholarshipContainsKeywordsPredicate(Arrays.asList("FaCulTY", "enGiNeeRInG"));
        assertTrue(predicate.test(new ApplicantBuilder()
                .withScholarship("Faculty of Engineering Scholarships").build()));
    }

    @Test
    public void test_scholarshipDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ScholarshipContainsKeywordsPredicate predicate =
                new ScholarshipContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ApplicantBuilder().withScholarship("NUS Sports Scholarship").build()));

        // Non-matching keyword
        predicate = new ScholarshipContainsKeywordsPredicate(Arrays.asList("Sports"));
        assertFalse(predicate.test(new ApplicantBuilder()
                .withScholarship("Faculty of Engineering Scholarships").build()));

        // Keywords match name, phone and email, but does not match scholarship
        predicate = new ScholarshipContainsKeywordsPredicate(Arrays.asList("Alice", "12345678", "alice@email.com"));
        assertFalse(predicate.test(new ApplicantBuilder().withName("Alice").withPhone("12345678")
                .withEmail("alice@email.com").withScholarship("NUS Merit").build()));
    }
}
