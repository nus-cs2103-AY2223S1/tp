package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalModules.CS2040;
import static seedu.address.testutil.TypicalTasks.TASK_A;
import static seedu.address.testutil.TypicalTasks.TASK_D;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class FilterPredicateTest {
    @Test
    public void test_taskFulfilsConditions_returnsTrue() {
        // Correct module
        FilterPredicate predicate = new FilterPredicate(Optional.of(CS2030), Optional.empty(),
                Optional.empty());
        assertTrue(predicate.test(TASK_A));

        // Correct completion status
        predicate = new FilterPredicate(Optional.empty(), Optional.of(true), Optional.empty());
        assertTrue(predicate.test(TASK_D));

        // Correct link status
        predicate = new FilterPredicate(Optional.empty(), Optional.empty(), Optional.of(true));
        assertTrue(predicate.test(TASK_D));

        // Correct module, completion status and link status
        predicate = new FilterPredicate(Optional.of(CS2030), Optional.of(false), Optional.of(false));
        assertTrue(predicate.test(TASK_A));
    }

    @Test
    public void test_taskDoesNotFulfilConditions_returnsFalse() {
        // Incorrect module
        FilterPredicate predicate = new FilterPredicate(Optional.of(CS2040), Optional.empty(),
                Optional.empty());
        assertFalse(predicate.test(TASK_A));

        // Incorrect completion status
        predicate = new FilterPredicate(Optional.empty(), Optional.of(false), Optional.empty());
        assertFalse(predicate.test(TASK_D));

        // Incorrect link status
        predicate = new FilterPredicate(Optional.empty(), Optional.empty(), Optional.of(false));
        assertFalse(predicate.test(TASK_D));

        // Incorrect module, completion status and link status
        predicate = new FilterPredicate(Optional.of(CS2040), Optional.of(true), Optional.of(true));
        assertFalse(predicate.test(TASK_A));

        // Incorrect module, correct completion status and link status
        predicate = new FilterPredicate(Optional.of(CS2040), Optional.of(false), Optional.of(false));
        assertFalse(predicate.test(TASK_A));

        // Incorrect completion status, correct module and link status
        predicate = new FilterPredicate(Optional.of(CS2030), Optional.of(true), Optional.of(false));
        assertFalse(predicate.test(TASK_A));

        // Incorrect link status, correct module and completion status
        predicate = new FilterPredicate(Optional.of(CS2030), Optional.of(false), Optional.of(true));
        assertFalse(predicate.test(TASK_A));
    }

    @Test
    public void equals() {
        FilterPredicate firstFilterPredicate = new FilterPredicate(Optional.of(CS2030), Optional.of(true),
                Optional.empty());
        FilterPredicate secondFilterPredicate = new FilterPredicate(Optional.of(CS2040), Optional.of(true),
                Optional.empty());
        FilterPredicate thirdFilterPredicate = new FilterPredicate(Optional.of(CS2030), Optional.of(false),
                Optional.empty());
        FilterPredicate fourthFilterPredicate = new FilterPredicate(Optional.of(CS2030), Optional.of(true),
                Optional.of(true));
        FilterPredicate firstFilterPredicateCopy = new FilterPredicate(Optional.of(CS2030), Optional.of(true),
                Optional.empty());

        // same object -> returns true
        assertTrue(firstFilterPredicate.equals(firstFilterPredicate));

        // same values -> returns true
        assertTrue(firstFilterPredicate.equals(firstFilterPredicateCopy));

        // different types -> returns false
        assertFalse(firstFilterPredicate.equals(1));

        // null -> returns false
        assertFalse(firstFilterPredicate.equals(null));

        // different module -> returns false
        assertFalse(firstFilterPredicate.equals(secondFilterPredicate));

        // different completion status -> returns false
        assertFalse(firstFilterPredicate.equals(thirdFilterPredicate));

        // different link status -> returns false
        assertFalse(firstFilterPredicate.equals(fourthFilterPredicate));
    }
}
