package seedu.intrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intrack.logic.commands.SortCommand.MESSAGE_SUCCESS_A;
import static seedu.intrack.logic.commands.SortCommand.MESSAGE_SUCCESS_D;
import static seedu.intrack.testutil.TypicalInternships.getTypicalInTrack;

import org.junit.jupiter.api.Test;

import seedu.intrack.model.Model;
import seedu.intrack.model.ModelManager;
import seedu.intrack.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
class SortCommandTest {
    private Model model = new ModelManager(getTypicalInTrack(), new UserPrefs());
    @Test
    public void equals() {
        final SortCommand standardCommand = new SortCommand("a");

        // same values -> returns true
        SortCommand commandWithSameValues = new SortCommand("a");
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different ordertype -> returns false
        assertFalse(standardCommand.equals(new SortCommand("d")));

    }

    @Test
    void execute_validOrderTypeCommandA_success() {
        //for ascending
        String orderType = "a";
        SortCommand sortCommand = new SortCommand(orderType);
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        String expectedMessage = String.format(MESSAGE_SUCCESS_A, model.getFilteredInternshipList().size());
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

    }

    @Test
    void execute_validOrderTypeCommandD_success() {
        //for descending
        String orderType = "d";
        SortCommand sortCommand = new SortCommand(orderType);
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        String expectedMessage = String.format(MESSAGE_SUCCESS_D, model.getFilteredInternshipList().size());
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }
}
