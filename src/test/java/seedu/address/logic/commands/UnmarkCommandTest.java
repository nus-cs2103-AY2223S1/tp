package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_MARKED_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_UNMARKED_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnmarkCommand}.
 */
public class UnmarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToUnmark = model.getFilteredTaskList().get(INDEX_MARKED_TASK.getZeroBased());
        // ensures that the task is marked
        assertTrue(taskToUnmark.isComplete());

        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_MARKED_TASK);
        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_TASK_SUCCESS, taskToUnmark);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task editedTask = new TaskBuilder(taskToUnmark).withStatus("incomplete").build();
        expectedModel.replaceTask(model.getFilteredTaskList().get(INDEX_MARKED_TASK.getZeroBased()),
            editedTask, true);

        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        // index "size of list + 1" chosen as boundary value for partition [size of list + 1...INT_MAX]
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        UnmarkCommand unmarkCommand = new UnmarkCommand(outOfBoundIndex);
        String expectedMessage = String.format(
            Messages.MESSAGE_INVALID_TASK_INDEX_TOO_LARGE, model.getFilteredTaskList().size() + 1);

        assertCommandFailure(unmarkCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidTaskUnfilteredList_throwsCommandException() {
        // unmarked task -> throws error
        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_UNMARKED_TASK);
        String expectedMessage = UnmarkCommand.MESSAGE_TASK_ALREADY_UNMARKED;

        assertCommandFailure(unmarkCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_MARKED_TASK);

        Task taskToUnmark = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        // ensures that the task is marked
        assertTrue(taskToUnmark.isComplete());

        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_FIRST_TASK);
        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_TASK_SUCCESS, taskToUnmark);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task editedTask = new TaskBuilder(taskToUnmark).withStatus("incomplete").build();
        expectedModel.replaceTask(model.getFilteredTaskList().get(0), editedTask, true);

        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        // index "2" chosen as boundary value for partition [size of list + 1...INT_MAX]
        showTaskAtIndex(model, INDEX_MARKED_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        UnmarkCommand unmarkCommand = new UnmarkCommand(outOfBoundIndex);
        String expectedMessage = String.format(
            Messages.MESSAGE_INVALID_TASK_INDEX_TOO_LARGE, model.getFilteredTaskList().size() + 1);

        assertCommandFailure(unmarkCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        UnmarkCommand unmarkFirstCommand = new UnmarkCommand(INDEX_FIRST_TASK);
        UnmarkCommand unmarkSecondCommand = new UnmarkCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommand));

        // same values -> returns true
        UnmarkCommand unmarkFirstCommandCopy = new UnmarkCommand(INDEX_FIRST_TASK);
        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommandCopy));

        // different types -> returns false
        assertFalse(unmarkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unmarkFirstCommand.equals(null));

        // different index -> returns false
        assertFalse(unmarkFirstCommand.equals(unmarkSecondCommand));
    }

}
