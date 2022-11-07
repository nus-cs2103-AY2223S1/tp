package seedu.trackascholar.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.testutil.ApplicantBuilder;

public class ApplicationStatusPredicateTest {

    @Test
    public void equals() {
        ApplicationStatusPredicate firstPredicate =
                new ApplicationStatusPredicate("pending");
        ApplicationStatusPredicate secondPredicate =
                new ApplicationStatusPredicate("accepted");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ApplicationStatusPredicate firstPredicateCopy = new ApplicationStatusPredicate("pending");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different applicant -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_applicationStatusContainsKeywords_returnsTrue() {
        // One keyword
        ApplicationStatusPredicate predicate = new ApplicationStatusPredicate("accepted");
        assertTrue(predicate.test(new ApplicantBuilder().withApplicationStatus("accepted").build()));

        // Mixed-case keywords
        ApplicationStatusPredicate secondPredicate = new ApplicationStatusPredicate("aCCepTEd");
        assertTrue(secondPredicate.test(new ApplicantBuilder().withApplicationStatus("accepted").build()));
    }

    @Test
    public void test_applicationStatusDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        ApplicationStatusPredicate secondPredicate = new ApplicationStatusPredicate("accepted");
        assertFalse(secondPredicate.test(new ApplicantBuilder().withApplicationStatus("pending").build()));
    }


}
