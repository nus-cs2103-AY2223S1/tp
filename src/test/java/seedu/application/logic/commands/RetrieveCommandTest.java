package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.logic.commands.CommandTestUtil.showApplicationByArchiveStatus;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBookWithArchive;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.application.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.application.commons.core.index.Index;
import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;
import seedu.application.model.application.Application;

public class RetrieveCommandTest {
    private Model model = new ModelManager(getTypicalApplicationBookWithArchive(), new UserPrefs());

    @Test
    public void execute_validIndexFilteredArchiveList_success() {
        showApplicationByArchiveStatus(model, true);
        Application applicationToRetrieve = model.getFilteredApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());
        RetrieveCommand retrieveCommand = new RetrieveCommand(INDEX_FIRST_APPLICATION);
        String expectedMessage = String.format(RetrieveCommand.MESSAGE_RETRIEVE_APPLICATION_SUCCESS,
                applicationToRetrieve);

        ModelManager expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
        expectedModel.retrieveApplication(applicationToRetrieve);

        assertCommandSuccess(retrieveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredArchiveList_throwsCommandException() {
        showApplicationByArchiveStatus(model, true);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationList().size() + 1);

        // ensures that outOfBoundIndex is still in bounds of application book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getApplicationBook().getApplicationList().size());
        RetrieveCommand retrieveCommand = new RetrieveCommand(outOfBoundIndex);

        assertCommandFailure(retrieveCommand, model,
                RetrieveCommand.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX_RETRIEVE);
    }

    @Test
    public void execute_validIndexFilteredUnarchiveList_throwsCommandException() {
        showApplicationByArchiveStatus(model, false);
        RetrieveCommand retrieveCommand = new RetrieveCommand(INDEX_FIRST_APPLICATION);

        assertCommandFailure(retrieveCommand, model, RetrieveCommand.MESSAGE_APPLICATION_IS_NOT_ARCHIVE);
    }

    @Test
    public void execute_invalidIndexFilteredUnarchiveList_throwsCommandException() {
        showApplicationByArchiveStatus(model, true);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationList().size() + 1);

        // ensures that outOfBoundIndex is still in bounds of application book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getApplicationBook().getApplicationList().size());
        RetrieveCommand retrieveCommand = new RetrieveCommand(outOfBoundIndex);
        assertCommandFailure(retrieveCommand, model,
                RetrieveCommand.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX_RETRIEVE);
    }

    @Test
    public void equals() {
        RetrieveCommand retrieveFirstCommand = new RetrieveCommand(INDEX_FIRST_APPLICATION);
        RetrieveCommand retrieveSecondCommand = new RetrieveCommand(INDEX_SECOND_APPLICATION);

        // same object -> returns true
        assertTrue(retrieveFirstCommand.equals(retrieveFirstCommand));

        // same values -> returns true
        RetrieveCommand retrieveFirstCommandCopy = new RetrieveCommand(INDEX_FIRST_APPLICATION);
        assertTrue(retrieveFirstCommand.equals(retrieveFirstCommandCopy));

        // different types -> returns false
        assertFalse(retrieveFirstCommand.equals(1));

        // null -> returns false
        assertFalse(retrieveFirstCommand.equals(null));

        // different application -> returns false
        assertFalse(retrieveFirstCommand.equals(retrieveSecondCommand));
    }
}
