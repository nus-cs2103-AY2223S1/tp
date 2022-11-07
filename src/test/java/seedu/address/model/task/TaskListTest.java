package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_EARLIEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_QUIZ;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_QUIZ;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_2101;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.READ_BOOK;
import static seedu.address.testutil.TypicalTasks.WRITE_TEST_CASES;
import static seedu.address.testutil.TypicalTasks.getTypicalTasks;
import static seedu.address.testutil.TypicalTasks.getTypicalTasksSortedByDeadline;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.TaskBuilder;

public class TaskListTest {

    private final TaskList taskList = new TaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(taskList.contains(READ_BOOK));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        taskList.addTask(READ_BOOK);
        assertTrue(taskList.contains(READ_BOOK));
    }

    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        taskList.addTask(READ_BOOK);
        Task editedReadBook = new TaskBuilder(READ_BOOK).withCompletionStatus(false).build();
        assertTrue(taskList.contains(editedReadBook));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.addTask(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        taskList.addTask(READ_BOOK);
        assertThrows(DuplicateTaskException.class, () -> taskList.addTask(READ_BOOK));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTask(null, READ_BOOK));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTask(READ_BOOK, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskList.setTask(READ_BOOK, READ_BOOK));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        taskList.addTask(READ_BOOK);
        taskList.setTask(READ_BOOK, READ_BOOK);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.addTask(READ_BOOK);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskHasSameIdentity_success() {
        taskList.addTask(READ_BOOK);
        Task editedReadBook = new TaskBuilder(READ_BOOK).withDeadline(VALID_DEADLINE_QUIZ).withTags(VALID_TAG_2101)
                .build();
        taskList.setTask(READ_BOOK, editedReadBook);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.addTask(editedReadBook);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        taskList.addTask(READ_BOOK);
        taskList.setTask(READ_BOOK, WRITE_TEST_CASES);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.addTask(WRITE_TEST_CASES);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        taskList.addTask(READ_BOOK);
        taskList.addTask(WRITE_TEST_CASES);
        assertThrows(DuplicateTaskException.class, () -> taskList.setTask(READ_BOOK, WRITE_TEST_CASES));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskList.remove(READ_BOOK));
    }

    @Test
    public void remove_existingTask_removesTask() {
        taskList.addTask(READ_BOOK);
        taskList.remove(READ_BOOK);
        TaskList expectedTaskList = new TaskList();
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_nullTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks((TaskList) null));
    }

    @Test
    public void setTasks_taskList_replacesOwnListWithProvidedTaskList() {
        taskList.addTask(READ_BOOK);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.addTask(WRITE_TEST_CASES);
        taskList.setTasks(expectedTaskList);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        taskList.addTask(READ_BOOK);
        List<Task> personList = Collections.singletonList(WRITE_TEST_CASES);
        taskList.setTasks(personList);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.addTask(WRITE_TEST_CASES);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_listWithDuplicateTasks_throwsDuplicateTaskException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(READ_BOOK, READ_BOOK);
        assertThrows(DuplicateTaskException.class, () -> taskList.setTasks(listWithDuplicateTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> taskList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void sortByDeadline_unsortedList_sortedList() {
        taskList.setTasks(getTypicalTasks());
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.setTasks(getTypicalTasksSortedByDeadline());
        taskList.sortByDeadline();
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void sortByDeadline_sortedList_sameList() {
        taskList.setTasks(getTypicalTasksSortedByDeadline());
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.setTasks(getTypicalTasksSortedByDeadline());
        taskList.sortByDeadline();
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void sortByDeadline_addTaskToSortedList_sortedList() {
        taskList.setTasks(getTypicalTasks());
        taskList.sortByDeadline();

        TaskList expectedTaskList = new TaskList();
        Task taskWithEarliestDeadline = new TaskBuilder()
                .withDescription(VALID_DESCRIPTION_QUIZ).withDeadline(VALID_DEADLINE_EARLIEST).build();
        expectedTaskList.addTask(taskWithEarliestDeadline);
        for (Task task : getTypicalTasksSortedByDeadline()) {
            expectedTaskList.addTask(task);
        }

        taskList.addTask(taskWithEarliestDeadline);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void sortByDeadline_editTaskCompletionStatusInSortedList_sortedList() {
        taskList.setTasks(getTypicalTasks());
        taskList.sortByDeadline();

        TaskList expectedTaskList = new TaskList();
        Task editedWriteTestCases = new TaskBuilder(WRITE_TEST_CASES).withCompletionStatus(true).build();
        expectedTaskList.addTask(editedWriteTestCases);
        for (Task task : getTypicalTasksSortedByDeadline()) {
            if (task.isSameTask(editedWriteTestCases)) {
                continue;
            }

            expectedTaskList.addTask(task);
        }

        taskList.setTask(WRITE_TEST_CASES, editedWriteTestCases);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void sortByById_unsortedList_sortedList() {
        taskList.setTasks(getTypicalTasksSortedByDeadline());
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.setTasks(getTypicalTasks());
        taskList.sortById();
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void sortById_sortedList_sameList() {
        taskList.setTasks(getTypicalTasks());
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.setTasks(getTypicalTasks());
        taskList.sortById();
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void sortById_editTaskInSortedList_sortedList() {
        taskList.setTasks(getTypicalTasks());

        TaskList expectedTaskList = new TaskList();
        Task editedReadBook = new TaskBuilder(READ_BOOK).withCompletionStatus(true).build();
        expectedTaskList.addTask(editedReadBook);
        for (Task task : getTypicalTasks()) {
            if (task.isSameTask(editedReadBook)) {
                continue;
            }

            expectedTaskList.addTask(task);
        }

        taskList.setTask(READ_BOOK, editedReadBook);
        taskList.sortById();
        assertEquals(expectedTaskList, taskList);
    }
}
