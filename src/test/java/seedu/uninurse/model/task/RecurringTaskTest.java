package seedu.uninurse.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalDateTime.DATE_TIME_FUTURE;
import static seedu.uninurse.testutil.TypicalDateTime.DATE_TIME_NOT_TODAY;
import static seedu.uninurse.testutil.TypicalDateTime.DATE_TIME_ONE;
import static seedu.uninurse.testutil.TypicalDateTime.DATE_TIME_PAST;
import static seedu.uninurse.testutil.TypicalDateTime.DATE_TIME_THREE;
import static seedu.uninurse.testutil.TypicalDateTime.DATE_TIME_TODAY;
import static seedu.uninurse.testutil.TypicalDateTime.DATE_TIME_TWO;

import org.junit.jupiter.api.Test;

class RecurringTaskTest {

    private static final String TASK_DESCRIPTION = "Administer 1 unit of insulin";
    private static final String TASK_DESCRIPTION_TWO = "Administer 2 units of insulin";
    private static final String INVALID_TASK_DESCRIPTION = "";

    @Test
    public void constructor_null_throwsNullPointerException() {
        // Everything null -> throws error
        assertThrows(NullPointerException.class, () -> new RecurringTask(null, null, null, 0));
        // No description -> throws error
        assertThrows(NullPointerException.class, () -> new RecurringTask(null, DATE_TIME_ONE,
                Recurrence.DAY, 1));
        // No DateTime -> throws error
        assertThrows(NullPointerException.class, () -> new RecurringTask(TASK_DESCRIPTION, null, Recurrence.DAY, 1));
        // No Recurrence -> throws error
        assertThrows(NullPointerException.class, () -> new RecurringTask(TASK_DESCRIPTION, DATE_TIME_ONE, null, 1));
    }

    @Test
    public void constructor_invalidTaskDescription_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new RecurringTask(INVALID_TASK_DESCRIPTION, DATE_TIME_ONE,
                Recurrence.DAY, 1));
    }

    @Test
    public void isTaskToday_taskNotToday_returnsFalse() {
        RecurringTask recurringTask = new RecurringTask(TASK_DESCRIPTION, DATE_TIME_NOT_TODAY, Recurrence.DAY, 1);
        assertFalse(recurringTask.isTaskToday());
    }

    @Test
    public void isTaskToday_taskToday_returnsTrue() {
        RecurringTask recurringTask = new RecurringTask(TASK_DESCRIPTION, DATE_TIME_TODAY, Recurrence.DAY, 1);
        assertTrue(recurringTask.isTaskToday());
    }

    @Test
    public void isTaskOnDay_taskNotOnDay_returnsFalse() {
        RecurringTask recurringTask = new RecurringTask(TASK_DESCRIPTION, DATE_TIME_ONE, Recurrence.DAY, 1);
        assertFalse(recurringTask.isTaskOnDay(DATE_TIME_TWO));
    }

    @Test
    public void isTaskOnDay_taskOnDay_returnsTrue() {
        RecurringTask recurringTask = new RecurringTask(TASK_DESCRIPTION, DATE_TIME_ONE, Recurrence.DAY, 1);
        assertTrue(recurringTask.isTaskOnDay(DATE_TIME_ONE));
    }

    @Test
    public void passedTaskDate_taskHasNotPassed_returnsFalse() {
        RecurringTask recurringTask = new RecurringTask(TASK_DESCRIPTION, DATE_TIME_FUTURE, Recurrence.DAY, 1);
        assertFalse(recurringTask.passedTaskDate());
    }

    @Test
    public void passedTaskDate_taskHasPassed_returnsTrue() {
        RecurringTask recurringTask = new RecurringTask(TASK_DESCRIPTION, DATE_TIME_PAST, Recurrence.DAY, 1);
        assertTrue(recurringTask.passedTaskDate());
    }

    @Test
    public void getNextRecurringTask_returnsCorrectRecurringTask() {
        RecurringTask recurringTask = new RecurringTask(TASK_DESCRIPTION, DATE_TIME_ONE, Recurrence.DAYS, 4);
        RecurringTask nextRecurringTask = new RecurringTask(TASK_DESCRIPTION, DATE_TIME_THREE, Recurrence.DAYS, 4);

        assertEquals(nextRecurringTask, recurringTask.getNextRecurringTask());
    }

    @Test
    public void isValidRecurAndFreq_invalidRecurOrFreq_returnsFalse() {
        // empty recurrence and frequency -> returns false
        assertFalse(RecurringTask.isValidRecurAndFreq(""));
        assertFalse(RecurringTask.isValidRecurAndFreq(" "));

        // valid recurrence but invalid frequency -> returns false
        assertFalse(RecurringTask.isValidRecurAndFreq(" " + Recurrence.DAYS));
        assertFalse(RecurringTask.isValidRecurAndFreq("x" + Recurrence.DAYS));
        assertFalse(RecurringTask.isValidRecurAndFreq("-9" + Recurrence.DAYS));
        assertFalse(RecurringTask.isValidRecurAndFreq("9x" + Recurrence.DAYS));
        assertFalse(RecurringTask.isValidRecurAndFreq("3.7" + Recurrence.DAYS));
        assertFalse(RecurringTask.isValidRecurAndFreq("4.0" + Recurrence.DAYS));

        // valid frequency but invalid recurrence -> returns false
        assertFalse(RecurringTask.isValidRecurAndFreq("4"));
        assertFalse(RecurringTask.isValidRecurAndFreq("4 x"));
        assertFalse(RecurringTask.isValidRecurAndFreq("4 10"));
        assertFalse(RecurringTask.isValidRecurAndFreq("4 custom recurrence"));
    }

    @Test
    public void isValidRecurAndFreq_validRecurAndFreq_returnsTrue() {
        assertTrue(RecurringTask.isValidRecurAndFreq("1 " + Recurrence.DAY));
        assertTrue(RecurringTask.isValidRecurAndFreq("4 " + Recurrence.WEEKS));
        assertTrue(RecurringTask.isValidRecurAndFreq("3 " + Recurrence.MONTHS));
    }

    @Test
    public void equals() {
        RecurringTask recurringTask = new RecurringTask(TASK_DESCRIPTION, DATE_TIME_ONE, Recurrence.DAY, 1);

        // same object -> returns true
        assertEquals(recurringTask, recurringTask);

        // same values -> returns true
        RecurringTask recurringTask2 = new RecurringTask(TASK_DESCRIPTION, DATE_TIME_ONE, Recurrence.DAY, 1);
        assertEquals(recurringTask, recurringTask2);

        // different descriptions -> returns false
        recurringTask2 = new RecurringTask(TASK_DESCRIPTION_TWO, DATE_TIME_ONE, Recurrence.DAY, 1);
        assertNotEquals(recurringTask, recurringTask2);

        // different DateTime -> returns false
        recurringTask2 = new RecurringTask(TASK_DESCRIPTION, DATE_TIME_THREE, Recurrence.DAY, 1);
        assertNotEquals(recurringTask, recurringTask2);

        // different Recurrence -> returns false;
        recurringTask2 = new RecurringTask(TASK_DESCRIPTION, DATE_TIME_ONE, Recurrence.WEEK, 1);
        assertNotEquals(recurringTask, recurringTask2);

        // different frequency -> returns false;
        recurringTask = new RecurringTask(TASK_DESCRIPTION, DATE_TIME_ONE, Recurrence.DAYS, 3);
        recurringTask2 = new RecurringTask(TASK_DESCRIPTION, DATE_TIME_TWO, Recurrence.DAYS, 4);
        assertNotEquals(recurringTask, recurringTask2);

        // null -> returns false
        assertNotEquals(null, recurringTask);
    }

}
