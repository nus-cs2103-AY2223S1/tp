package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicateTaskException;
import seedu.address.model.person.exceptions.TaskNotFoundException;

public class TaskListTest {

    private final Task taskOne = new Task("Task 1");
    private final Task taskTwo = new Task("Task 2");
    private final Task duplicateTask = new Task("Task 1");

    private final TaskList taskList = new TaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(taskList.contains(taskOne));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        taskList.add(taskOne);
        assertTrue(taskList.contains(taskOne));
    }

    @Test
    public void contains_taskWithSameDescription_returnsTrue() {
        taskList.add(taskOne);
        assertTrue(taskList.contains(duplicateTask));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        taskList.add(taskOne);
        assertThrows(DuplicateTaskException.class, () -> taskList.add(duplicateTask));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTask(null, taskOne));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTask(taskOne, null));
    }

    @Test
    public void setPerson_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskList.setTask(taskOne, taskOne));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        taskList.add(taskOne);
        taskList.setTask(taskOne, taskOne);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(taskOne);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskHasDifferentDescription_success() {
        taskList.add(taskOne);
        taskList.setTask(taskOne, taskTwo);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(taskTwo);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setPerson_editedTaskHasNonUniqueDescription_throwsDuplicateTaskException() {
        taskList.add(taskOne);
        taskList.add(taskTwo);
        assertThrows(DuplicateTaskException.class, () -> taskList.setTask(taskOne, taskTwo));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskList.remove(taskOne));
    }

    @Test
    public void remove_existingTask_removesTask() {
        taskList.add(taskOne);
        taskList.remove(taskOne);
        TaskList expectedTaskList = new TaskList();
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_nullTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks((TaskList) null));
    }

    @Test
    public void setTasks_taskList_replacesOwnListWithProvidedTaskList() {
        taskList.add(taskOne);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(taskTwo);
        taskList.setTasks(expectedTaskList);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        taskList.add(taskOne);
        List<Task> newTaskList = Collections.singletonList(taskTwo);
        taskList.setTasks(newTaskList);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(taskTwo);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_listWithDuplicateTasks_throwsDuplicateTaskException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(taskOne, taskOne);
        assertThrows(DuplicateTaskException.class, () -> taskList.setTasks(listWithDuplicateTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> taskList.asUnmodifiableObservableList().remove(0));
    }
}
