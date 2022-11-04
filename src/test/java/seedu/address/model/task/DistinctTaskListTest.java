package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExams.FINAL_EXAM;
import static seedu.address.testutil.TypicalExams.MIDTERM_EXAM;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalModules.CS2040;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalTasks.TASK_A;
import static seedu.address.testutil.TypicalTasks.TASK_B;
import static seedu.address.testutil.TypicalTasks.TASK_C;
import static seedu.address.testutil.TypicalTasks.TASK_D;
import static seedu.address.testutil.TypicalTasks.TASK_H;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.commons.Criteria;
import seedu.address.model.exam.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.model.task.exceptions.WrongTaskModifiedException;
import seedu.address.testutil.ExamBuilder;
import seedu.address.testutil.ModuleBuilder;
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
        distinctTaskList.addTask(TASK_D);
        distinctTaskList.addTask(TASK_C);
        distinctTaskList.addTask(TASK_B);

        // null module -> throws null pointer exception
        assertThrows(NullPointerException.class, () -> distinctTaskList.getNumOfCompletedModuleTasks(null));

        // 1 completed task with module
        assertEquals(distinctTaskList.getNumOfCompletedModuleTasks(CS2040), 1);

        // adding another completed task linked to the module -> count increases by 1
        Task anotherTask = new TaskBuilder(TASK_D).withTaskDescription("another task").build();
        distinctTaskList.addTask(anotherTask);
        assertEquals(distinctTaskList.getNumOfCompletedModuleTasks(CS2040), 2);

        // no completed task with module
        assertEquals(distinctTaskList.getNumOfCompletedModuleTasks(CS2030), 0);

        // no tasks with module
        assertEquals(distinctTaskList.getNumOfCompletedModuleTasks(CS2103T), 0);
    }

    @Test
    public void getTotalNumOfModuleTasks() {
        distinctTaskList.addTask(TASK_C);
        distinctTaskList.addTask(TASK_D);

        // null module -> throws null pointer exception
        assertThrows(NullPointerException.class, () -> distinctTaskList.getTotalNumOfModuleTasks(null));

        // 2 tasks with module
        assertEquals(distinctTaskList.getTotalNumOfModuleTasks(CS2040), 2);

        // adding another task with the same module -> count increases by 1
        Task anotherTask = new TaskBuilder(TASK_C).withTaskDescription("another task").build();
        distinctTaskList.addTask(anotherTask);
        assertEquals(distinctTaskList.getTotalNumOfModuleTasks(CS2040), 3);

        // no tasks with module
        assertEquals(distinctTaskList.getTotalNumOfModuleTasks(CS2103T), 0);
    }

    @Test
    public void getNumOfCompletedExamTasks() {

        // null exam -> throws null pointer exception
        assertThrows(NullPointerException.class, () -> distinctTaskList.getNumOfCompletedExamTasks(null));

        // no completed task with exam
        Task incompleteTask = new TaskBuilder(TASK_D).withTaskDescription("incomplete task")
            .withStatus("incomplete").build();
        distinctTaskList.addTask(incompleteTask);
        assertEquals(distinctTaskList.getNumOfCompletedExamTasks(MIDTERM_EXAM), 0);

        // 1 completed task with exam
        distinctTaskList.addTask(TASK_D);
        assertEquals(distinctTaskList.getNumOfCompletedExamTasks(MIDTERM_EXAM), 1);

        // adding another completed task linked to the exam -> count increases by 1
        Task anotherTask = new TaskBuilder(TASK_D).withTaskDescription("another task").build();
        distinctTaskList.addTask(anotherTask);
        assertEquals(distinctTaskList.getNumOfCompletedExamTasks(MIDTERM_EXAM), 2);

        // no tasks with exam
        assertEquals(distinctTaskList.getNumOfCompletedExamTasks(FINAL_EXAM), 0);
    }


    @Test
    public void getTotalNumOfExamTasks() {
        distinctTaskList.addTask(TASK_D);

        // null exam -> throws null pointer exception
        assertThrows(NullPointerException.class, () -> distinctTaskList.getTotalNumOfExamTasks(null));

        // 1 task with exam
        assertEquals(distinctTaskList.getTotalNumOfExamTasks(MIDTERM_EXAM), 1);

        // adding another task with the same exam -> count increases by 1
        Task anotherTask = new TaskBuilder(TASK_D).withTaskDescription("another task").build();
        distinctTaskList.addTask(anotherTask);
        assertEquals(distinctTaskList.getTotalNumOfExamTasks(MIDTERM_EXAM), 2);

        // no tasks with exam
        assertEquals(distinctTaskList.getTotalNumOfExamTasks(FINAL_EXAM), 0);
    }

    @Test
    public void deleteTasksWithModule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> distinctTaskList.deleteTasksWithModule(null));
    }

    @Test
    public void deleteTasksWithModule_hasTasksWithModule_success() {
        distinctTaskList.addTask(TASK_A);
        distinctTaskList.addTask(TASK_C);
        distinctTaskList.deleteTasksWithModule(CS2030);
        DistinctTaskList expectedList = new DistinctTaskList();
        expectedList.addTask(TASK_C);
        assertEquals(distinctTaskList, expectedList);
    }

    @Test
    public void deleteTasksWithModule_noTasksWithModule_success() {
        distinctTaskList.addTask(TASK_A);
        distinctTaskList.deleteTasksWithModule(CS2040);
        DistinctTaskList expectedList = new DistinctTaskList();
        expectedList.addTask(TASK_A);
        assertEquals(distinctTaskList, expectedList);
    }

    @Test
    public void unlinkTasksFromExam_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> distinctTaskList.unlinkTasksFromExam(null));
    }

    @Test
    public void unlinkTasksFromExam_success() {
        distinctTaskList.addTask(TASK_D);
        distinctTaskList.unlinkTasksFromExam(MIDTERM_EXAM);
        assertFalse(distinctTaskList.taskList.get(0).isLinked());
    }

    @Test
    public void isExamLinkedToTask() {
        // null
        assertThrows(NullPointerException.class, () -> distinctTaskList.isExamLinkedToTask(null));

        // empty list
        assertFalse(distinctTaskList.isExamLinkedToTask(FINAL_EXAM));

        // no tasks linked to exam
        distinctTaskList.addTask(TASK_D);
        assertFalse(distinctTaskList.isExamLinkedToTask(FINAL_EXAM));

        // 1 task linked to exam
        assertTrue(distinctTaskList.isExamLinkedToTask(MIDTERM_EXAM));
    }

    @Test
    public void sortTasks_byPriority() {
        Criteria priority = new Criteria("priority");
        distinctTaskList.addTask(TASK_B); // task with low priority
        distinctTaskList.addTask(TASK_D); // task with high priority
        DistinctTaskList expectedList = new DistinctTaskList();
        expectedList.setTasks(new ArrayList<>(Arrays.asList(TASK_D, TASK_B)));
        distinctTaskList.sortTasks(priority);
        assertEquals(expectedList, distinctTaskList);
    }

    @Test
    public void sortTasks_byDeadline() {
        Criteria deadline = new Criteria("deadline");
        distinctTaskList.addTask(TASK_H); // task with a further deadline
        distinctTaskList.addTask(TASK_D); // task with a closer deadline
        DistinctTaskList expectedList = new DistinctTaskList();
        expectedList.setTasks(new ArrayList<>(Arrays.asList(TASK_D, TASK_H)));
        distinctTaskList.sortTasks(deadline);
        assertEquals(expectedList, distinctTaskList);
    }

    @Test
    public void sortTasks_byModule() {
        Criteria module = new Criteria("module");
        distinctTaskList.addTask(TASK_H);
        distinctTaskList.addTask(TASK_D);
        distinctTaskList.addTask(TASK_A);
        DistinctTaskList expectedList = new DistinctTaskList();
        expectedList.setTasks(new ArrayList<>(Arrays.asList(TASK_A, TASK_D, TASK_H)));
        distinctTaskList.sortTasks(module);
        assertEquals(expectedList, distinctTaskList);
    }

    @Test
    public void sortTasks_byDescription() {
        Criteria description = new Criteria("description");
        distinctTaskList.addTask(TASK_H);
        distinctTaskList.addTask(TASK_D);
        distinctTaskList.addTask(TASK_A);
        DistinctTaskList expectedList = new DistinctTaskList();
        expectedList.setTasks(new ArrayList<>(Arrays.asList(TASK_A, TASK_D, TASK_H)));
        distinctTaskList.sortTasks(description);
        assertEquals(expectedList, distinctTaskList);
    }

    @Test
    public void updateExamFieldForTask_null_throwsNullPointerException() {
        // null previousExam
        assertThrows(NullPointerException.class, () ->
            distinctTaskList.updateExamFieldForTask(null, MIDTERM_EXAM));

        // null newExam
        assertThrows(NullPointerException.class, () ->
            distinctTaskList.updateExamFieldForTask(MIDTERM_EXAM, null));
    }

    @Test
    public void updateExamFieldForTask_success() {
        // task linked to previousExam
        Task taskLinkedToPreviousExam = new TaskBuilder(TASK_A).withExam(FINAL_EXAM).build();
        distinctTaskList.addTask(taskLinkedToPreviousExam);

        // task linked to a different exam
        Task taskLinkedToDifferentExam = new TaskBuilder(TASK_C).withExam(MIDTERM_EXAM).build();
        distinctTaskList.addTask(taskLinkedToDifferentExam);

        // task not linked
        distinctTaskList.addTask(TASK_B);

        Exam editedExam = new ExamBuilder(FINAL_EXAM).withDescription("edited name").build();
        distinctTaskList.updateExamFieldForTask(FINAL_EXAM, editedExam);
        assertEquals(distinctTaskList.taskList.get(0).getExam(), editedExam);
        assertNotEquals(distinctTaskList.taskList.get(1).getExam(), editedExam);
        assertNotEquals(distinctTaskList.taskList.get(2).getExam(), editedExam);
    }

    @Test
    public void updateModuleFieldForTask_null_throwsNullPointerException() {
        // null previousModule
        assertThrows(NullPointerException.class, () -> distinctTaskList.updateModuleFieldForTask(null, CS2030));

        // null newModule
        assertThrows(NullPointerException.class, () -> distinctTaskList.updateModuleFieldForTask(CS2030, null));
    }

    @Test
    public void updateModuleFieldForTask_success() {
        // task with same module
        distinctTaskList.addTask(TASK_A);

        // task with different module
        distinctTaskList.addTask(TASK_C);

        Module editedModule = new ModuleBuilder(CS2030).withModuleName("edited name").build();
        distinctTaskList.updateModuleFieldForTask(CS2030, editedModule);
        assertTrue(distinctTaskList.taskList.get(0).getModule().hasAllSameFields(editedModule));
        assertFalse(distinctTaskList.taskList.get(1).getModule().hasAllSameFields(editedModule));
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
