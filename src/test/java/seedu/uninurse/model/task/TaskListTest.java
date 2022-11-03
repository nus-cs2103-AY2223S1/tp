package seedu.uninurse.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TestUtil.getCurrentDate;
import static seedu.uninurse.testutil.TypicalTasks.TASK_HEALTH_RECORDS;
import static seedu.uninurse.testutil.TypicalTasks.TASK_INSULIN;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.uninurse.model.task.exceptions.DuplicateTaskException;

class TaskListTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskList(null));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        TaskList tasks = new TaskList();
        assertThrows(NullPointerException.class, () -> tasks.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        TaskList tasks = new TaskList().add(TASK_INSULIN);
        assertThrows(DuplicateTaskException.class, () -> tasks.add(TASK_INSULIN));
    }

    @Test
    public void edit_sameTask_throwsDuplicateTaskException() {
        TaskList tasks = new TaskList().add(TASK_INSULIN);
        assertThrows(DuplicateTaskException.class, () -> tasks.edit(0, TASK_INSULIN));
    }

    @Test
    public void edit_diffTask_success() {
        Task editedTask = new NonRecurringTask("new description", TASK_INSULIN.getDateTime());
        TaskList tasks = new TaskList().add(TASK_INSULIN);

        TaskList expectedTasks = new TaskList().add(editedTask);

        tasks = tasks.edit(0, editedTask);
        assertEquals(tasks, expectedTasks);
    }

    @Test
    public void size_emptyList_returnsZero() {
        TaskList tasks = new TaskList();
        assertEquals(tasks.size(), 0);
    }

    @Test
    public void size_nonEmptyList_returnsNonZero() {
        TaskList tasks = new TaskList().add(TASK_INSULIN);
        assertNotEquals(tasks.size(), 0);
    }

    @Test
    public void isEmpty_emptyList_returnsTrue() {
        TaskList tasks = new TaskList();
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void isEmpty_nonEmptyList_returnsFalse() {
        TaskList tasks = new TaskList().add(TASK_INSULIN);
        assertFalse(tasks.isEmpty());
    }

    @Test
    public void getInternalList_modifyList_throwsUnsupportedOperationException() {
        TaskList tasks = new TaskList().add(TASK_INSULIN);
        assertThrows(UnsupportedOperationException.class, () -> tasks.getInternalList().remove(0));
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
