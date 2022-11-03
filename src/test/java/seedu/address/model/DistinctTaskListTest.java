package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExams.EXAMONE;
import static seedu.address.testutil.TypicalExams.EXAMTWO;
//import static seedu.address.testutil.TypicalTasks.TASKONE;
//import static seedu.address.testutil.TypicalTasks.TASKTWO;
import static seedu.address.testutil.TypicalTasks.TASK_J;
import static seedu.address.testutil.TypicalTasks.TASK_K;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.DistinctTaskList;
import seedu.address.model.task.Task;
import seedu.address.testutil.ExamBuilder;


public class DistinctTaskListTest {
    private final DistinctTaskList distinctTaskList = new DistinctTaskList();

    @Test
    public void isExamLinkedToTask_returnsTrue() {
        distinctTaskList.addTask(TASK_J);
        Task editedTask = TASK_J.linkTask(EXAMONE);
        distinctTaskList.replaceTask(TASK_J, editedTask, true);
        assertTrue(distinctTaskList.isExamLinkedToTask(EXAMONE));
    }

    @Test
    public void isExamLinkedToTaskForNoLinksAtAll_returnsFalse() {
        assertFalse(distinctTaskList.isExamLinkedToTask(EXAMONE));
    }
    @Test
    public void isExamLinkedToTask_returnsFalse() {
        distinctTaskList.addTask(TASK_J);
        Task editedTask = TASK_J.linkTask(EXAMTWO);
        distinctTaskList.replaceTask(TASK_J, editedTask, true);
        assertFalse(distinctTaskList.isExamLinkedToTask(EXAMONE));
    }

    @Test
    public void isExamLinkedToTaskWithMultipleLinks_returnsTrue() {
        distinctTaskList.addTask(TASK_J);
        distinctTaskList.addTask(TASK_K);
        Task editedTask = TASK_J.linkTask(EXAMTWO);
        Task editedTask2 = TASK_K.linkTask(EXAMTWO);
        distinctTaskList.replaceTask(TASK_J, editedTask, true);
        distinctTaskList.replaceTask(TASK_K, editedTask2, true);
        assertTrue(distinctTaskList.isExamLinkedToTask(EXAMTWO));
    }

    @Test
    public void isExamLinkedToTask_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> distinctTaskList.isExamLinkedToTask(null));
    }


    @Test
    public void updateExamFieldForTask_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> distinctTaskList.updateExamFieldForTask(null, null));
    }

    @Test
    public void updateExamFieldForTaskWhereAllTasksLinkedToExam_success() {
        distinctTaskList.addTask(TASK_J);
        distinctTaskList.addTask(TASK_K);
        Task editedTask = TASK_J.linkTask(EXAMTWO);
        Task editedTask2 = TASK_K.linkTask(EXAMTWO);
        distinctTaskList.replaceTask(TASK_J, editedTask, true);
        distinctTaskList.replaceTask(TASK_K, editedTask2, true);
        distinctTaskList.updateExamFieldForTask(EXAMTWO, EXAMONE);
        assertTrue(distinctTaskList.taskList.get(0).getExam().equals(EXAMONE));
        assertTrue(distinctTaskList.taskList.get(1).getExam().equals(EXAMONE));
    }

    @Test
    public void updateExamFieldForTaskWhereSomeTasksLinkedToExam_success() {
        distinctTaskList.addTask(TASK_J);
        distinctTaskList.addTask(TASK_K);
        Task editedTask = TASK_J.linkTask(EXAMTWO);
        Task editedTask2 = TASK_K.linkTask(new ExamBuilder().build());
        distinctTaskList.replaceTask(TASK_J, editedTask, true);
        distinctTaskList.replaceTask(TASK_K, editedTask2, true);
        distinctTaskList.updateExamFieldForTask(EXAMTWO, EXAMONE);
        assertTrue(distinctTaskList.taskList.get(0).getExam().equals(EXAMONE));
        assertFalse(distinctTaskList.taskList.get(1).getExam().equals(EXAMONE));
    }

    @Test
    public void updateExamFieldForTaskWhereSomeTasksLinkedToExam2_success() {
        distinctTaskList.addTask(TASK_J);
        distinctTaskList.addTask(TASK_K);
        Task editedTask = TASK_J.linkTask(EXAMTWO);
        distinctTaskList.replaceTask(TASK_J, editedTask, true);
        distinctTaskList.updateExamFieldForTask(EXAMTWO, EXAMONE);
        assertTrue(distinctTaskList.taskList.get(0).getExam().equals(EXAMONE));
        assertFalse(distinctTaskList.taskList.get(1).isLinked());
    }
    @Test
    public void updateExamFieldForTaskWhereNoTasksLinkedToExam() {
        distinctTaskList.addTask(TASK_J);
        distinctTaskList.addTask(TASK_K);
        Task editedTask = TASK_J.linkTask(new ExamBuilder().build());
        Task editedTask2 = TASK_K.linkTask(new ExamBuilder().build());
        distinctTaskList.replaceTask(TASK_J, editedTask, true);
        distinctTaskList.replaceTask(TASK_K, editedTask2, true);
        distinctTaskList.updateExamFieldForTask(EXAMTWO, EXAMONE);
        assertFalse(distinctTaskList.taskList.get(0).getExam().equals(EXAMONE));
        assertFalse(distinctTaskList.taskList.get(1).getExam().equals(EXAMONE));
    }

    @Test
    public void updateExamFieldForTaskWhereNoLinks() {
        distinctTaskList.addTask(TASK_J);
        distinctTaskList.addTask(TASK_K);
        distinctTaskList.updateExamFieldForTask(EXAMTWO, EXAMONE);
        assertFalse(distinctTaskList.taskList.get(0).isLinked());
        assertFalse(distinctTaskList.taskList.get(1).isLinked());
    }

}
