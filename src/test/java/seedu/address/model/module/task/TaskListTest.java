package seedu.address.model.module.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK_A;
import static seedu.address.testutil.TypicalTasks.TASK_B;
import static seedu.address.testutil.TypicalTasks.TASK_C;
import static seedu.address.testutil.TypicalTasks.getTypicalTasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

public class TaskListTest {

    private final TaskList taskList = new TaskList(getTypicalTasks());
    private final Task taskNotInList = new Task("Some task not in list");

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(taskList.contains(taskNotInList));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        taskList.add(taskNotInList);
        assertTrue(taskList.contains(taskNotInList));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        List<Task> observableList = taskList.asUnmodifiableObservableList();
        assertThrows(UnsupportedOperationException.class, () -> observableList.remove(0));
        assertThrows(UnsupportedOperationException.class, () -> observableList.add(taskNotInList));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.add(null));
    }

    @Test
    public void add_duplicateTask_success() {
        List<Task> tasksWithDuplicates = new ArrayList<>(Arrays.asList(TASK_A,
                TASK_B, TASK_C, TASK_A));
        TaskList expectedTaskList = new TaskList(tasksWithDuplicates);
        taskList.add(TASK_A);
        assertEquals(taskList, expectedTaskList);
    }

    @Test
    public void setTasks_nullReplacementTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks(null));
    }

    @Test
    public void setTasks_replacementTaskListIsSameTaskList_success() {
        TaskList newTaskList = new TaskList(getTypicalTasks());
        newTaskList.setTasks(taskList);
        assertEquals(newTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        List<Task> newTasks = new ArrayList<>();
        newTasks.add(taskNotInList);
        TaskList expectedTaskList = new TaskList(newTasks);
        TaskList newTaskList = new TaskList(getTypicalTasks());
        newTaskList.setTasks(expectedTaskList);
        assertEquals(newTaskList, expectedTaskList);
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.remove(null));
    }

    @Test
    public void remove_existingTask_removesTask() {
        Index indexZero = Index.fromZeroBased(0);
        taskList.remove(indexZero);
        taskList.remove(indexZero);
        taskList.remove(indexZero);
        TaskList expectedTaskList = new TaskList();
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void swapTasks_nullTasks_swapsTasks() {
        Index indexZero = Index.fromZeroBased(0);
        assertThrows(NullPointerException.class, () -> taskList.swapTasks(null, indexZero));
        assertThrows(NullPointerException.class, () -> taskList.swapTasks(indexZero, null));
        assertThrows(NullPointerException.class, () -> taskList.swapTasks(null, null));
    }

    @Test
    public void swapTasks_existingTasks_swapsTasks() {
        Index indexZero = Index.fromZeroBased(0);
        Index indexOne = Index.fromZeroBased(1);
        taskList.swapTasks(indexZero, indexOne);
        TaskList expectedTaskList =
                new TaskList(Arrays.asList(TASK_B, TASK_A, TASK_C));
        assertEquals(expectedTaskList, taskList);
    }
}
