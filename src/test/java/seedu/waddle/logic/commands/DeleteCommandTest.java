package seedu.waddle.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.waddle.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.waddle.logic.commands.CommandTestUtil.showItineraryAtIndex;
import static seedu.waddle.testutil.TypicalIndexes.INDEX_FIRST_ITINERARY;
import static seedu.waddle.testutil.TypicalIndexes.INDEX_SECOND_ITINERARY;
import static seedu.waddle.testutil.TypicalItineraries.getTypicalWaddle;

import org.junit.jupiter.api.Test;

import seedu.waddle.commons.core.Messages;
import seedu.waddle.commons.core.index.Index;
import seedu.waddle.model.Model;
import seedu.waddle.model.ModelManager;
import seedu.waddle.model.UserPrefs;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalWaddle(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Itinerary itineraryToDelete = model.getFilteredItineraryList().get(INDEX_FIRST_ITINERARY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ITINERARY);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ITINERARY_SUCCESS, itineraryToDelete);

        ModelManager expectedModel = new ModelManager(model.getWaddle(), new UserPrefs());
        expectedModel.deleteItinerary(itineraryToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredItineraryList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showItineraryAtIndex(model, INDEX_FIRST_ITINERARY);

        Itinerary itineraryToDelete = model.getFilteredItineraryList().get(INDEX_FIRST_ITINERARY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ITINERARY);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ITINERARY_SUCCESS, itineraryToDelete);

        Model expectedModel = new ModelManager(model.getWaddle(), new UserPrefs());
        expectedModel.deleteItinerary(itineraryToDelete);
        showNoItinerary(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showItineraryAtIndex(model, INDEX_FIRST_ITINERARY);

        Index outOfBoundIndex = INDEX_SECOND_ITINERARY;
        // ensures that outOfBoundIndex is still in bounds of Waddle list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWaddle().getItineraryList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_ITINERARY);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_ITINERARY);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_ITINERARY);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no itinerary.
     */
    private void showNoItinerary(Model model) {
        model.updateFilteredItineraryList(p -> false);

        assertTrue(model.getFilteredItineraryList().isEmpty());
    }
}
