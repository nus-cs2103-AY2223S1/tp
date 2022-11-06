package swift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swift.logic.commands.CommandTestUtil.assertCommandFailure;
import static swift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static swift.testutil.TypicalTaskIndexes.INDEX_FIRST_TASK;
import static swift.testutil.TypicalTaskIndexes.INDEX_SECOND_TASK;
import static swift.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import swift.commons.core.Messages;
import swift.commons.core.index.Index;
import swift.model.AddressBook;
import swift.model.Model;
import swift.model.ModelManager;
import swift.model.UserPrefs;
import swift.model.task.Task;
import swift.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnmarkTaskCommand}.
 */
public class UnmarkTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute_validIndex_success() {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());
        Task unmarkedTask = model.getFilteredTaskList().get(indexLastTask.getZeroBased());

        TaskBuilder taskInList = new TaskBuilder(unmarkedTask);
        Task markedTask = taskInList.withIsDone(true).build();

        model.setTask(unmarkedTask, markedTask);

        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(indexLastTask);

        String expectedMessage =
                String.format(UnmarkTaskCommand.MESSAGE_UNMARK_TASK_SUCCESS, unmarkedTask);

        CommandResult commandResult = new CommandResult(expectedMessage, CommandType.TASKS);

        Model expectedModel =
                new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setTask(markedTask, unmarkedTask);

        assertCommandSuccess(unmarkTaskCommand, model, commandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(unmarkTaskCommand, model,
                Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_markedTask_failure() {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());

        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(indexLastTask);
        assertCommandFailure(unmarkTaskCommand, model,
                UnmarkTaskCommand.MESSAGE_TASK_ALREADY_INCOMPLETE);
    }

    @Test
    public void equals() {
        UnmarkTaskCommand unmarkFirstTaskCommand = new UnmarkTaskCommand(INDEX_FIRST_TASK);
        UnmarkTaskCommand unmarkSecondTaskCommand = new UnmarkTaskCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(unmarkFirstTaskCommand.equals(unmarkFirstTaskCommand));

        // same values -> returns true
        UnmarkTaskCommand unmarkFirstTaskCommandCopy = new UnmarkTaskCommand(INDEX_FIRST_TASK);
        assertTrue(unmarkFirstTaskCommand.equals(unmarkFirstTaskCommandCopy));

        // different types -> returns false
        assertFalse(unmarkFirstTaskCommand.equals(1));

        // null -> returns false
        assertFalse(unmarkFirstTaskCommand.equals(null));

        // different task -> returns false
        assertFalse(unmarkFirstTaskCommand.equals(unmarkSecondTaskCommand));
    }
}
