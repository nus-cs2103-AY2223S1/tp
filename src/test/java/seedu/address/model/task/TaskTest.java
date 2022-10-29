package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_COOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_COOK;
import static seedu.address.testutil.TypicalTasks.COOK;
import static seedu.address.testutil.TypicalTasks.STUDY;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void markAsDone() {
        // isDone is initially false -> isDone becomes true
        Task editedStudy = new TaskBuilder(STUDY).withIsDone(false).build();
        editedStudy.markAsDone();
        assertTrue(editedStudy.getIsDone());

        // isDone is initially true -> isDone stays true
        editedStudy = new TaskBuilder(STUDY).withIsDone(true).build();
        editedStudy.markAsDone();
        assertTrue(editedStudy.getIsDone());
    }

    @Test
    public void markAsNotDone() {
        // isDone is initially false -> isDone stays false
        Task editedStudy = new TaskBuilder(STUDY).withIsDone(false).build();
        editedStudy.markAsNotDone();
        assertFalse(editedStudy.getIsDone());

        // isDone is initially true -> isDone becomes false
        editedStudy = new TaskBuilder(STUDY).withIsDone(true).build();
        editedStudy.markAsNotDone();
        assertFalse(editedStudy.getIsDone());
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(STUDY.isSameTask(STUDY));

        // null -> returns false
        assertFalse(STUDY.isSameTask(null));

        // name differs, all other attributes same -> returns false
        Task editedStudy = new TaskBuilder(STUDY).withName(VALID_TASK_NAME_COOK).build();
        assertFalse(STUDY.isSameTask(editedStudy));

        // deadline differs, all other attributes same -> returns false
        editedStudy = new TaskBuilder(STUDY).withDeadline(VALID_TASK_DEADLINE_COOK).build();
        assertFalse(STUDY.isSameTask(editedStudy));

        // isDone differs, all other attributes same -> return true
        editedStudy = new TaskBuilder(STUDY).withIsDone(true).build();
        assertTrue(STUDY.isSameTask(editedStudy));

        // name differs in case, all other attributes same -> returns false
        Task editedCook = new TaskBuilder(COOK).withName(VALID_TASK_NAME_COOK.toLowerCase()).build();
        assertFalse(COOK.isSameTask(editedCook));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_TASK_NAME_COOK + "  ";
        editedCook = new TaskBuilder(COOK).withName(nameWithTrailingSpaces).build();
        assertFalse(COOK.isSameTask(editedCook));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task studyCopy = new TaskBuilder(STUDY).build();
        assertTrue(STUDY.equals(studyCopy));

        // same object -> returns true
        assertTrue(STUDY.equals(STUDY));

        // null -> returns false
        assertFalse(STUDY.equals(null));

        // different type -> returns false
        assertFalse(STUDY.equals(1));

        // different task -> returns false
        assertFalse(STUDY.equals(COOK));

        // different name -> returns false
        Task editedStudy = new TaskBuilder(STUDY).withName(VALID_TASK_NAME_COOK).build();
        assertFalse(STUDY.equals(editedStudy));

        // different deadline -> returns false
        editedStudy = new TaskBuilder(STUDY).withDeadline(VALID_TASK_DEADLINE_COOK).build();
        assertFalse(STUDY.equals(editedStudy));

        // different isDone -> returns false
        editedStudy = new TaskBuilder(STUDY).withIsDone(true).build();
        assertFalse(STUDY.equals(editedStudy));
    }
}
