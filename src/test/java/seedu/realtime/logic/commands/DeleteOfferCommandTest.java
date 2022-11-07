package seedu.realtime.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realtime.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.realtime.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.realtime.logic.commands.CommandTestUtil.showOfferAtIndex;
import static seedu.realtime.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.realtime.testutil.TypicalIndexes.SECOND_INDEX;
import static seedu.realtime.testutil.TypicalOffers.getTypicalRealTime;

import org.junit.jupiter.api.Test;

import seedu.realtime.commons.core.Messages;
import seedu.realtime.commons.core.index.Index;
import seedu.realtime.model.Model;
import seedu.realtime.model.ModelManager;
import seedu.realtime.model.UserPrefs;
import seedu.realtime.model.offer.Offer;

/**
 * Contains integration tests (integration with the Model) and unit tests for
 * {@code DeleteOfferCommand}
 */
public class DeleteOfferCommandTest {

    private Model model = new ModelManager(getTypicalRealTime(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Offer offerToDelete = model.getFilteredOfferList().get(FIRST_INDEX.getZeroBased());
        DeleteOfferCommand deleteOfferCommand = new DeleteOfferCommand(FIRST_INDEX);

        String expectedMessage = String.format(DeleteOfferCommand.MESSAGE_DELETE_OFFER_SUCCESS, offerToDelete);

        ModelManager expectedModel = new ModelManager(model.getRealTime(), new UserPrefs());
        expectedModel.deleteOffer(offerToDelete);

        assertCommandSuccess(deleteOfferCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOfferList().size() + 1);
        DeleteOfferCommand deleteOfferCommand = new DeleteOfferCommand(outOfBoundIndex);

        assertCommandFailure(deleteOfferCommand, model, Messages.MESSAGE_INVALID_OFFER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showOfferAtIndex(model, FIRST_INDEX);

        Offer offerToDelete = model.getFilteredOfferList().get(FIRST_INDEX.getZeroBased());
        DeleteOfferCommand deleteOfferCommand = new DeleteOfferCommand(FIRST_INDEX);

        String expectedMessage = String.format(DeleteOfferCommand.MESSAGE_DELETE_OFFER_SUCCESS, offerToDelete);

        Model expectedModel = new ModelManager(model.getRealTime(), new UserPrefs());
        expectedModel.deleteOffer(offerToDelete);
        showNoOffer(expectedModel);

        // assertCommandSuccess(deleteOfferCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showOfferAtIndex(model, FIRST_INDEX);

        Index outOfBoundIndex = SECOND_INDEX;
        // ensures that outOfBoundIndex is still in bounds of realtime list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getRealTime().getOfferList().size());

        DeleteOfferCommand deleteOfferCommand = new DeleteOfferCommand(outOfBoundIndex);

        // assertCommandFailure(deleteOfferCommand, model, Messages.MESSAGE_INVALID_OFFER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteOfferCommand deleteFirstCommand = new DeleteOfferCommand(FIRST_INDEX);
        DeleteOfferCommand deleteSecondCommand = new DeleteOfferCommand(SECOND_INDEX);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteOfferCommand deleteFirstCommandCopy = new DeleteOfferCommand(FIRST_INDEX);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different offer -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoOffer(Model model) {
        model.updateFilteredOfferList(p -> false);

        assertTrue(model.getFilteredOfferList().isEmpty());
    }
}
