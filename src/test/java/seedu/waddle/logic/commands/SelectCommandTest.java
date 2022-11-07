package seedu.waddle.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import seedu.waddle.logic.StageManager;
import seedu.waddle.model.Model;
import seedu.waddle.model.ModelManager;
import seedu.waddle.model.UserPrefs;
import seedu.waddle.model.itinerary.Itinerary;


public class SelectCommandTest {
    private Model model = new ModelManager(getTypicalWaddle(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        StageManager.getInstance().setHomeStage();
        Itinerary itineraryToSelect = model.getFilteredItineraryList().get(INDEX_FIRST_ITINERARY.getZeroBased());
        SelectCommand selectCommand = new SelectCommand(INDEX_FIRST_ITINERARY);

        String expectedMessage = String.format(SelectCommand.MESSAGE_SELECT_ITINERARY_SUCCESS,
                itineraryToSelect.getDescription());

        ModelManager expectedModel = new ModelManager(model.getWaddle(), new UserPrefs());
        StageManager.getInstance().setWishStage(itineraryToSelect);

        assertCommandSuccess(selectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredItineraryList().size() + 1);
        SelectCommand selectCommand = new SelectCommand(outOfBoundIndex);

        assertCommandFailure(selectCommand, model, Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        StageManager.getInstance().setHomeStage();
        showItineraryAtIndex(model, INDEX_FIRST_ITINERARY);

        Itinerary itineraryToSelect = model.getFilteredItineraryList().get(INDEX_FIRST_ITINERARY.getZeroBased());
        SelectCommand selectCommand = new SelectCommand(INDEX_FIRST_ITINERARY);

        String expectedMessage = String.format(SelectCommand.MESSAGE_SELECT_ITINERARY_SUCCESS,
                itineraryToSelect.getDescription());

        Model expectedModel = new ModelManager(model.getWaddle(), new UserPrefs());
        showFirstItinerary(expectedModel);
        StageManager.getInstance().setWishStage(itineraryToSelect);

        assertCommandSuccess(selectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showItineraryAtIndex(model, INDEX_FIRST_ITINERARY);

        Index outOfBoundIndex = INDEX_SECOND_ITINERARY;
        // ensures that outOfBoundIndex is still in bounds of Waddle list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWaddle().getItineraryList().size());

        SelectCommand selectCommand = new SelectCommand(outOfBoundIndex);

        assertCommandFailure(selectCommand, model, Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        SelectCommand selectFirstCommand = new SelectCommand(INDEX_FIRST_ITINERARY);
        SelectCommand selectSecondCommand = new SelectCommand(INDEX_SECOND_ITINERARY);

        // same object -> returns true
        assertTrue(selectFirstCommand.equals(selectFirstCommand));

        // same values -> returns true
        SelectCommand selectFirstCommandCopy = new SelectCommand(INDEX_FIRST_ITINERARY);
        assertTrue(selectFirstCommand.equals(selectFirstCommandCopy));

        // different types -> returns false
        assertFalse(selectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(selectFirstCommand.equals(null));

        // different index -> returns false
        assertFalse(selectFirstCommand.equals(selectSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show first itinerary in getTypicalWaddle().
     */
    private void showFirstItinerary(Model model) {
        model.updateFilteredItineraryList(itinerary ->
                itinerary.equals(getTypicalWaddle().getItineraryList().get(INDEX_FIRST_ITINERARY.getZeroBased())));

        assertEquals(1, model.getFilteredItineraryList().size());
    }
}
