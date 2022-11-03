package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalToDos.FIRST;
import static seedu.address.testutil.TypicalToDos.SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.TaskNotFoundException;

public class TaskListTest {

    private final TaskList taskList = new TaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(taskList.contains(FIRST));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        taskList.add(FIRST);
        assertTrue(taskList.contains(FIRST));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.add(null));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.remove(null));
    }

    @Test
    public void remove_existingTask_removesTask() {
        taskList.add(FIRST);
        taskList.remove(FIRST);
        TaskList expectedTaskList = new TaskList();
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> taskList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void setTask_invalidTask_throwsTaskNotFoundException() {
        taskList.add(FIRST);
        assertThrows(TaskNotFoundException.class, () -> taskList.setTask(SECOND, FIRST));
    }

    @Test
    public void setTask_validTask_editsTask() {
        taskList.add(FIRST);
        taskList.setTask(FIRST, SECOND);

        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(SECOND);

        assertEquals(expectedTaskList, taskList);
    }
}
