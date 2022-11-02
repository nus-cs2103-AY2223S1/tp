package seedu.travelr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.travelr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.travelr.logic.commands.CommandTestUtil.showTripAtIndex;
import static seedu.travelr.testutil.TypicalIndexes.INDEX_FIRST_TRIP;
import static seedu.travelr.testutil.TypicalIndexes.INDEX_SECOND_TRIP;
import static seedu.travelr.testutil.TypicalTrips.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.travelr.commons.core.Messages;
import seedu.travelr.commons.core.index.Index;
import seedu.travelr.model.Model;
import seedu.travelr.model.ModelManager;
import seedu.travelr.model.UserPrefs;
import seedu.travelr.model.trip.Trip;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Trip personToDelete = model.getFilteredTripList().get(INDEX_FIRST_TRIP.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_TRIP);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_TRIP_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getTravelr(), new UserPrefs());
        expectedModel.deleteTrip(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTripList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_DISPLAYED_TRIP_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTripAtIndex(model, INDEX_FIRST_TRIP);

        Trip personToDelete = model.getFilteredTripList().get(INDEX_FIRST_TRIP.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_TRIP);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_TRIP_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getTravelr(), new UserPrefs());
        expectedModel.deleteTrip(personToDelete);
        showNoTrip(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTripAtIndex(model, INDEX_FIRST_TRIP);

        Index outOfBoundIndex = INDEX_SECOND_TRIP;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTravelr().getTripList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_DISPLAYED_TRIP_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_TRIP);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_TRIP);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_TRIP);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTrip(Model model) {
        model.updateFilteredTripList(p -> false);

        assertTrue(model.getFilteredTripList().isEmpty());
    }
}
