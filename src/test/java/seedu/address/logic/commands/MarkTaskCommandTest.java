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

public class MarkTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(MarkTaskCommand
                .MESSAGE_MARK_TASK_SUCCESS, INDEX_FIRST_TASK.getOneBased());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.markTask(INDEX_FIRST_TASK);
        assertCommandSuccess(markTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(markTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        MarkTaskCommand markTaskCommand = new MarkTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(
                MarkTaskCommand.MESSAGE_MARK_TASK_SUCCESS, INDEX_FIRST_TASK.getOneBased());

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showTaskAtIndex(expectedModel, INDEX_FIRST_TASK);
        expectedModel.markTask(INDEX_FIRST_TASK);

        assertCommandSuccess(markTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        MarkTaskCommand markTaskCommand = new MarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(markTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(INDEX_FIRST_TASK);
        MarkTaskCommand anoMarkTaskCommand = new MarkTaskCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(markTaskCommand.equals(markTaskCommand));

        // same values -> returns true
        MarkTaskCommand markTaskCommandCopy = new MarkTaskCommand(INDEX_FIRST_TASK);
        assertTrue(markTaskCommand.equals(markTaskCommandCopy));

        // different types -> returns false
        assertFalse(markTaskCommand.equals(1));

        // null -> returns false
        assertFalse(markTaskCommand.equals(null));

        // different project -> returns false
        assertFalse(markTaskCommand.equals(anoMarkTaskCommand));
    }
}
