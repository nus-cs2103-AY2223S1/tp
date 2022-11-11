package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

public class InternshipHasApplicationStatusPredicateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InternshipHasApplicationStatusPredicate(null));
    }

    @Test
    public void equals() {
        ApplicationStatus firstApplicationStatus = ApplicationStatus.Applied;
        ApplicationStatus secondApplicationStatus = ApplicationStatus.Accepted;

        InternshipHasApplicationStatusPredicate firstPredicate =
                new InternshipHasApplicationStatusPredicate(firstApplicationStatus);
        InternshipHasApplicationStatusPredicate secondPredicate =
                new InternshipHasApplicationStatusPredicate(secondApplicationStatus);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        InternshipHasApplicationStatusPredicate firstPredicateCopy =
                new InternshipHasApplicationStatusPredicate(firstApplicationStatus);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_containsApplicationStatus_returnsTrue() {
        InternshipHasApplicationStatusPredicate predicate =
                new InternshipHasApplicationStatusPredicate(ApplicationStatus.Applied);
        assertTrue(predicate.test(new InternshipBuilder().withApplicationStatus(ApplicationStatus.Applied).build()));
    }

    @Test
    public void test_doesNotContainApplicationStatus_returnsFalse() {
        InternshipHasApplicationStatusPredicate predicate =
                new InternshipHasApplicationStatusPredicate(ApplicationStatus.Applied);
        assertFalse(predicate.test(new InternshipBuilder().withApplicationStatus(ApplicationStatus.Rejected).build()));
    }
}
