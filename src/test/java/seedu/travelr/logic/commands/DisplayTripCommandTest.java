package seedu.travelr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.testutil.Assert.assertThrows;
import static seedu.travelr.testutil.TypicalTrips.getEmptyTravelr;

import org.junit.jupiter.api.Test;

import seedu.travelr.commons.core.index.Index;
import seedu.travelr.model.Model;
import seedu.travelr.model.ModelManager;
import seedu.travelr.model.UserPrefs;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.testutil.TripBuilder;

class DisplayTripCommandTest {

    private Model model = new ModelManager(getEmptyTravelr(), new UserPrefs());

    @Test
    public void constructor_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DisplayTripCommand(null));
    }

    @Test
    public void execute_display_successful() throws Exception {

        Trip validTrip = new TripBuilder().build();
        new AddCommand(validTrip).execute(model);
        CommandResult commandResult = new DisplayTripCommand(Index.fromOneBased(1)).execute(model);

        assertEquals(String.format(DisplayTripCommand.MESSAGE_DISPLAY_TRIP_SUCCESS, validTrip),
                commandResult.getFeedbackToUser());
    }

    @Test
    void testEquals() {
        DisplayTripCommand displayTripCommand = new DisplayTripCommand(Index.fromOneBased(1));
        DisplayTripCommand displayTripCommandCopy = new DisplayTripCommand(Index.fromOneBased(1));

        // same object -> returns true
        assertTrue(displayTripCommand.equals(displayTripCommand));

        // same values -> returns true
        assertTrue(displayTripCommand.equals(displayTripCommandCopy));
    }
}
