package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.InternshipCommandTestUtil.showInternshipAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.internship.Internship;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteInternshipCommand}.
 */
public class DeleteInternshipCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Internship internshipToDelete = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        DeleteInternshipCommand deleteInternshipCommand = new DeleteInternshipCommand(INDEX_FIRST_INTERNSHIP);

        String expectedMessage = String.format(DeleteInternshipCommand.MESSAGE_DELETE_INTERNSHIP_SUCCESS,
                internshipToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteInternship(internshipToDelete);

        assertCommandSuccess(deleteInternshipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        DeleteInternshipCommand deleteInternshipCommand = new DeleteInternshipCommand(outOfBoundIndex);

        assertCommandFailure(deleteInternshipCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Internship internshipToDelete = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        DeleteInternshipCommand deleteInternshipCommand = new DeleteInternshipCommand(INDEX_FIRST_INTERNSHIP);

        String expectedMessage = String.format(DeleteInternshipCommand.MESSAGE_DELETE_INTERNSHIP_SUCCESS,
                internshipToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteInternship(internshipToDelete);
        showNoInternship(expectedModel);

        assertCommandSuccess(deleteInternshipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getInternshipList().size());

        DeleteInternshipCommand deleteInternshipCommand = new DeleteInternshipCommand(outOfBoundIndex);

        assertCommandFailure(deleteInternshipCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteInternshipCommand deleteFirstCommand = new DeleteInternshipCommand(INDEX_FIRST_INTERNSHIP);
        DeleteInternshipCommand deleteSecondCommand = new DeleteInternshipCommand(INDEX_SECOND_INTERNSHIP);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteInternshipCommand deleteFirstCommandCopy = new DeleteInternshipCommand(INDEX_FIRST_INTERNSHIP);
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
