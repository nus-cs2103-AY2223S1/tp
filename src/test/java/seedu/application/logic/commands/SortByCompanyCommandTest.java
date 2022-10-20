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
 * Contains integration tests (interaction with the Model) for {@code SortByCompanyCommand}.
 */
class SortByCompanyCommandTest {

    private final Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @Test
    public void execute_sort_success() {
        SortCommand sortCommand = new SortByCompanyCommand(false);

        String expectedMessage = String.format(SortByCompanyCommand.MESSAGE_SUCCESS, "");

        ModelManager expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
        expectedModel.sortApplicationListByCompany(false);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortReverse_success() {
        SortCommand sortCommand = new SortByCompanyCommand(true);

        String expectedMessage = String.format(SortByCompanyCommand.MESSAGE_SUCCESS, " reverse");

        ModelManager expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
        expectedModel.sortApplicationListByCompany(true);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        SortCommand sortByCompanyCommand = new SortByCompanyCommand(false);
        SortCommand sortByCompanyReversedCommand = new SortByCompanyCommand(true);
        SortCommand sortByPositionCommand = new SortByPositionCommand(false);

        // same object -> returns true
        assertEquals(sortByCompanyCommand, sortByCompanyCommand);

        // same values -> returns true
        SortCommand sortByCompanyCommandCopy = new SortByCompanyCommand(false);
        assertEquals(sortByCompanyCommand, sortByCompanyCommandCopy);

        // different types -> returns false
        assertNotEquals(sortByCompanyCommand, 1);

        // null -> returns false
        assertNotEquals(sortByCompanyCommand, null);

        // different values -> return false
        assertNotEquals(sortByCompanyCommand, sortByCompanyReversedCommand);

        // different sort command subtype -> return false
        assertNotEquals(sortByCompanyCommand, sortByPositionCommand);
    }
}
