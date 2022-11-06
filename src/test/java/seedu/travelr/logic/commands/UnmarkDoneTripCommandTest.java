package seedu.travelr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.commons.core.Messages.MESSAGE_RESET_VIEW;
import static seedu.travelr.testutil.Assert.assertThrows;
import static seedu.travelr.testutil.TypicalTrips.getEmptyTravelr;

import org.junit.jupiter.api.Test;

import seedu.travelr.commons.core.index.Index;
import seedu.travelr.model.Model;
import seedu.travelr.model.ModelManager;
import seedu.travelr.model.UserPrefs;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.testutil.EventBuilder;
import seedu.travelr.testutil.TripBuilder;

class UnmarkDoneTripCommandTest {

    private Model model = new ModelManager(getEmptyTravelr(), new UserPrefs());

    @Test
    public void constructor_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkTripDoneCommand(null));
    }

    @Test
    public void execute_display_successful() throws Exception {
        Event validEvent = new EventBuilder().build();
        new AddEventCommand(validEvent).execute(model);
        Trip validTrip = new TripBuilder().build();
        new AddCommand(validTrip).execute(model);
        new AddEventToTripCommand(
                validEvent.getTitle(), validTrip.getTitle()).execute(model);
        new MarkTripDoneCommand(Index.fromOneBased(1)).execute(model);
        CommandResult commandResult = new UnmarkDoneTripCommand(Index.fromOneBased(1)).execute(model);

        assertEquals(String.format(UnmarkDoneTripCommand.MESSAGE_SUCCESS + "\n" + MESSAGE_RESET_VIEW,
                        validTrip.getTitle()),
                commandResult.getFeedbackToUser());
    }

    @Test
    void testEquals() {
        UnmarkDoneTripCommand unmarkDoneTripCommand =
                new UnmarkDoneTripCommand(Index.fromOneBased(1));
        UnmarkDoneTripCommand unmarkDoneTripCommandCopy =
                new UnmarkDoneTripCommand(Index.fromOneBased(1));

        // same object -> returns true
        assertTrue(unmarkDoneTripCommand.equals(unmarkDoneTripCommand));

        // same values -> returns true
        assertTrue(unmarkDoneTripCommand.equals(unmarkDoneTripCommandCopy));
    }
}
