package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TaskIsDonePredicateTest {
    @Test
    public void equals() {

        TaskIsDonePredicate predicate = new TaskIsDonePredicate(List.of("false"));

        // same object -> returns true
        assertTrue(predicate.equals(predicate));

        // same values -> returns true
        TaskIsDonePredicate predicateCopy = new TaskIsDonePredicate(List.of("false"));
        assertTrue(predicate.equals(predicateCopy));

        // different types -> returns false
        assertFalse(predicate.equals(1));

        // null -> returns false
        assertFalse(predicate.equals(null));

    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        TaskIsDonePredicate predicate = new TaskIsDonePredicate(List.of("false"));
        assertTrue(predicate.test(new PersonBuilder().withIsDone(false).build()));

    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        TaskIsDonePredicate predicate = new TaskIsDonePredicate(List.of("false"));
        assertFalse(predicate.test(new PersonBuilder().withIsDone(true).build()));

    }
}
