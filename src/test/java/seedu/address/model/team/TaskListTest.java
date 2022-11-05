package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalTasks.TASK_1;
import static seedu.address.testutil.TypicalTasks.TASK_1_DETAILS;
import static seedu.address.testutil.TypicalTasks.TASK_2;
import static seedu.address.testutil.TypicalTasks.TASK_2_DETAILS;
import static seedu.address.testutil.TypicalTasks.TASK_3;
import static seedu.address.testutil.TypicalTeams.TYPICAL_MEMBERS_CARL;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicateTaskException;
import seedu.address.model.person.exceptions.TaskNotFoundException;
import seedu.address.testutil.Assert;
import seedu.address.testutil.TaskBuilder;

class TaskListTest {
    private final TaskList taskList = new TaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> taskList.contains(null));
    }

    @Test
    public void contains_linkNotInList_returnsFalse() {
        assertFalse(taskList.contains(TASK_1));
    }

    @Test
    public void contains_linkInList_returnsTrue() {
        taskList.add(TASK_1);
        assertTrue(taskList.contains(TASK_1));
    }

    @Test
    public void contains_linkWithSameIdentityFieldsInList_returnsFalse() {
        taskList.add(TASK_1);
        Task editedTask = new TaskBuilder(TASK_1).withDeadline(VALID_DEADLINE).build();
        assertFalse(taskList.contains(editedTask));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> taskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        taskList.add(TASK_1);
        Assert.assertThrows(DuplicateTaskException.class, () -> taskList.add(TASK_1));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> taskList.setTask(null, TASK_1));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> taskList.setTask(TASK_1, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        Assert.assertThrows(TaskNotFoundException.class, () -> taskList.setTask(TASK_1, TASK_1));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        taskList.add(TASK_1);
        taskList.setTask(TASK_1, TASK_1);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(TASK_1);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskHasSameIdentity_success() {
        taskList.add(TASK_1);
        Task editedTask = new TaskBuilder(TASK_1).withDeadline(VALID_DEADLINE).build();
        taskList.setTask(TASK_1, editedTask);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(editedTask);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        taskList.add(TASK_1);
        taskList.setTask(TASK_1, TASK_2);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(TASK_2);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        taskList.add(TASK_1);
        taskList.add(TASK_2);
        Assert.assertThrows(DuplicateTaskException.class, () -> taskList.setTask(TASK_2, TASK_1));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> taskList.remove(null));
    }

    @Test
    public void remove_linkDoesNotExist_throwsTaskNotFoundException() {
        Assert.assertThrows(TaskNotFoundException.class, () -> taskList.remove(TASK_1));
    }

    @Test
    public void remove_existingTask_removesTask() {
        taskList.add(TASK_1);
        taskList.remove(TASK_1);
        TaskList expectedTaskList = new TaskList();
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_nullTaskList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> taskList.setTasks(null));
    }

    @Test
    public void setTasks_taskList_replacesOwnListWithProvidedTaskList() {
        taskList.add(TASK_1);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(TASK_2);
        taskList.setTasks(expectedTaskList.asUnmodifiableObservableList());
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> taskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        taskList.add(TASK_1);
        List<Task> linkList = Collections.singletonList(TASK_2);
        taskList.setTasks(linkList);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(TASK_2);
        assertEquals(expectedTaskList, taskList);
    }


    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, ()
                -> taskList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void getNumTasksPerPerson_modifyList_returnsTrue() {
        HashMap<Person, Integer> map = new HashMap<>();
        map.put(ALICE, 1);
        map.put(BENSON, 2);
        map.put(CARL, 1);
        Task taskOneReplicate = new TaskBuilder(TASK_1_DETAILS).build();
        Task taskTwoReplicate = new TaskBuilder(TASK_2_DETAILS).build();
        taskList.add(taskOneReplicate);
        taskList.add(taskTwoReplicate);
        assertEquals(map, taskList.getNumTasksPerPerson());
    }

    @Test
    public void removeAssigneeIfExists_removeAssignee_returnsTrue() {
        Task taskOneReplicate = new TaskBuilder(TASK_1_DETAILS).build();
        taskList.add(taskOneReplicate);
        taskList.removeAssigneeIfExists(BENSON);
        Task taskWithoutBenson = new TaskBuilder(TASK_1_DETAILS)
                .withAssignees(Collections.singletonList(ALICE)).build();
        TaskList newTaskList = new TaskList();
        newTaskList.add(taskWithoutBenson);
        assertEquals(newTaskList, taskList);
    }

    @Test
    public void setAssigneeIfExists_modifyAssignee_returnsTrue() {
        Task taskOneReplicate = new TaskBuilder(TASK_1_DETAILS).build();
        taskList.add(taskOneReplicate);
        taskList.setAssigneeIfExists(ALICE, CARL);
        Task taskWithCarl = new TaskBuilder(TASK_1_DETAILS)
                .withAssignees(TYPICAL_MEMBERS_CARL).build();
        TaskList newTaskList = new TaskList();
        newTaskList.add(taskWithCarl);
        assertEquals(newTaskList, taskList);
    }

}
