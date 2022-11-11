package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static taskbook.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static taskbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static taskbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static taskbook.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static taskbook.testutil.TypicalIndexes.INDEX_SECOND_TASK;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import taskbook.commons.core.index.Index;
import taskbook.logic.commands.tasks.TaskUnmarkCommand;
import taskbook.model.Model;
import taskbook.model.ModelManager;
import taskbook.model.UserPrefs;
import taskbook.model.task.Deadline;
import taskbook.testutil.DeadlineBuilder;
import taskbook.testutil.TypicalTaskBook;

public class TaskUnmarkCommandTest {
    @Test
    public void equals() {
        TaskUnmarkCommand taskUnmarkFirstCommand = new TaskUnmarkCommand(INDEX_FIRST_TASK);
        TaskUnmarkCommand taskUnmarkSecondCommand = new TaskUnmarkCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertEquals(taskUnmarkFirstCommand, taskUnmarkFirstCommand);

        // same values -> returns true
        TaskUnmarkCommand taskUnmarkFirstCommandCopy = new TaskUnmarkCommand(INDEX_FIRST_TASK);
        assertEquals(taskUnmarkFirstCommand, taskUnmarkFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, taskUnmarkFirstCommand);

        // null -> returns false
        assertNotEquals(null, taskUnmarkFirstCommand);

        // different task -> returns false
        assertNotEquals(taskUnmarkFirstCommand, taskUnmarkSecondCommand);
    }

    @Test
    public void execute_validUnmark_success() {
        Model model = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        Deadline task = TypicalTaskBook.EATING;
        List<String> tags = new ArrayList<>();

        Deadline editedTask = new DeadlineBuilder()
                .withName(task.getName())
                .withAssignment(task.getAssignment())
                .withDescription(task.getDescription().description)
                .withIsDone(false)
                .withDeadlineDate(task.getDate())
                .withTags(task.getTags())
                .build();
        TaskUnmarkCommand unmarkCommand = new TaskUnmarkCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(TaskUnmarkCommand.MESSAGE_UNMARK_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        expectedModel.setTask(model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased()), editedTask);
        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexOutOfBounds_throwsCommandException() {
        Model model = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        Index index = Index.fromZeroBased(model.getFilteredTaskList().size());
        TaskUnmarkCommand unmarkCommand = new TaskUnmarkCommand(index);

        assertCommandFailure(unmarkCommand, model, MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }
}
