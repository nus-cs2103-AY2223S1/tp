package seedu.uninurse.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalDateTime.DATE_TIME_ONE;
import static seedu.uninurse.testutil.TypicalTasks.TASK_HEALTH_RECORDS;
import static seedu.uninurse.testutil.TypicalTasks.TASK_INSULIN;

import org.junit.jupiter.api.Test;

// TODO rewrite the tests to factor in DateTime portion of Tasks
public class TaskTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NonRecurringTask(null));
    }

    @Test
    public void constructor_invalidTaskDescription_throwsIllegalArgumentException() {
        String invalidTaskDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new NonRecurringTask(invalidTaskDescription));
    }

    @Test
    public void isValidTaskDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Task.isValidTaskDescription(null));
    }

    @Test
    public void isValidTaskDescription_validDescription_returnsTrue() {
        // numbers in task description -> returns true
        assertTrue(Task.isValidTaskDescription("123"));

        // special characters in task description -> returns true
        assertTrue(Task.isValidTaskDescription("@!#$%^&*()-=+_[];.,`~:<>?/"));

        // valid task description -> returns true
        assertTrue(Task.isValidTaskDescription("Check vitals"));
    }

    @Test
    public void isValidTaskDescription_invalidDescription_returnsFalse() {
        // empty task description -> returns false
        assertFalse(Task.isValidTaskDescription(""));

        // blank task description -> returns false
        assertFalse(Task.isValidTaskDescription("  "));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(TASK_INSULIN, TASK_INSULIN);

        // same values -> returns true
        String taskInsulinDescription = "Administer 1 unit of insulin";
        Task taskInsulinCopy = new NonRecurringTask(taskInsulinDescription, DATE_TIME_ONE);
        assertEquals(TASK_INSULIN, taskInsulinCopy);

        // different types -> returns false
        assertNotEquals(1, TASK_INSULIN);

        // null -> returns false
        assertNotEquals(null, TASK_INSULIN);

        // different description -> returns false
        assertNotEquals(TASK_INSULIN, TASK_HEALTH_RECORDS);
    }
}
