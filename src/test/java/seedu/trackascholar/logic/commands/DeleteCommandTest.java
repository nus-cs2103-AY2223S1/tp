package seedu.trackascholar.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackascholar.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.trackascholar.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.trackascholar.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static seedu.trackascholar.testutil.TypicalApplicants.getTypicalTrackAScholar;
import static seedu.trackascholar.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;
import static seedu.trackascholar.testutil.TypicalIndexes.INDEX_SECOND_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.commons.core.Messages;
import seedu.trackascholar.commons.core.index.Index;
import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.ModelManager;
import seedu.trackascholar.model.UserPrefs;
import seedu.trackascholar.model.applicant.Applicant;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalTrackAScholar(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Applicant applicantToDelete = model.getFilteredApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_APPLICANT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_APPLICANT_SUCCESS, applicantToDelete);

        ModelManager expectedModel = new ModelManager(model.getTrackAScholar(), new UserPrefs());
        expectedModel.deleteApplicant(applicantToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showApplicantAtIndex(model, INDEX_FIRST_APPLICANT);

        Applicant applicantToDelete = model.getFilteredApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_APPLICANT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_APPLICANT_SUCCESS, applicantToDelete);

        Model expectedModel = new ModelManager(model.getTrackAScholar(), new UserPrefs());
        expectedModel.deleteApplicant(applicantToDelete);
        showNoApplicant(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicantAtIndex(model, INDEX_FIRST_APPLICANT);

        Index outOfBoundIndex = INDEX_SECOND_APPLICANT;
        // ensures that outOfBoundIndex is still in bounds of TrackAScholar
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTrackAScholar().getApplicantList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_APPLICANT);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_APPLICANT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_APPLICANT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different applicant -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoApplicant(Model model) {
        model.updateFilteredApplicantList(p -> false);

        assertTrue(model.getFilteredApplicantList().isEmpty());
    }
}
