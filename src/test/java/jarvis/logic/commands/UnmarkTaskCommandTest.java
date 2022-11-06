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
 * {@code UnmarkTaskCommand}.
 */
public class UnmarkTaskCommandTest {

    private static final Index taskIndex = Index.fromZeroBased(0);

    private Model model = new ModelManager(getTypicalTaskBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        List<Task> taskList = model.getFilteredTaskList();
        for (Task t: taskList) {
            t.markAsDone();
        }
    }

    @Test
    public void execute_withValidIndex_success() throws Exception {
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(taskIndex);

        Task taskToUnmark = model.getFilteredTaskList().get(0);
        CommandResult commandResult = unmarkTaskCommand.execute(model);
        assertEquals(String.format(UnmarkTaskCommand.MESSAGE_UNMARK_TASK_SUCCESS, taskToUnmark),
                commandResult.getFeedbackToUser());
        assertFalse(taskToUnmark.isDone()); // task should be marked not completed

        // mark everything as not completed, to ensure task at index 0 is marked not completed
        List<Task> taskList = model.getFilteredTaskList();
        for (Task t: taskList) {
            t.markAsNotDone();
        }
        taskToUnmark = model.getFilteredTaskList().get(0);
        commandResult = unmarkTaskCommand.execute(model);
        assertEquals(String.format(UnmarkTaskCommand.MESSAGE_UNMARK_TASK_SUCCESS, taskToUnmark),
                commandResult.getFeedbackToUser());
        assertFalse(taskToUnmark.isDone()); // task should remain not completed
    }

    @Test
    public void execute_withInvalidIndex_throwsCommandException() {
        int taskCount = model.getFilteredTaskList().size();
        assertCommandFailure(new UnmarkTaskCommand(Index.fromZeroBased(taskCount)), model,
                Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        assertCommandFailure(new UnmarkTaskCommand(Index.fromZeroBased(taskCount + 100)), model,
                Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(taskIndex);
        UnmarkTaskCommand unmarkTaskCommandDifferentIndex = new UnmarkTaskCommand(Index.fromZeroBased(1));

        // same object -> returns true
        assertTrue(unmarkTaskCommand.equals(unmarkTaskCommand));

        // different types -> returns false
        assertFalse(unmarkTaskCommand.equals(1));

        // null -> returns false
        assertFalse(unmarkTaskCommand.equals(null));

        // same lesson -> returns true
        UnmarkTaskCommand unmarkTaskCommandCopy = new UnmarkTaskCommand(taskIndex);
        assertTrue(unmarkTaskCommand.equals(unmarkTaskCommandCopy));

        // different lesson -> returns false
        assertFalse(unmarkTaskCommand.equals(unmarkTaskCommandDifferentIndex));
    }
}
