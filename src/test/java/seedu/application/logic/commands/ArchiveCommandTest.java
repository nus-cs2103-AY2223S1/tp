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

public class ArchiveCommandTest {
    private Model model = new ModelManager(getTypicalApplicationBookWithArchive(), new UserPrefs());

    @Test
    public void execute_validIndexFilteredUnarchiveList_success() {
        showApplicationByArchiveStatus(model, false);
        Application applicationToArchive = model.getFilteredApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());
        ArchiveCommand archiveCommand = new ArchiveCommand(INDEX_FIRST_APPLICATION);
        String expectedMessage = String.format(ArchiveCommand.MESSAGE_ARCHIVE_APPLICATION_SUCCESS,
                applicationToArchive);

        ModelManager expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
        expectedModel.archiveApplication(applicationToArchive);

        assertCommandSuccess(archiveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredUnarchiveList_throwsCommandException() {
        showApplicationByArchiveStatus(model, false);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationList().size() + 1);

        // ensures that outOfBoundIndex is still in bounds of application book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getApplicationBook().getApplicationList().size());
        ArchiveCommand archiveCommand = new ArchiveCommand(outOfBoundIndex);

        assertCommandFailure(archiveCommand, model, ArchiveCommand.MESSAGE_INVALID_INDEX_ARCHIVE);
    }

    @Test
    public void execute_validIndexFilteredArchiveList_throwsCommandException() {
        showApplicationByArchiveStatus(model, true);
        ArchiveCommand archiveCommand = new ArchiveCommand(INDEX_FIRST_APPLICATION);

        assertCommandFailure(archiveCommand, model, ArchiveCommand.MESSAGE_APPLICATION_EXIST_IN_ARCHIVE);
    }

    @Test
    public void execute_invalidIndexFilteredArchiveList_throwsCommandException() {
        showApplicationByArchiveStatus(model, true);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationList().size() + 1);

        // ensures that outOfBoundIndex is still in bounds of application book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getApplicationBook().getApplicationList().size());
        ArchiveCommand archiveCommand = new ArchiveCommand(outOfBoundIndex);
        assertCommandFailure(archiveCommand, model, ArchiveCommand.MESSAGE_INVALID_INDEX_ARCHIVE);
    }

    @Test
    public void equals() {
        ArchiveCommand archiveFirstCommand = new ArchiveCommand(INDEX_FIRST_APPLICATION);
        ArchiveCommand archiveSecondCommand = new ArchiveCommand(INDEX_SECOND_APPLICATION);

        // same object -> returns true
        assertTrue(archiveFirstCommand.equals(archiveFirstCommand));

        // same values -> returns true
        ArchiveCommand archiveFirstCommandCopy = new ArchiveCommand(INDEX_FIRST_APPLICATION);
        assertTrue(archiveFirstCommand.equals(archiveFirstCommandCopy));

        // different types -> returns false
        assertFalse(archiveFirstCommand.equals(1));

        // null -> returns false
        assertFalse(archiveFirstCommand.equals(null));

        // different application -> returns false
        assertFalse(archiveFirstCommand.equals(archiveSecondCommand));
    }
}
