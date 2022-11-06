package jarvis.logic.commands;

import static jarvis.logic.commands.CommandTestUtil.assertCommandFailure;
import static jarvis.testutil.TypicalTasks.getTypicalTaskBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jarvis.commons.core.Messages;
import jarvis.commons.core.index.Index;
import jarvis.model.Model;
import jarvis.model.ModelManager;
import jarvis.model.Task;
import jarvis.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkTaskCommand}.
 */
public class MarkTaskCommandTest {

    private static final Index taskIndex = Index.fromZeroBased(0);

    private Model model = new ModelManager(getTypicalTaskBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        List<Task> taskList = model.getFilteredTaskList();
        for (Task t: taskList) {
            t.markAsNotDone();
        }
    }

    @Test
    public void execute_withValidIndex_success() throws Exception {
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(taskIndex);

        Task taskToMark = model.getFilteredTaskList().get(0);
        CommandResult commandResult = markTaskCommand.execute(model);
        assertEquals(String.format(MarkTaskCommand.MESSAGE_MARK_TASK_SUCCESS, taskToMark),
                commandResult.getFeedbackToUser());
        assertTrue(taskToMark.isDone()); // task should be marked completed

        // mark everything as completed, to ensure task at index 0 is marked completed
        List<Task> taskList = model.getFilteredTaskList();
        for (Task t: taskList) {
            t.markAsDone();
        }
        taskToMark = model.getFilteredTaskList().get(0);
        commandResult = markTaskCommand.execute(model);
        assertEquals(String.format(MarkTaskCommand.MESSAGE_MARK_TASK_SUCCESS, taskToMark),
                commandResult.getFeedbackToUser());
        assertTrue(taskToMark.isDone()); // task should remain completed
    }

    @Test
    public void execute_withInvalidIndex_throwsCommandException() {
        int taskCount = model.getFilteredTaskList().size();
        assertCommandFailure(new MarkTaskCommand(Index.fromZeroBased(taskCount)), model,
                Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        assertCommandFailure(new MarkTaskCommand(Index.fromZeroBased(taskCount + 100)), model,
                Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(taskIndex);
        MarkTaskCommand markTaskCommandDifferentIndex = new MarkTaskCommand(Index.fromZeroBased(1));

        // same object -> returns true
        assertTrue(markTaskCommand.equals(markTaskCommand));

        // different types -> returns false
        assertFalse(markTaskCommand.equals(1));

        // null -> returns false
        assertFalse(markTaskCommand.equals(null));

        // same lesson -> returns true
        MarkTaskCommand markTaskCommandCopy = new MarkTaskCommand(taskIndex);
        assertTrue(markTaskCommand.equals(markTaskCommandCopy));

        // different lesson -> returns false
        assertFalse(markTaskCommand.equals(markTaskCommandDifferentIndex));
    }
}
