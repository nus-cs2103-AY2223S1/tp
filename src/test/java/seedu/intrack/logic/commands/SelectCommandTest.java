package seedu.intrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.intrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intrack.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.intrack.testutil.Assert.assertThrows;
import static seedu.intrack.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.intrack.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.intrack.testutil.TypicalInternships.getTypicalInTrack;

import org.junit.jupiter.api.Test;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.commons.core.index.Index;
import seedu.intrack.model.Model;
import seedu.intrack.model.ModelManager;
import seedu.intrack.model.UserPrefs;
import seedu.intrack.model.internship.Internship;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SelectCommand}.
 */
public class SelectCommandTest {

    private Model model = new ModelManager(getTypicalInTrack(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SelectCommand(null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Internship internshipToSelect = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        SelectCommand selectCommand = new SelectCommand(INDEX_FIRST_INTERNSHIP);

        String expectedMessage = String.format(SelectCommand.MESSAGE_SELECT_INTERNSHIP_SUCCESS, internshipToSelect);

        ModelManager expectedModel = new ModelManager(model.getInTrack(), new UserPrefs());
        expectedModel.updateSelectedInternship(a -> a.isSameInternship(internshipToSelect));

        assertCommandSuccess(selectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        SelectCommand selectCommand = new SelectCommand(outOfBoundIndex);

        assertCommandFailure(selectCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Internship internshipToSelect = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        SelectCommand selectCommand = new SelectCommand(INDEX_FIRST_INTERNSHIP);

        String expectedMessage = String.format(SelectCommand.MESSAGE_SELECT_INTERNSHIP_SUCCESS, internshipToSelect);

        ModelManager expectedModel = new ModelManager(model.getInTrack(), new UserPrefs());
        expectedModel.updateSelectedInternship(a -> a.isSameInternship(internshipToSelect));
        expectedModel.updateFilteredInternshipList(a -> a.isSameInternship(internshipToSelect));

        assertCommandSuccess(selectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        // ensures that outOfBoundIndex is still in bounds of internship tracker list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInTrack().getInternshipList().size());

        SelectCommand selectCommand = new SelectCommand(outOfBoundIndex);

        assertCommandFailure(selectCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        SelectCommand selectFirstCommand = new SelectCommand(INDEX_FIRST_INTERNSHIP);
        SelectCommand selectSecondCommand = new SelectCommand(INDEX_SECOND_INTERNSHIP);

        // same object -> returns true
        assertTrue(selectFirstCommand.equals(selectFirstCommand));

        // same values -> returns true
        SelectCommand selectFirstCommandCopy = new SelectCommand(INDEX_FIRST_INTERNSHIP);
        assertTrue(selectFirstCommand.equals(selectFirstCommandCopy));

        // different types -> returns false
        assertFalse(selectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(selectFirstCommand.equals(null));

        // different internship -> returns false
        assertFalse(selectFirstCommand.equals(selectSecondCommand));
    }
}
