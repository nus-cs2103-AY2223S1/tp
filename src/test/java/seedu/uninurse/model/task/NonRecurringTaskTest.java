package seedu.uninurse.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalDateTime.DATE_TIME_NOT_TODAY;
import static seedu.uninurse.testutil.TypicalDateTime.DATE_TIME_ONE;
import static seedu.uninurse.testutil.TypicalDateTime.DATE_TIME_TODAY;
import static seedu.uninurse.testutil.TypicalDateTime.DATE_TIME_TWO;
import static seedu.uninurse.testutil.TypicalTasks.TASK_HEALTH_RECORDS;
import static seedu.uninurse.testutil.TypicalTasks.TASK_INSULIN;

import org.junit.jupiter.api.Test;

public class NonRecurringTaskTest {

    private static final String TASK_DESCRIPTION = "Administer 1 unit of insulin";
    private static final String TASK_DESCRIPTION_TWO = "Administer 2 units of insulin";
    private static final String INVALID_TASK_DESCRIPTION = "";

    @Test
    public void constructor_null_throwsNullPointerException() {
        // No description -> throws error
        assertThrows(NullPointerException.class, () -> new NonRecurringTask(null));
        // Both no description and no DateTime -> throws error
        assertThrows(NullPointerException.class, () -> new NonRecurringTask(null, null));
        // No description but has DateTime -> throws error
        assertThrows(NullPointerException.class, () -> new NonRecurringTask(null, DATE_TIME_ONE));
        // Description but trying to construct using null DateTime -> throws an error
        assertThrows(NullPointerException.class, () -> new NonRecurringTask(TASK_DESCRIPTION, null));
    }

    @Test
    public void constructor_invalidTaskDescription_throwsIllegalArgumentException() {
        // Invalid description -> throws error
        assertThrows(IllegalArgumentException.class, () -> new NonRecurringTask(INVALID_TASK_DESCRIPTION));
        // Invalid description using with DateTime constructor -> throws error
        assertThrows(IllegalArgumentException.class, () -> new NonRecurringTask(INVALID_TASK_DESCRIPTION,
                DATE_TIME_ONE));
    }

    @Test
    public void isTaskToday_taskNotToday_returnsFalse() {
        NonRecurringTask nonRecurringTask = new NonRecurringTask(TASK_DESCRIPTION, DATE_TIME_NOT_TODAY);
        assertFalse(nonRecurringTask.isTaskToday());
    }

    @Test
    public void isTaskToday_taskToday_returnsTrue() {
        NonRecurringTask nonRecurringTask = new NonRecurringTask(TASK_DESCRIPTION, DATE_TIME_TODAY);
        assertTrue(nonRecurringTask.isTaskToday());
    }

    @Test
    public void isTaskOnDay_taskNotOnDay_returnsFalse() {
        NonRecurringTask nonRecurringTask = new NonRecurringTask(TASK_DESCRIPTION, DATE_TIME_ONE);
        assertFalse(nonRecurringTask.isTaskOnDay(DATE_TIME_TWO));
    }

    @Test
    public void isTaskOnDay_taskOnDay_returnsTrue() {
        NonRecurringTask nonRecurringTask = new NonRecurringTask(TASK_DESCRIPTION, DATE_TIME_ONE);
        assertTrue(nonRecurringTask.isTaskOnDay(DATE_TIME_ONE));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(TASK_INSULIN, TASK_INSULIN);

        // same values -> returns true
        NonRecurringTask nonRecurringTask1 = new NonRecurringTask(TASK_DESCRIPTION, DATE_TIME_ONE);
        NonRecurringTask nonRecurringTask2 = new NonRecurringTask(TASK_DESCRIPTION, DATE_TIME_ONE);
        assertEquals(nonRecurringTask1, nonRecurringTask2);

        // same description but different DateTime -> returns false
        nonRecurringTask1 = new NonRecurringTask(TASK_DESCRIPTION, DATE_TIME_ONE);
        nonRecurringTask2 = new NonRecurringTask(TASK_DESCRIPTION, DATE_TIME_TWO);
        assertNotEquals(nonRecurringTask1, nonRecurringTask2);

        // different description but same DateTime -> returns false
        nonRecurringTask1 = new NonRecurringTask(TASK_DESCRIPTION, DATE_TIME_ONE);
        nonRecurringTask2 = new NonRecurringTask(TASK_DESCRIPTION_TWO, DATE_TIME_ONE);
        assertNotEquals(nonRecurringTask1, nonRecurringTask2);

        // null -> returns false
        assertNotEquals(null, TASK_INSULIN);

        // different tasks -> returns false
        assertNotEquals(TASK_INSULIN, TASK_HEALTH_RECORDS);
    }
}
