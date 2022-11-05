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
 * {@code MarkTaskCommand}.
 */
public class MarkTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());

    // To make things easier to test, task 0 will be unmarked and task 1 will be marked
    public MarkTaskCommandTest() {
        model.setTask(model.getSortedTaskList().get(0), model.getSortedTaskList().get(0).withStatus(false));
        model.setTask(model.getSortedTaskList().get(1), model.getSortedTaskList().get(1).withStatus(true));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToMark = model.getSortedTaskList().get(INDEX_FIRST.getZeroBased());
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(INDEX_FIRST);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getTaskList(), new UserPrefs());
        Task markedTask = new Task(
                taskToMark.getName(), taskToMark.getModule(), taskToMark.getDeadline(), new Status(true));
        expectedModel.setTask(taskToMark, markedTask);

        String expectedMessage = String.format(MarkTaskCommand.MESSAGE_MARK_TASK_SUCCESS, markedTask);

        assertCommandSuccess(markTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedTaskList().size() + 1);
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(markTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST);

        Task taskToMark = model.getSortedTaskList().get(INDEX_FIRST.getZeroBased());
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(INDEX_FIRST);

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTaskList(), new UserPrefs());
        Task markedTask = new Task(
                taskToMark.getName(), taskToMark.getModule(), taskToMark.getDeadline(), new Status(true));
        showTaskAtIndex(expectedModel, INDEX_FIRST);
        expectedModel.setTask(taskToMark, markedTask);

        String expectedMessage = String.format(MarkTaskCommand.MESSAGE_MARK_TASK_SUCCESS, markedTask);

        assertCommandSuccess(markTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskList().getTaskList().size());

        MarkTaskCommand markTaskCommand = new MarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(markTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void excecute_markUnmarkedTask_success() {
        Task taskToMark = model.getSortedTaskList().get(0);
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(Index.fromZeroBased(0));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getTaskList(), new UserPrefs());
        Task markedTask = new Task(
                taskToMark.getName(), taskToMark.getModule(), taskToMark.getDeadline(), new Status(true));
        expectedModel.setTask(taskToMark, markedTask);

        String expectedMessage = String.format(MarkTaskCommand.MESSAGE_MARK_TASK_SUCCESS, markedTask);

        assertCommandSuccess(markTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markMarkedTask_throwsCommandException() {
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(Index.fromZeroBased(1));

        assertCommandFailure(markTaskCommand, model, MarkTaskCommand.MESSAGE_TASK_ALREADY_COMPLETED);
    }

    @Test
    public void equals() {
        MarkTaskCommand markTaskFirstCommand = new MarkTaskCommand(Index.fromZeroBased(0));
        MarkTaskCommand markTaskSecondCommand = new MarkTaskCommand(Index.fromZeroBased(1));

        // same object -> returns true
        assertTrue(markTaskFirstCommand.equals(markTaskFirstCommand));

        // same values -> returns true
        MarkTaskCommand markTaskFirstCommandCopy = new MarkTaskCommand(Index.fromZeroBased(0));
        assertTrue(markTaskFirstCommand.equals(markTaskFirstCommandCopy));

        // different types -> returns false
        assertFalse(markTaskFirstCommand.equals(0));

        // null -> returns false
        assertFalse(markTaskFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(markTaskFirstCommand.equals(markTaskSecondCommand));
    }

}
