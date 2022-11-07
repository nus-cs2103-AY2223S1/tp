package foodwhere.logic.commands;

import static foodwhere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.index.Index;
import foodwhere.model.Model;
import foodwhere.model.ModelManager;
import foodwhere.model.UserPrefs;
import foodwhere.model.review.Review;
import foodwhere.testutil.TypicalIndexes;
import foodwhere.testutil.TypicalStalls;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RDeleteCommand}.
 */
public class RDeleteCommandTest {

    private Model model = new ModelManager(TypicalStalls.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Review reviewToDelete = model.getFilteredReviewList().get(TypicalIndexes.INDEX_FIRST_REVIEW.getZeroBased());
        RDeleteCommand rDeleteCommand = new RDeleteCommand(TypicalIndexes.INDEX_FIRST_REVIEW);

        String expectedMessage = String.format(rDeleteCommand.MESSAGE_DELETE_REVIEW_SUCCESS, reviewToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteReview(reviewToDelete);

        assertCommandSuccess(rDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredReviewList().size() + 1);
        RDeleteCommand rDeleteCommand = new RDeleteCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(rDeleteCommand, model, RDeleteCommand.MESSAGE_INVALID_INDEX_ERROR);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Review reviewToDelete = model.getFilteredReviewList().get(TypicalIndexes.INDEX_FIRST_REVIEW.getZeroBased());
        RDeleteCommand rDeleteCommand = new RDeleteCommand(TypicalIndexes.INDEX_FIRST_REVIEW);

        String expectedMessage = String.format(rDeleteCommand.MESSAGE_DELETE_REVIEW_SUCCESS, reviewToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteReview(reviewToDelete);
        showNoReview(expectedModel);

        CommandTestUtil.assertCommandSuccess(rDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        RDeleteCommand deleteFirstCommand = new RDeleteCommand(TypicalIndexes.INDEX_FIRST_REVIEW);
        RDeleteCommand deleteSecondCommand = new RDeleteCommand(TypicalIndexes.INDEX_SECOND_REVIEW);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        RDeleteCommand deleteFirstCommandCopy = new RDeleteCommand(TypicalIndexes.INDEX_FIRST_REVIEW);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different stall -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoReview(Model model) {
        model.updateFilteredReviewList(p -> false);

        assertTrue(model.getFilteredReviewList().isEmpty());
    }
}
