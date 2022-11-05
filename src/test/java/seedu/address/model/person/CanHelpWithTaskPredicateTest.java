package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TaskBuilder;

public class CanHelpWithTaskPredicateTest {

    @Test
    public void equals() {
        int firstTaskIndex = 2;
        int secondTaskIndex = 4;

        CanHelpWithTaskPredicate firstPredicate = new CanHelpWithTaskPredicate(firstTaskIndex);
        CanHelpWithTaskPredicate secondPredicate = new CanHelpWithTaskPredicate(secondTaskIndex);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CanHelpWithTaskPredicate firstPredicateCopy = new CanHelpWithTaskPredicate(2);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different index -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personTakesTasksModule_returnsTrue() {
        // One keyword
        CanHelpWithTaskPredicate predicate = CanHelpWithTaskPredicate
                .withTask(new TaskBuilder().withModule("CS1234").build());
        assertTrue(predicate.test(new PersonBuilder().withModules("CS1234").build()));

        // Mixed case keywords
        predicate = CanHelpWithTaskPredicate
                .withTask(new TaskBuilder().withModule("cs1234").build());
        assertTrue(predicate.test(new PersonBuilder().withModules("CS1234").build()));
    }

    @Test
    public void test_personNotTakingTasksModule_returnsFalse() {
        // Non-matching keyword
        CanHelpWithTaskPredicate predicate = CanHelpWithTaskPredicate
                .withTask(new TaskBuilder().withModule("BIZ1101").build());
        assertFalse(predicate.test(new PersonBuilder().withModules("CS1234").build()));
    }

    @Test
    public void getTaskIndex() {
        CanHelpWithTaskPredicate testPredicate = new CanHelpWithTaskPredicate(99);
        assertEquals(99, testPredicate.getTaskIndex().getOneBased());
    }
}
