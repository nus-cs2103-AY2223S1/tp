package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK_DEADLINE_AFTER_CHICKEN;
import static seedu.address.testutil.TypicalTasks.TASK_DEADLINE_BEFORE_CHICKEN;
import static seedu.address.testutil.TypicalTasks.TASK_DEADLINE_SAME_CHICKEN;
import static seedu.address.testutil.TypicalTasks.TASK_FUEL;
import static seedu.address.testutil.TypicalTasks.TASK_GARLIC;
import static seedu.address.testutil.TypicalTasks.getTimedTaskList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.TaskList;

public class TaskListTest {

    private final TaskList taskList = new TaskList();
    private final TaskList typicalTaskList = getTypicalTaskList();
    private final TaskList timedTaskList = getTimedTaskList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taskList.getTaskList());
    }

    @Test
    public void constructor_copyTaskList_equalToOriginal() {
        assertEquals(typicalTaskList, new TaskList(typicalTaskList));
    }

    @Test
    public void setTasks_withValidListOfTask_equalToOriginal() {
        taskList.setTasks(typicalTaskList.getTaskList());
        assertEquals(typicalTaskList, taskList);
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTaskList_replacesData() {
        TaskList newData = getTypicalTaskList();
        taskList.resetData(newData);
        assertEquals(newData, taskList);
    }

    @Test
    public void addTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.addTask(null));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTaskList_returnsFalse() {
        assertFalse(taskList.hasTask(TASK_FUEL));
    }

    @Test
    public void hasTask_taskInTaskList_returnsTrue() {
        taskList.addTask(TASK_FUEL);
        assertTrue(taskList.hasTask(TASK_FUEL));
    }

    @Test
    public void setTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTask(null, Index.fromZeroBased(0)));
    }

    @Test
    public void setTask_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTask(TASK_FUEL, null));
    }

    @Test
    public void setTask_indexOutOfBound_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.setTask(TASK_FUEL, Index.fromZeroBased(0)));
    }

    @Test
    public void setTask_validTaskAndIndex_replacesTask() {
        taskList.addTask(TASK_FUEL);
        taskList.setTask(TASK_GARLIC, Index.fromZeroBased(0));
        assertEquals(TASK_GARLIC, taskList.getTaskList().get(0));
    }

    @Test
    public void deleteTask_indexOutOfBound_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(Index.fromZeroBased(0)));
    }

    @Test
    public void deleteTask_validIndex_success() {
        taskList.addTask(TASK_FUEL);
        taskList.deleteTask(Index.fromZeroBased(0));
        assertEquals(Collections.emptyList(), taskList.getTaskList());
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> typicalTaskList.getTaskList().remove(0));
    }

    @Test
    public void findIndexOfTask_earlierDeadline_success() {
        int results = timedTaskList.findIndexOfTask(TASK_DEADLINE_BEFORE_CHICKEN);
        int expectedResults = 1;
        assertEquals(results, expectedResults);
    }

    @Test
    public void findIndexOfTask_afterDeadline_success() {
        int results = timedTaskList.findIndexOfTask(TASK_DEADLINE_AFTER_CHICKEN);
        int expectedResults = 3;
        assertEquals(results, expectedResults);
    }

    @Test
    public void findIndexOfTask_sameDeadline_success() {
        int results = timedTaskList.findIndexOfTask(TASK_DEADLINE_SAME_CHICKEN);
        int expectedResults = 1;
        assertEquals(results, expectedResults);
    }

    @Test
    public void findIndexOfTask_emptyTaskList_success() {
        int results = taskList.findIndexOfTask(TASK_DEADLINE_AFTER_CHICKEN);
        int expectedResults = 0;
        assertEquals(results, expectedResults);
    }

}
