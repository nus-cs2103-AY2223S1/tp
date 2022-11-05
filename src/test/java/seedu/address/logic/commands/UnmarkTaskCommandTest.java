package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnmarkTaskCommand}.
 */
public class UnmarkTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());

    // To make things easier to test, task 0 will be marked and task 1 will be unmarked
    public UnmarkTaskCommandTest() {
        model.setTask(model.getSortedTaskList().get(0), model.getSortedTaskList().get(0).withStatus(true));
        model.setTask(model.getSortedTaskList().get(1), model.getSortedTaskList().get(1).withStatus(false));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToUnmark = model.getSortedTaskList().get(0);
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(Index.fromZeroBased(0));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getTaskList(), new UserPrefs());
        Task unmarkedTask = new Task(
                taskToUnmark.getName(), taskToUnmark.getModule(), taskToUnmark.getDeadline(), new Status(false));
        expectedModel.setTask(taskToUnmark, unmarkedTask);

        String expectedMessage = String.format(UnmarkTaskCommand.MESSAGE_UNMARK_TASK_SUCCESS, unmarkedTask);

        assertCommandSuccess(unmarkTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedTaskList().size() + 1);
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(unmarkTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST);

        Task taskToUnmark = model.getSortedTaskList().get(INDEX_FIRST.getZeroBased());
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(INDEX_FIRST);

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTaskList(), new UserPrefs());
        Task unmarkedTask = new Task(
                taskToUnmark.getName(), taskToUnmark.getModule(), taskToUnmark.getDeadline(), new Status(false));
        showTaskAtIndex(expectedModel, INDEX_FIRST);
        expectedModel.setTask(taskToUnmark, unmarkedTask);

        String expectedMessage = String.format(UnmarkTaskCommand.MESSAGE_UNMARK_TASK_SUCCESS, unmarkedTask);

        assertCommandSuccess(unmarkTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskList().getTaskList().size());

        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(unmarkTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }



    @Test
    public void excecute_unmarkMarkedTask_success() {
        Task taskToMark = model.getSortedTaskList().get(0);
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(Index.fromZeroBased(0));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getTaskList(), new UserPrefs());
        Task unmarkedTask = new Task(
                taskToMark.getName(), taskToMark.getModule(), taskToMark.getDeadline(), new Status(false));
        expectedModel.setTask(taskToMark, unmarkedTask);

        String expectedMessage = String.format(UnmarkTaskCommand.MESSAGE_UNMARK_TASK_SUCCESS, unmarkedTask);

        assertCommandSuccess(unmarkTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unmarkUnmarkedTask_throwsCommandException() {
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(Index.fromZeroBased(1));

        assertCommandFailure(unmarkTaskCommand, model, UnmarkTaskCommand.MESSAGE_TASK_ALREADY_NOT_COMPLETED);
    }

    @Test
    public void equals() {
        UnmarkTaskCommand unmarkTaskFirstCommand = new UnmarkTaskCommand(Index.fromZeroBased(0));
        UnmarkTaskCommand unmarkTaskSecondCommand = new UnmarkTaskCommand(Index.fromZeroBased(1));

        // same object -> returns true
        assertTrue(unmarkTaskFirstCommand.equals(unmarkTaskFirstCommand));

        // same values -> returns true
        UnmarkTaskCommand unmarkTaskFirstCommandCopy = new UnmarkTaskCommand(Index.fromZeroBased(0));
        assertTrue(unmarkTaskFirstCommand.equals(unmarkTaskFirstCommandCopy));

        // different types -> returns false
        assertFalse(unmarkTaskFirstCommand.equals(0));

        // null -> returns false
        assertFalse(unmarkTaskFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(unmarkTaskFirstCommand.equals(unmarkTaskSecondCommand));
    }
}
