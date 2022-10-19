package seedu.application.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.application.testutil.ApplicationBuilder;

public class ShowArchiveOnlyPredicateTest {

    @Test
    public void equals() {

        ShowArchiveOnlyPredicate firstPredicate = new ShowArchiveOnlyPredicate();
        ShowArchiveOnlyPredicate secondPredicate = new ShowArchiveOnlyPredicate();

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
    public void test_applicationIsArchived_returnsTrue() {
        ShowArchiveOnlyPredicate predicate = new ShowArchiveOnlyPredicate();
        assertTrue(predicate.test(new ApplicationBuilder().withArchiveStatus(true).build()));
    }

    @Test
    public void test_applicationIsNotArchived_returnsFalse() {
        ShowArchiveOnlyPredicate predicate = new ShowArchiveOnlyPredicate();
        assertFalse(predicate.test(new ApplicationBuilder().build()));
    }
}
