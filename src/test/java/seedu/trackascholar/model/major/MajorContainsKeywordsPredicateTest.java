package seedu.trackascholar.model.major;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.testutil.ApplicantBuilder;

public class MajorContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        MajorContainsKeywordsPredicate firstPredicate =
                new MajorContainsKeywordsPredicate(firstPredicateKeywordList);
        MajorContainsKeywordsPredicate secondPredicate =
                new MajorContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MajorContainsKeywordsPredicate firstPredicateCopy =
                new MajorContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different applicant -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_majorsContainsKeywords_returnsTrue() {
        // One keyword and one major
        MajorContainsKeywordsPredicate predicate =
                new MajorContainsKeywordsPredicate(Collections.singletonList("Computer"));
        assertTrue(predicate.test(new ApplicantBuilder().withMajors("Computer Science").build()));

        // One keyword and two majors
        assertTrue(predicate.test(new ApplicantBuilder().withMajors("Computer Science", "Mathematics").build()));
        predicate = new MajorContainsKeywordsPredicate(Collections.singletonList("Mathematics"));
        assertTrue(predicate.test(new ApplicantBuilder().withMajors("Computer Science", "Mathematics").build()));

        // Multiple keywords and one major
        predicate = new MajorContainsKeywordsPredicate(Arrays.asList("Computer", "Science"));
        assertTrue(predicate.test(new ApplicantBuilder().withMajors("Computer Science").build()));

        // Multiple keywords and two major
        assertTrue(predicate.test(new ApplicantBuilder().withMajors("Computer Science", "Mathematics").build()));
        predicate = new MajorContainsKeywordsPredicate(Arrays.asList("Computer", "Mathematics"));
        assertTrue(predicate.test(new ApplicantBuilder().withMajors("Computer Science", "Mathematics").build()));

        // Only one matching keyword
        predicate = new MajorContainsKeywordsPredicate(Arrays.asList("Business", "Computer"));
        assertTrue(predicate.test(new ApplicantBuilder().withMajors("Computer Science", "Mathematics").build()));

        // Mixed-case keywords
        predicate = new MajorContainsKeywordsPredicate(Arrays.asList("cOmpuTEr", "scIEncE"));
        assertTrue(predicate.test(new ApplicantBuilder().withMajors("Computer Science").build()));
        predicate = new MajorContainsKeywordsPredicate(Arrays.asList("mAtHEmatIcS"));
        assertTrue(predicate.test(new ApplicantBuilder().withMajors("Mathematics").build()));
        predicate = new MajorContainsKeywordsPredicate(Arrays.asList("cOmpuTEr", "scIEncE", "mAtHEmatIcS"));
        assertTrue(predicate.test(new ApplicantBuilder().withMajors("Computer Science", "Mathematics").build()));
    }

    @Test
    public void test_majorsDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        MajorContainsKeywordsPredicate predicate = new MajorContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ApplicantBuilder().withMajors("Computer Science").build()));
        assertFalse(predicate.test(new ApplicantBuilder().withMajors("Computer Science", "Mathematics").build()));

        // Non-matching keyword
        predicate = new MajorContainsKeywordsPredicate(Arrays.asList("Business"));
        assertFalse(predicate.test(new ApplicantBuilder().withMajors("Computer Science").build()));
        assertFalse(predicate.test(new ApplicantBuilder().withMajors("Computer Science", "Mathematics").build()));

        // Keywords match name, phone, email and scholarship, but does not match majors
        predicate = new MajorContainsKeywordsPredicate(Arrays.asList("Alice", "12345678", "alice@email.com",
                "NUS", "Merit"));
        assertFalse(predicate.test(new ApplicantBuilder().withName("Alice").withPhone("12345678")
                .withEmail("alice@email.com").withScholarship("NUS Merit").withMajors("Computer Science").build()));
        assertFalse(predicate.test(new ApplicantBuilder().withName("Alice").withPhone("12345678")
                .withEmail("alice@email.com").withScholarship("NUS Merit")
                .withMajors("Computer Science", "Mathematics").build()));
    }
}
