package seedu.application.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.application.testutil.ApplicationBuilder;

public class HideArchiveFromListPredicateTest {
    @Test
    public void equals() {

        HideArchiveFromListPredicate firstPredicate = new HideArchiveFromListPredicate();
        HideArchiveFromListPredicate secondPredicate = new HideArchiveFromListPredicate();

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // different object same nature -> returns true
        assertTrue(firstPredicate.equals(secondPredicate));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));
    }

    @Test
    public void test_applicationIsArchived_returnsFalse() {
        HideArchiveFromListPredicate predicate = new HideArchiveFromListPredicate();
        assertFalse(predicate.test(new ApplicationBuilder().withArchiveStatus(true).build()));
    }

    @Test
    public void test_applicationIsNotArchived_returnsTrue() {
        HideArchiveFromListPredicate predicate = new HideArchiveFromListPredicate();
        assertTrue(predicate.test(new ApplicationBuilder().build()));
    }
}
