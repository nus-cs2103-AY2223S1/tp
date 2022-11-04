package seedu.hrpro.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.hrpro.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hrpro.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.hrpro.testutil.TypicalHrPro.getTypicalHrPro;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_SECOND_TASK;

import org.junit.jupiter.api.Test;

import seedu.hrpro.commons.core.Messages;
import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.model.Model;
import seedu.hrpro.model.ModelManager;
import seedu.hrpro.model.UserPrefs;

public class UnmarkTaskCommandTest {
    private Model model = new ModelManager(getTypicalHrPro(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(INDEX_FIRST_TASK);
        model.markTask(INDEX_FIRST_TASK);

        String expectedMessage = String.format(UnmarkTaskCommand
                .MESSAGE_UNMARK_TASK_SUCCESS, INDEX_FIRST_TASK.getOneBased());

        ModelManager expectedModel = new ModelManager(model.getHrPro(), new UserPrefs());
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

        Model expectedModel = new ModelManager(model.getHrPro(), new UserPrefs());
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
        // ensures that outOfBoundIndex is still in bounds of hr pro list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHrPro().getTaskList().size());

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
