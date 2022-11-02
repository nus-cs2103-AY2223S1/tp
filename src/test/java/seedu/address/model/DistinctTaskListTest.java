package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExams.EXAMONE;
import static seedu.address.testutil.TypicalExams.EXAMTWO;
import static seedu.address.testutil.TypicalTasks.TASKONE;
import static seedu.address.testutil.TypicalTasks.TASKTWO;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.DistinctTaskList;
import seedu.address.model.task.Task;
import seedu.address.testutil.ExamBuilder;


public class DistinctTaskListTest {
    private final DistinctTaskList distinctTaskList = new DistinctTaskList();

    @Test
    public void isExamLinkedToTask_returnsTrue() {
        distinctTaskList.addTask(TASKONE);
        Task editedTask = TASKONE.linkTask(EXAMONE);
        distinctTaskList.replaceTask(TASKONE, editedTask, true);
        assertTrue(distinctTaskList.isExamLinkedToTask(EXAMONE));
    }

    @Test
    public void isExamLinkedToTaskForNoLinksAtAll_returnsFalse() {
        assertFalse(distinctTaskList.isExamLinkedToTask(EXAMONE));
    }
    @Test
    public void isExamLinkedToTask_returnsFalse() {
        distinctTaskList.addTask(TASKONE);
        Task editedTask = TASKONE.linkTask(EXAMTWO);
        distinctTaskList.replaceTask(TASKONE, editedTask, true);
        assertFalse(distinctTaskList.isExamLinkedToTask(EXAMONE));
    }

    @Test
    public void isExamLinkedToTaskWithMultipleLinks_returnsTrue() {
        distinctTaskList.addTask(TASKONE);
        distinctTaskList.addTask(TASKTWO);
        Task editedTask = TASKONE.linkTask(EXAMTWO);
        Task editedTask2 = TASKTWO.linkTask(EXAMTWO);
        distinctTaskList.replaceTask(TASKONE, editedTask, true);
        distinctTaskList.replaceTask(TASKTWO, editedTask2, true);
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
        distinctTaskList.addTask(TASKONE);
        distinctTaskList.addTask(TASKTWO);
        Task editedTask = TASKONE.linkTask(EXAMTWO);
        Task editedTask2 = TASKTWO.linkTask(EXAMTWO);
        distinctTaskList.replaceTask(TASKONE, editedTask, true);
        distinctTaskList.replaceTask(TASKTWO, editedTask2, true);
        distinctTaskList.updateExamFieldForTask(EXAMTWO, EXAMONE);
        assertTrue(distinctTaskList.taskList.get(0).getExam().equals(EXAMONE));
        assertTrue(distinctTaskList.taskList.get(1).getExam().equals(EXAMONE));
    }

    @Test
    public void updateExamFieldForTaskWhereSomeTasksLinkedToExam_success() {
        distinctTaskList.addTask(TASKONE);
        distinctTaskList.addTask(TASKTWO);
        Task editedTask = TASKONE.linkTask(EXAMTWO);
        Task editedTask2 = TASKTWO.linkTask(new ExamBuilder().build());
        distinctTaskList.replaceTask(TASKONE, editedTask, true);
        distinctTaskList.replaceTask(TASKTWO, editedTask2, true);
        distinctTaskList.updateExamFieldForTask(EXAMTWO, EXAMONE);
        assertTrue(distinctTaskList.taskList.get(0).getExam().equals(EXAMONE));
        assertFalse(distinctTaskList.taskList.get(1).getExam().equals(EXAMONE));
    }

    @Test
    public void updateExamFieldForTaskWhereSomeTasksLinkedToExam2_success() {
        distinctTaskList.addTask(TASKONE);
        distinctTaskList.addTask(TASKTWO);
        Task editedTask = TASKONE.linkTask(EXAMTWO);
        distinctTaskList.replaceTask(TASKONE, editedTask, true);
        distinctTaskList.updateExamFieldForTask(EXAMTWO, EXAMONE);
        assertTrue(distinctTaskList.taskList.get(0).getExam().equals(EXAMONE));
        assertFalse(distinctTaskList.taskList.get(1).isLinked());
    }
    @Test
    public void updateExamFieldForTaskWhereNoTasksLinkedToExam() {
        distinctTaskList.addTask(TASKONE);
        distinctTaskList.addTask(TASKTWO);
        Task editedTask = TASKONE.linkTask(new ExamBuilder().build());
        Task editedTask2 = TASKTWO.linkTask(new ExamBuilder().build());
        distinctTaskList.replaceTask(TASKONE, editedTask, true);
        distinctTaskList.replaceTask(TASKTWO, editedTask2, true);
        distinctTaskList.updateExamFieldForTask(EXAMTWO, EXAMONE);
        assertFalse(distinctTaskList.taskList.get(0).getExam().equals(EXAMONE));
        assertFalse(distinctTaskList.taskList.get(1).getExam().equals(EXAMONE));
    }

    @Test
    public void updateExamFieldForTaskWhereNoLinks() {
        distinctTaskList.addTask(TASKONE);
        distinctTaskList.addTask(TASKTWO);
        distinctTaskList.updateExamFieldForTask(EXAMTWO, EXAMONE);
        assertFalse(distinctTaskList.taskList.get(0).isLinked());
        assertFalse(distinctTaskList.taskList.get(1).isLinked());
    }

}
