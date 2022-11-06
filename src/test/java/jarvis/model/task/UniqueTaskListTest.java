package jarvis.model.task;

import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalTasks.MISSION1;
import static jarvis.testutil.TypicalTasks.STUDIO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import jarvis.model.Task;
import jarvis.model.UniqueTaskList;
import jarvis.model.exceptions.DuplicateTaskException;
import jarvis.model.exceptions.TaskNotFoundException;
import jarvis.testutil.TaskBuilder;

public class UniqueTaskListTest {

    private final UniqueTaskList uniqueTaskList = new UniqueTaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(uniqueTaskList.contains(MISSION1));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        uniqueTaskList.add(MISSION1);
        assertTrue(uniqueTaskList.contains(MISSION1));
    }

    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTaskList.add(MISSION1);
        Task editedMission = new TaskBuilder(MISSION1).build();
        assertTrue(uniqueTaskList.contains(editedMission));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        uniqueTaskList.add(MISSION1);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.add(MISSION1));
    }

    @Test
    public void add_validTask_success() {
        uniqueTaskList.add(STUDIO);
        assertTrue(uniqueTaskList.contains(STUDIO));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(null, MISSION1));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(MISSION1, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.setTask(MISSION1, MISSION1));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        uniqueTaskList.add(MISSION1);
        uniqueTaskList.setTask(MISSION1, MISSION1);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(MISSION1);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasSameIdentity_success() {
        uniqueTaskList.add(MISSION1);
        Task editedMission = new TaskBuilder(MISSION1).build();
        uniqueTaskList.setTask(MISSION1, editedMission);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(editedMission);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        uniqueTaskList.add(MISSION1);
        uniqueTaskList.setTask(MISSION1, STUDIO);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(STUDIO);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        uniqueTaskList.add(MISSION1);
        uniqueTaskList.add(STUDIO);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTask(MISSION1, STUDIO));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.remove(MISSION1));
    }

    @Test
    public void remove_existingTask_removesTask() {
        uniqueTaskList.add(MISSION1);
        uniqueTaskList.remove(MISSION1);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullUniqueTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((UniqueTaskList) null));
    }

    @Test
    public void setTasks_uniqueTaskList_replacesOwnListWithProvidedUniqueTaskList() {
        uniqueTaskList.add(MISSION1);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(STUDIO);
        uniqueTaskList.setTasks(expectedUniqueTaskList);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        uniqueTaskList.add(MISSION1);
        List<Task> taskList = Collections.singletonList(STUDIO);
        uniqueTaskList.setTasks(taskList);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(STUDIO);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_listWithDuplicateTasks_throwsDuplicateTaskException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(MISSION1, MISSION1);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTasks(listWithDuplicateTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTaskList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(uniqueTaskList.equals(uniqueTaskList));

        // same internal list -> returns true
        UniqueTaskList uniqueTaskListCopy = new UniqueTaskList();
        assertTrue(uniqueTaskList.equals(uniqueTaskListCopy));

        // different type -> returns false
        assertFalse(uniqueTaskList.equals(5));

        // null -> returns false
        assertFalse(uniqueTaskList.equals(null));

        // different internal list -> returns false
        uniqueTaskListCopy.add(MISSION1);
        assertFalse(uniqueTaskList.equals(uniqueTaskListCopy));
    }
}
