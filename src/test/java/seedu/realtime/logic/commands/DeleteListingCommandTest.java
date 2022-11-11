package seedu.realtime.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realtime.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.realtime.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.realtime.logic.commands.CommandTestUtil.showListingAtIndex;
import static seedu.realtime.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.realtime.testutil.TypicalIndexes.SECOND_INDEX;
import static seedu.realtime.testutil.TypicalListings.getTypicalRealTime;

import org.junit.jupiter.api.Test;

import seedu.realtime.commons.core.Messages;
import seedu.realtime.commons.core.index.Index;
import seedu.realtime.model.Model;
import seedu.realtime.model.ModelManager;
import seedu.realtime.model.UserPrefs;
import seedu.realtime.model.listing.Listing;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteListingCommandTest {

    private Model model = new ModelManager(getTypicalRealTime(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Listing listingToDelete = model.getFilteredListingList().get(FIRST_INDEX.getZeroBased());
        DeleteListingCommand deleteListingCommand = new DeleteListingCommand(FIRST_INDEX);

        String expectedMessage = String.format(DeleteListingCommand.MESSAGE_DELETE_LISTING_SUCCESS, listingToDelete);

        ModelManager expectedModel = new ModelManager(model.getRealTime(), new UserPrefs());
        expectedModel.deleteListing(listingToDelete);

        assertCommandSuccess(deleteListingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredListingList().size() + 1);
        DeleteListingCommand deleteListingCommand = new DeleteListingCommand(outOfBoundIndex);

        assertCommandFailure(deleteListingCommand, model, Messages.MESSAGE_INVALID_LISTING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showListingAtIndex(model, FIRST_INDEX);

        Listing listingToDelete = model.getFilteredListingList().get(FIRST_INDEX.getZeroBased());
        DeleteListingCommand deleteListingCommand = new DeleteListingCommand(FIRST_INDEX);

        String expectedMessage = String.format(DeleteListingCommand.MESSAGE_DELETE_LISTING_SUCCESS, listingToDelete);

        Model expectedModel = new ModelManager(model.getRealTime(), new UserPrefs());
        expectedModel.deleteListing(listingToDelete);
        showNoListing(expectedModel);

        assertCommandSuccess(deleteListingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showListingAtIndex(model, FIRST_INDEX);

        Index outOfBoundIndex = SECOND_INDEX;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getRealTime().getListingList().size());

        DeleteListingCommand deleteListingCommand = new DeleteListingCommand(outOfBoundIndex);

        assertCommandFailure(deleteListingCommand, model, Messages.MESSAGE_INVALID_LISTING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteListingCommand deleteFirstCommand = new DeleteListingCommand(FIRST_INDEX);
        DeleteListingCommand deleteSecondCommand = new DeleteListingCommand(SECOND_INDEX);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteListingCommand deleteFirstCommandCopy = new DeleteListingCommand(FIRST_INDEX);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different listing -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoListing(Model model) {
        model.updateFilteredListingList(p -> false);

        assertTrue(model.getFilteredListingList().isEmpty());
    }
}
