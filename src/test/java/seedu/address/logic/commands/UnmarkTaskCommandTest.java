package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UnmarkTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(INDEX_FIRST_TASK);
        model.markTask(INDEX_FIRST_TASK);

        String expectedMessage = String.format(UnmarkTaskCommand
                .MESSAGE_UNMARK_TASK_SUCCESS, INDEX_FIRST_TASK.getOneBased());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.markTask(INDEX_FIRST_TASK);
        expectedModel.unmarkTask(INDEX_FIRST_TASK);
        assertCommandSuccess(unmarkTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(unmarkTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        model.markTask(INDEX_FIRST_TASK);

        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(
                UnmarkTaskCommand.MESSAGE_UNMARK_TASK_SUCCESS, INDEX_FIRST_TASK.getOneBased());

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showTaskAtIndex(expectedModel, INDEX_FIRST_TASK);
        expectedModel.markTask(INDEX_FIRST_TASK);
        expectedModel.unmarkTask(INDEX_FIRST_TASK);

        assertCommandSuccess(unmarkTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        model.markTask(INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(unmarkTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(INDEX_FIRST_TASK);
        UnmarkTaskCommand anoUnmarkTaskCommand = new UnmarkTaskCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(unmarkTaskCommand.equals(unmarkTaskCommand));

        // same values -> returns true
        UnmarkTaskCommand unmarkTaskCommandCopy = new UnmarkTaskCommand(INDEX_FIRST_TASK);
        assertTrue(unmarkTaskCommand.equals(unmarkTaskCommandCopy));

        // different types -> returns false
        assertFalse(unmarkTaskCommand.equals(1));

        // null -> returns false
        assertFalse(unmarkTaskCommand.equals(null));

        // different project -> returns false
        assertFalse(unmarkTaskCommand.equals(anoUnmarkTaskCommand));
    }
}
