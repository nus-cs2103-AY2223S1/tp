package seedu.trackascholar.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.ModelManager;
import seedu.trackascholar.model.TrackAScholar;
import seedu.trackascholar.model.UserPrefs;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.model.applicant.Name;
import seedu.trackascholar.testutil.ApplicantBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackascholar.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.trackascholar.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.trackascholar.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static seedu.trackascholar.testutil.TypicalApplicants.getTypicalTrackAScholar;
import static seedu.trackascholar.testutil.TypicalApplicants.ALICE;
import static seedu.trackascholar.testutil.TypicalApplicants.BENSON;
import static seedu.trackascholar.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;
import static seedu.trackascholar.testutil.TypicalIndexes.INDEX_SECOND_APPLICANT;


public class UnPinCommandTest {
    private Model model = new ModelManager(getTypicalTrackAScholar(), new UserPrefs());
    @Test
    public void execute_unfilteredList_success() {
        Applicant unPinnedApplicant = new ApplicantBuilder(BENSON).build();
        Name benName = BENSON.getName();
        UnPinCommand unPinCommand = new UnPinCommand(benName);
        String expectedMessage = String.format(UnPinCommand.MESSAGE_UNPIN_APPLICANT_SUCCESS, unPinnedApplicant);
        Model expectedModel = new ModelManager(new TrackAScholar(model.getTrackAScholar()), new UserPrefs());
        expectedModel.setApplicant(model.getFilteredApplicantList().get(1), unPinnedApplicant);
        assertCommandSuccess(unPinCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Applicant applicantInFilteredList = model.getFilteredApplicantList().get(INDEX_SECOND_APPLICANT.getZeroBased());
        Applicant unPinnedApplicant = new ApplicantBuilder(applicantInFilteredList).build();
        UnPinCommand unPinCommand = new UnPinCommand(applicantInFilteredList.getName());
        String expectedMessage = String.format(UnPinCommand.MESSAGE_UNPIN_APPLICANT_SUCCESS, unPinnedApplicant);
        Model expectedModel = new ModelManager(new TrackAScholar(model.getTrackAScholar()), new UserPrefs());
        expectedModel.setApplicant(model.getFilteredApplicantList().get(1), unPinnedApplicant);
        assertCommandSuccess(unPinCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unPinSameApplicantAgainFilteredList_failure() {
        showApplicantAtIndex(model, INDEX_FIRST_APPLICANT);
        Applicant applicantInFilteredList = model.getFilteredApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        // trying to unpin an unpinned applicant in filtered list into a duplicate in TrackAScholar
        Name name = applicantInFilteredList.getName();
        UnPinCommand unPinCommand = new UnPinCommand(name);
        assertCommandFailure(unPinCommand, model, UnPinCommand.MESSAGE_APPLICANT_ALREADY_UNPINNED);
    }

    @Test
    public void equals() {
        Name aliceName = ALICE.getName();
        final UnPinCommand standardCommand = new UnPinCommand(aliceName);
        // same values -> returns true
        UnPinCommand commandWithSameValues = new UnPinCommand(aliceName);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        Name bensonName = BENSON.getName();
        // different index -> returns false
        assertFalse(standardCommand.equals(new UnPinCommand(bensonName)));

    }
}
