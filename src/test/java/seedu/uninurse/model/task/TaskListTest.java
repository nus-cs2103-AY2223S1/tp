package seedu.uninurse.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TestUtil.getCurrentDate;
import static seedu.uninurse.testutil.TypicalTasks.TASK_HEALTH_RECORDS;
import static seedu.uninurse.testutil.TypicalTasks.TASK_INSULIN;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TaskListTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskList(null));
    }

    @Test
    public void containsTaskToday_noTasksToday_returnsFalse() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(TASK_HEALTH_RECORDS);
        tasks.add(TASK_INSULIN);

        TaskList taskList = new TaskList(tasks);

        assertFalse(taskList.containsTaskToday());
    }

    @Test
    public void containsTaskToday_oneTaskToday_returnsTrue() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(TASK_HEALTH_RECORDS);
        tasks.add(TASK_INSULIN);
        tasks.add(new NonRecurringTask("test", new DateTime(getCurrentDate())));

        TaskList taskList = new TaskList(tasks);

        assertTrue(taskList.containsTaskToday());
    }
}
