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
 * {@code MarkTaskCommand}.
 */
public class MarkTaskCommandTest {
  
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute_validIndex_success() {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());
        Task lastTask = model.getFilteredTaskList().get(indexLastTask.getZeroBased());

        TaskBuilder taskInList = new TaskBuilder(lastTask);
        Task markedTask = taskInList.withIsDone(true).build();

        MarkTaskCommand markTaskCommand = new MarkTaskCommand(indexLastTask);

        String expectedMessage =
                String.format(MarkTaskCommand.MESSAGE_MARK_TASK_SUCCESS, markedTask);

        CommandResult commandResult = new CommandResult(expectedMessage, CommandType.TASKS);

        Model expectedModel =
                new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setTask(lastTask, markedTask);

        assertCommandSuccess(markTaskCommand, model, commandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(markTaskCommand, model,
                Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_markedTask_failure() {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());
        Task lastTask = model.getFilteredTaskList().get(indexLastTask.getZeroBased());

        TaskBuilder taskInList = new TaskBuilder(lastTask);
        Task markedTask = taskInList.withIsDone(true).build();

        model.setTask(lastTask, markedTask);

        MarkTaskCommand markTaskCommand = new MarkTaskCommand(indexLastTask);
        assertCommandFailure(markTaskCommand, model, MarkTaskCommand.MESSAGE_TASK_ALREADY_COMPLETED);
    }

    @Test
    public void equals() {
        MarkTaskCommand markFirstTaskCommand = new MarkTaskCommand(INDEX_FIRST_TASK);
        MarkTaskCommand markSecondTaskCommand = new MarkTaskCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(markFirstTaskCommand.equals(markFirstTaskCommand));

        // same values -> returns true
        MarkTaskCommand markFirstTaskCommandCopy = new MarkTaskCommand(INDEX_FIRST_TASK);
        assertTrue(markFirstTaskCommand.equals(markFirstTaskCommandCopy));

        // different types -> returns false
        assertFalse(markFirstTaskCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstTaskCommand.equals(null));

        // different task -> returns false
        assertFalse(markFirstTaskCommand.equals(markSecondTaskCommand));
    }
}
