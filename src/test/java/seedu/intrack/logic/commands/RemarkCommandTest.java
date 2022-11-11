package seedu.intrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_REMARK_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_REMARK_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intrack.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.intrack.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.intrack.testutil.TypicalInternships.getTypicalInTrack;

import org.junit.jupiter.api.Test;

import seedu.intrack.model.InTrack;
import seedu.intrack.model.Model;
import seedu.intrack.model.ModelManager;
import seedu.intrack.model.UserPrefs;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Remark;
import seedu.intrack.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class RemarkCommandTest {

    private static final String REMARK_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalInTrack(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        // An internship must be selected before AddTask can be used
        model.updateSelectedInternship(a -> a.isSameInternship(firstInternship));

        Internship editedInternship = new InternshipBuilder(firstInternship).withRemark(REMARK_STUB).build();

        RemarkCommand remarkCommand = new RemarkCommand(new Remark(editedInternship.getRemark().value));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new InTrack(model.getInTrack()), new UserPrefs());
        expectedModel.setInternship(firstInternship, editedInternship);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_success() {
        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        // An internship must be selected before AddTask can be used
        model.updateSelectedInternship(a -> a.isSameInternship(firstInternship));

        Internship editedInternship = new InternshipBuilder(firstInternship).withRemark("").build();

        RemarkCommand remarkCommand = new RemarkCommand(new Remark(editedInternship.getRemark().toString()));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new InTrack(model.getInTrack()), new UserPrefs());
        expectedModel.setInternship(firstInternship, editedInternship);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        // An internship must be selected before AddTask can be used
        model.updateSelectedInternship(a -> a.isSameInternship(firstInternship));

        Internship editedInternship = new InternshipBuilder(model.getFilteredInternshipList()
                .get(INDEX_FIRST_INTERNSHIP.getZeroBased()))
                .withRemark(REMARK_STUB).build();

        RemarkCommand remarkCommand = new RemarkCommand(new Remark(editedInternship.getRemark().value));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new InTrack(model.getInTrack()), new UserPrefs());
        expectedModel.updateFilteredInternshipList(a -> a.isSameInternship(firstInternship));
        expectedModel.setInternship(firstInternship, editedInternship);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final RemarkCommand standardCommand = new RemarkCommand(new Remark(VALID_REMARK_AAPL));

        // same values -> returns true
        RemarkCommand commandWithSameValues = new RemarkCommand(new Remark(VALID_REMARK_AAPL));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(new Remark(VALID_REMARK_MSFT))));
    }
}
