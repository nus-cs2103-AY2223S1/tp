package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static taskbook.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static taskbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static taskbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static taskbook.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static taskbook.testutil.TypicalIndexes.INDEX_SECOND_TASK;

import org.junit.jupiter.api.Test;

import taskbook.commons.core.index.Index;
import taskbook.logic.commands.tasks.TaskMarkCommand;
import taskbook.model.Model;
import taskbook.model.ModelManager;
import taskbook.model.UserPrefs;
import taskbook.model.task.Todo;
import taskbook.testutil.TodoBuilder;
import taskbook.testutil.TypicalTaskBook;

public class TaskMarkCommandTest {
    @Test
    public void equals() {
        TaskMarkCommand taskMarkFirstCommand = new TaskMarkCommand(INDEX_FIRST_TASK);
        TaskMarkCommand taskMarkSecondCommand = new TaskMarkCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertEquals(taskMarkFirstCommand, taskMarkFirstCommand);

        // same values -> returns true
        TaskMarkCommand taskMarkFirstCommandCopy = new TaskMarkCommand(INDEX_FIRST_TASK);
        assertEquals(taskMarkFirstCommand, taskMarkFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, taskMarkFirstCommand);

        // null -> returns false
        assertNotEquals(null, taskMarkFirstCommand);

        // different task -> returns false
        assertNotEquals(taskMarkFirstCommand, taskMarkSecondCommand);
    }

    @Test
    public void execute_validMark_success() {
        Model model = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        Todo task = TypicalTaskBook.SLEEPING;
        Todo editedTask = new TodoBuilder()
                .withName(task.getName())
                .withAssignment(task.getAssignment())
                .withDescription(task.getDescription().description)
                .withIsDone(true)
                .withTags(task.getTags())
                .build();
        TaskMarkCommand markCommand = new TaskMarkCommand(INDEX_SECOND_TASK);

        String expectedMessage = String.format(TaskMarkCommand.MESSAGE_MARK_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        expectedModel.setTask(model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased()), editedTask);
        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexOutOfBounds_throwsCommandException() {
        Model model = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        Index index = Index.fromZeroBased(model.getFilteredTaskList().size());
        TaskMarkCommand markCommand = new TaskMarkCommand(index);

        assertCommandFailure(markCommand, model, MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }
}
