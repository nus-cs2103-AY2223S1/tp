package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertListingNotFoundFailure;
import static seedu.address.testutil.TypicalListingId.ID_FIRST_LISTING;
import static seedu.address.testutil.TypicalListingId.ID_SECOND_LISTING;
import static seedu.address.testutil.TypicalListings.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.listing.Listing;
import seedu.address.model.listing.ListingId;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteListingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validListingIdUnfilteredList_success() {
        Listing listingToDelete = model.getListing(ID_FIRST_LISTING);
        DeleteListingCommand deleteListingCommand = new DeleteListingCommand(ID_FIRST_LISTING);

        String expectedMessage = String.format(DeleteListingCommand.MESSAGE_DELETE_LISTING_SUCCESS, listingToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteListing(listingToDelete);

        assertCommandSuccess(deleteListingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidListingIdUnfilteredList_throwsCommandException() {
        DeleteListingCommand deleteListingCommand = new DeleteListingCommand(new ListingId("Invalid"));

        assertListingNotFoundFailure(deleteListingCommand, model, Messages.MESSAGE_LISTING_DOES_NOT_EXIST);
    }

    @Test
    public void equals() {
        DeleteListingCommand deleteFirstCommand = new DeleteListingCommand(ID_FIRST_LISTING);
        DeleteListingCommand deleteSecondCommand = new DeleteListingCommand(ID_SECOND_LISTING);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteListingCommand deleteFirstCommandCopy = new DeleteListingCommand(ID_FIRST_LISTING);
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
