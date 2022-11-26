package seedu.workbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.workbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.workbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.workbook.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.workbook.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.workbook.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.workbook.testutil.TypicalInternships.getTypicalWorkBook;

import org.junit.jupiter.api.Test;

import seedu.workbook.commons.core.Messages;
import seedu.workbook.commons.core.index.Index;
import seedu.workbook.model.Model;
import seedu.workbook.model.ModelManager;
import seedu.workbook.model.UserPrefs;
import seedu.workbook.model.internship.Internship;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalWorkBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Internship internshipToDelete = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_INTERNSHIP);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_INTERNSHIP_SUCCESS, internshipToDelete);

        ModelManager expectedModel = new ModelManager(model.getWorkBook(), new UserPrefs());
        expectedModel.deleteInternship(internshipToDelete);
        expectedModel.commitWorkBook();

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Internship internshipToDelete = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_INTERNSHIP);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_INTERNSHIP_SUCCESS, internshipToDelete);

        Model expectedModel = new ModelManager(model.getWorkBook(), new UserPrefs());
        expectedModel.deleteInternship(internshipToDelete);
        expectedModel.commitWorkBook();
        showNoInternship(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        // ensures that outOfBoundIndex is still in bounds of work book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWorkBook().getInternshipList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_INTERNSHIP);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_INTERNSHIP);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_INTERNSHIP);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different internship -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoInternship(Model model) {
        model.updateFilteredInternshipList(p -> false);

        assertTrue(model.getFilteredInternshipList().isEmpty());
    }
}
