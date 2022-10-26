package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.Module;

public class TaskContainsModulesPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateModuleList = Collections.singletonList("MOD1");
        List<String> secondPredicateModuleList = Arrays.asList("MOD1", "MOD2");

        TaskContainsModulesPredicate firstPredicate = new TaskContainsModulesPredicate(firstPredicateModuleList);
        TaskContainsModulesPredicate secondPredicate = new TaskContainsModulesPredicate(secondPredicateModuleList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskContainsModulesPredicate firstPredicateCopy = new TaskContainsModulesPredicate(firstPredicateModuleList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_moduleContainsModules_returnsTrue() {
        // One keyword
        TaskContainsModulesPredicate predicate =
                new TaskContainsModulesPredicate(Collections.singletonList("Mod1"));
        assertTrue(predicate.test(new Task(new TaskName("Science Math homework"), new Module("Mod1"),
                new Deadline("2022-05-05 15:04"), new Status(false))));

        // Mixed-case keywords
        predicate = new TaskContainsModulesPredicate(Collections.singletonList("mOd2"));
        assertTrue(predicate.test(new Task(new TaskName("Science Math homework"), new Module("MOD2"),
                new Deadline("2022-05-05 15:04"), new Status(false))));

        // Matching substring
        predicate = new TaskContainsModulesPredicate(Collections.singletonList("1521"));
        assertTrue(predicate.test(new Task(new TaskName("Science Math homework"), new Module("MA1521"),
                new Deadline("2022-05-05 15:04"), new Status(false))));
    }

    @Test
    public void test_moduleDoesNotContainModules_returnsFalse() {
        // Zero keywords
        TaskContainsModulesPredicate predicate = new TaskContainsModulesPredicate(Collections.emptyList());
        assertFalse(predicate.test(new Task(new TaskName("Science Math homework"), new Module("Mod1"),
                new Deadline("2022-05-05 15:04"), new Status(false))));

        // Non-matching keyword
        predicate = new TaskContainsModulesPredicate(Collections.singletonList("BZ1101"));
        assertFalse(predicate.test(new Task(new TaskName("Science Math homework"), new Module("Mod1"),
                new Deadline("2022-05-05 15:04"), new Status(false))));

        // Non matching substring
        predicate = new TaskContainsModulesPredicate(Collections.singletonList("1231"));
        assertFalse(predicate.test(new Task(new TaskName("Science Math homework"), new Module("MA1521"),
                new Deadline("2022-05-05 15:04"), new Status(false))));
    }
}
