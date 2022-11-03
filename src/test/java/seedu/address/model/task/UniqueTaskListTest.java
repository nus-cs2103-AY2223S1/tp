package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.COOK;
import static seedu.address.testutil.TypicalTasks.STUDY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.TaskBuilder;

public class UniqueTaskListTest {

    private final UniqueTaskList uniqueTaskList = new UniqueTaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(uniqueTaskList.contains(STUDY));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        uniqueTaskList.add(STUDY);
        assertTrue(uniqueTaskList.contains(STUDY));
    }

    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTaskList.add(STUDY);
        Task editedStudy = new TaskBuilder(STUDY).withIsDone(!STUDY.getIsDone()).build();
        assertTrue(uniqueTaskList.contains(editedStudy));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        uniqueTaskList.add(STUDY);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.add(STUDY));
    }

    @Test
    public void add_taskNotInList_addsTask() {
        uniqueTaskList.add(STUDY);
        assertTrue(uniqueTaskList.contains(STUDY));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.remove(STUDY));
    }

    @Test
    public void remove_existingTask_removesTask() {
        uniqueTaskList.add(STUDY);
        uniqueTaskList.remove(STUDY);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        assertEquals(uniqueTaskList, expectedUniqueTaskList);
    }

    @Test
    public void setTasks_nullUniqueTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((UniqueTaskList) null));
    }

    @Test
    public void setTasks_uniqueTaskList_replacesOwnListWithProvidedUniqueTaskList() {
        uniqueTaskList.add(STUDY);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(COOK);
        uniqueTaskList.setTasks(expectedUniqueTaskList);
        assertEquals(uniqueTaskList, expectedUniqueTaskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((UniqueTaskList) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedUniqueTaskList() {
        uniqueTaskList.add(STUDY);
        List<Task> taskList = Collections.singletonList(COOK);
        uniqueTaskList.setTasks(taskList);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(COOK);
        assertEquals(uniqueTaskList, expectedUniqueTaskList);
    }

    @Test
    public void setTasks_listWithDuplicateTasks_throwsDuplicateTaskException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(STUDY, STUDY);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTasks(listWithDuplicateTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTaskList.asUnmodifiableObservableList().remove(0));
    }
}
