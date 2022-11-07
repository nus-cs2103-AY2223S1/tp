package seedu.intrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.intrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intrack.logic.commands.SortSalaryCommand.MESSAGE_SUCCESS_A;
import static seedu.intrack.logic.commands.SortSalaryCommand.MESSAGE_SUCCESS_D;
import static seedu.intrack.testutil.TypicalInternships.getTypicalInTrack;

import org.junit.jupiter.api.Test;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.commons.core.index.Index;
import seedu.intrack.model.Model;
import seedu.intrack.model.ModelManager;
import seedu.intrack.model.UserPrefs;

public class SortSalaryCommandTest {
    private Model model = new ModelManager(getTypicalInTrack(), new UserPrefs());
    @Test
    public void equals() {
        final SortSalaryCommand standardCommand = new SortSalaryCommand("a");

        // same values -> returns true
        SortSalaryCommand commandWithSameValues = new SortSalaryCommand("a");
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different ordertype -> returns false
        assertFalse(standardCommand.equals(new SortSalaryCommand("d")));

    }

    @Test
    void execute_validOrderTypeCommandA_success() {
        //for ascending
        String orderType = "a";
        SortSalaryCommand sortSalaryCommand = new SortSalaryCommand(orderType);
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        String expectedMessage = String.format(MESSAGE_SUCCESS_A, model.getFilteredInternshipList().size());
        assertCommandSuccess(sortSalaryCommand, model, expectedMessage, expectedModel);

    }

    @Test
    void execute_validOrderTypeCommandD_success() {
        //for descending
        String orderType = "d";
        SortSalaryCommand sortSalaryCommand = new SortSalaryCommand(orderType);
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        String expectedMessage = String.format(MESSAGE_SUCCESS_D, model.getFilteredInternshipList().size());
        assertCommandSuccess(sortSalaryCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_invalidOrderType_throwsCommandException() {
        String orderType = "e";
        SortSalaryCommand sortSalaryCommand = new SortSalaryCommand(orderType);

        assertCommandFailure(sortSalaryCommand, model, SortSalaryCommand.SORT_COMMAND_CONSTRAINTS);

    }
}
