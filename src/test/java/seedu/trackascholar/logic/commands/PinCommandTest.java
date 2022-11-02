package seedu.trackascholar.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackascholar.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.trackascholar.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.trackascholar.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static seedu.trackascholar.testutil.TypicalApplicants.getTypicalTrackAScholar;
import static seedu.trackascholar.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;
import static seedu.trackascholar.testutil.TypicalIndexes.INDEX_SECOND_APPLICANT;
import static seedu.trackascholar.testutil.TypicalApplicants.ALICE;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.commons.core.Messages;
import seedu.trackascholar.commons.core.index.Index;
import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.ModelManager;
import seedu.trackascholar.model.TrackAScholar;
import seedu.trackascholar.model.UserPrefs;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.testutil.ApplicantBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for PinCommand.
 */
public class PinCommandTest {
    private Model model = new ModelManager(getTypicalTrackAScholar(), new UserPrefs());

    @Test
    public void execute_unfilteredList_success() {
        Applicant pinnedApplicant = new ApplicantBuilder(ALICE).build_pinned();
        PinCommand pinCommand = new PinCommand(INDEX_FIRST_APPLICANT);
        String expectedMessage = String.format(PinCommand.MESSAGE_PIN_APPLICANT_SUCCESS, pinnedApplicant);
        Model expectedModel = new ModelManager(new TrackAScholar(model.getTrackAScholar()), new UserPrefs());
        expectedModel.setApplicant(model.getFilteredApplicantList().get(0), pinnedApplicant);
        assertCommandSuccess(pinCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Applicant applicantInFilteredList = model.getFilteredApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        Applicant pinnedApplicant = new ApplicantBuilder(applicantInFilteredList).build_pinned();
        PinCommand pinCommand = new PinCommand(INDEX_FIRST_APPLICANT);
        String expectedMessage = String.format(PinCommand.MESSAGE_PIN_APPLICANT_SUCCESS, pinnedApplicant);
        Model expectedModel = new ModelManager(new TrackAScholar(model.getTrackAScholar()), new UserPrefs());
        expectedModel.setApplicant(model.getFilteredApplicantList().get(0), pinnedApplicant);
        assertCommandSuccess(pinCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_pinSameApplicantAgainFilteredList_failure() {
        showApplicantAtIndex(model, INDEX_FIRST_APPLICANT);
        Applicant applicantInFilteredList = model.getFilteredApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        Applicant pinnedApplicant = new ApplicantBuilder(applicantInFilteredList).build_pinned();
        model.setApplicant(model.getFilteredApplicantList().get(0), pinnedApplicant);
        // pins applicant again in filtered list into a duplicate in TrackAScholar
        PinCommand pinCommand = new PinCommand(INDEX_FIRST_APPLICANT);
        assertCommandFailure(pinCommand, model, PinCommand.MESSAGE_APPLICANT_ALREADY_PINNED);
    }

    @Test
    public void execute_invalidApplicantIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        PinCommand pinCommand = new PinCommand(outOfBoundIndex);
        assertCommandFailure(pinCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }
    /**
     * Pins applicant in filtered list where index is larger than size of filtered list,
     * but smaller than size of TrackAScholar
     */
    @Test
    public void execute_invalidApplicantIndexFilteredList_failure() {
        showApplicantAtIndex(model, INDEX_FIRST_APPLICANT);
        Index outOfBoundIndex = INDEX_SECOND_APPLICANT;
        // ensures that outOfBoundIndex is still in bounds of TrackAScholar
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTrackAScholar().getApplicantList().size());
        PinCommand pinCommand = new PinCommand(outOfBoundIndex);
        assertCommandFailure(pinCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }
    @Test
    public void equals() {
        final PinCommand standardCommand = new PinCommand(INDEX_FIRST_APPLICANT);

        // same values -> returns true
        PinCommand commandWithSameValues = new PinCommand(INDEX_FIRST_APPLICANT);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new PinCommand(INDEX_SECOND_APPLICANT)));

    }
}
