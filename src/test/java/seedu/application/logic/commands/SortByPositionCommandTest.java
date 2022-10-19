package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBook;

import org.junit.jupiter.api.Test;

import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code SortByPositionCommand}.
 */
class SortByPositionCommandTest {

    private final Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @Test
    public void execute_sort_success() {
        SortCommand sortCommand = new SortByPositionCommand(false);

        String expectedMessage = String.format(SortByPositionCommand.MESSAGE_SUCCESS, "");

        ModelManager expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
        expectedModel.sortApplicationListByPosition(false);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortReverse_success() {
        SortCommand sortCommand = new SortByPositionCommand(true);

        String expectedMessage = String.format(SortByPositionCommand.MESSAGE_SUCCESS, " reverse");

        ModelManager expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
        expectedModel.sortApplicationListByPosition(true);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        SortCommand sortByPositionCommand = new SortByPositionCommand(false);
        SortCommand sortByPositionReversedCommand = new SortByPositionCommand(true);
        SortCommand sortByDateCommand = new SortByDateCommand(false);

        // same object -> returns true
        assertEquals(sortByPositionCommand, sortByPositionCommand);

        // same values -> returns true
        SortCommand sortByPositionCommandCopy = new SortByPositionCommand(false);
        assertEquals(sortByPositionCommand, sortByPositionCommandCopy);

        // different types -> returns false
        assertNotEquals(sortByPositionCommand, 1);

        // null -> returns false
        assertNotEquals(sortByPositionCommand, null);

        // different values -> return false
        assertNotEquals(sortByPositionCommand, sortByPositionReversedCommand);

        // different sort command subtype -> return false
        assertNotEquals(sortByPositionCommand, sortByDateCommand);
    }
}
