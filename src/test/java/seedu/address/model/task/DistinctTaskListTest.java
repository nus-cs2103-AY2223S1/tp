package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExams.FINAL_EXAM;
import static seedu.address.testutil.TypicalExams.MIDTERM_EXAM;
import static seedu.address.testutil.TypicalModules.CS2040;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalTasks.TASK_A;
import static seedu.address.testutil.TypicalTasks.TASK_B;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalTasks.TASK_D;
import static seedu.address.testutil.TypicalTasks.getTypicalTasks;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.model.task.exceptions.WrongTaskModifiedException;
import seedu.address.testutil.TaskBuilder;

public class DistinctTaskListTest {

    private final DistinctTaskList distinctTaskList = new DistinctTaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> distinctTaskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(distinctTaskList.contains(TASK_A));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        distinctTaskList.addTask(TASK_A);
        assertTrue(distinctTaskList.contains(TASK_A));
    }

    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        distinctTaskList.addTask(TASK_A);
        Task editedTask = new TaskBuilder(TASK_A).withStatus("complete").build();
        assertTrue(distinctTaskList.contains(editedTask));
    }

    @Test
    public void containsModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> distinctTaskList.containsModule(null));
    }

    @Test
    public void containsModule_moduleNotInList_returnsFalse() {
        assertFalse(distinctTaskList.containsModule(CS2030));
    }

    @Test
    public void containsModule_moduleInList_returnsTrue() {
        distinctTaskList.addTask(TASK_A);
        assertTrue(distinctTaskList.containsModule(CS2030));
    }

    @Test
    public void addTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> distinctTaskList.addTask(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicatePersonException() {
        distinctTaskList.addTask(TASK_A);
        assertThrows(DuplicateTaskException.class, () -> distinctTaskList.addTask(TASK_A));
    }

    @Test
    public void replaceTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> distinctTaskList
            .replaceTask(null, TASK_A, false));
    }

    @Test
    public void replaceTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> distinctTaskList
            .replaceTask(TASK_A, null, false));
    }

    @Test
    public void replaceTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> distinctTaskList.replaceTask(TASK_A, TASK_A, true));
    }

    @Test
    public void replaceTask_expectedSameTask_success() {
        distinctTaskList.addTask(TASK_A);
        distinctTaskList.replaceTask(TASK_A, TASK_A, true);
        DistinctTaskList expectedDistinctTaskList = new DistinctTaskList();
        expectedDistinctTaskList.addTask(TASK_A);
        assertEquals(expectedDistinctTaskList, distinctTaskList);
    }

    @Test
    public void replaceTask_expectedSameTaskButIsDifferentTask_throwsWrongTaskModifiedException() {
        distinctTaskList.addTask(TASK_A);
        assertThrows(WrongTaskModifiedException.class, () -> distinctTaskList
            .replaceTask(TASK_A, TASK_B, true));
    }

    @Test
    public void replaceTask_expectedDifferentTask_success() {
        distinctTaskList.addTask(TASK_A);
        distinctTaskList.replaceTask(TASK_A, TASK_B, false);
        DistinctTaskList expectedDistinctTaskList = new DistinctTaskList();
        expectedDistinctTaskList.addTask(TASK_B);
        assertEquals(expectedDistinctTaskList, distinctTaskList);
    }

    @Test
    public void replaceTask_expectedDifferentTaskButIsSameTask_throwsDuplicateTaskException() {
        // edited task has non-unique identity
        distinctTaskList.addTask(TASK_A);
        distinctTaskList.addTask(TASK_B);
        assertThrows(DuplicateTaskException.class, () -> distinctTaskList.replaceTask(TASK_A, TASK_B, false));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> distinctTaskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> distinctTaskList.remove(TASK_A));
    }

    @Test
    public void remove_existingTask_success() {
        distinctTaskList.addTask(TASK_A);
        distinctTaskList.remove(TASK_A);
        DistinctTaskList expectedDistinctTaskList = new DistinctTaskList();
        assertEquals(expectedDistinctTaskList, distinctTaskList);
    }

    @Test
    public void getNumOfCompletedModuleTasks() {
        distinctTaskList.setTasks(getTypicalTasks());

        // null module -> throws null pointer exception
        assertThrows(NullPointerException.class, () -> distinctTaskList.getNumOfCompletedModuleTasks(null));

        // 1 completed task with module
        assertEquals(distinctTaskList.getNumOfCompletedModuleTasks(CS2040), 1);

        // no completed task with module
        assertEquals(distinctTaskList.getNumOfCompletedModuleTasks(CS2030), 0);

        // no tasks with module
        assertEquals(distinctTaskList.getNumOfCompletedModuleTasks(CS2030), 0);
    }

    @Test
    public void getTotalNumOfModuleTasks() {
        distinctTaskList.setTasks(getTypicalTasks());

        // null module -> throws null pointer exception
        assertThrows(NullPointerException.class, () -> distinctTaskList.getTotalNumOfModuleTasks(null));

        // 2 tasks with module
        assertEquals(distinctTaskList.getTotalNumOfModuleTasks(CS2040), 3);

        // no tasks with module
        assertEquals(distinctTaskList.getTotalNumOfModuleTasks(CS2103T), 0);
    }

    @Test
    public void getNumOfCompletedExamTasks() {
        distinctTaskList.setTasks(getTypicalTasks());

        // null exam -> throws null pointer exception
        assertThrows(NullPointerException.class, () -> distinctTaskList.getNumOfCompletedExamTasks(null));

        // 1 completed task with exam
        assertEquals(distinctTaskList.getNumOfCompletedExamTasks(MIDTERM_EXAM), 1);

        // no completed task with exam
        Task editedTask = new TaskBuilder(TASK_D).withStatus("incomplete").build();
        distinctTaskList.replaceTask(TASK_D, editedTask, true);
        assertEquals(distinctTaskList.getNumOfCompletedExamTasks(MIDTERM_EXAM), 0);

        // no tasks with exam
        assertEquals(distinctTaskList.getNumOfCompletedExamTasks(FINAL_EXAM), 0);
    }


    @Test
    public void getTotalNumOfExamTasks() {
        distinctTaskList.setTasks(getTypicalTasks());

        // null exam -> throws null pointer exception
        assertThrows(NullPointerException.class, () -> distinctTaskList.getTotalNumOfExamTasks(null));

        // 2 tasks with exam
        assertEquals(distinctTaskList.getTotalNumOfExamTasks(MIDTERM_EXAM), 2);

        // no tasks with exam
        assertEquals(distinctTaskList.getTotalNumOfExamTasks(FINAL_EXAM), 0);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> distinctTaskList.setTasks(null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        distinctTaskList.addTask(TASK_A);
        List<Task> taskList = Collections.singletonList(TASK_B);
        distinctTaskList.setTasks(taskList);
        DistinctTaskList expectedDistinctTaskList = new DistinctTaskList();
        expectedDistinctTaskList.addTask(TASK_B);
        assertEquals(expectedDistinctTaskList, distinctTaskList);
    }

    @Test
    public void getUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> distinctTaskList.getUnmodifiableTaskList().remove(0));
    }
}
