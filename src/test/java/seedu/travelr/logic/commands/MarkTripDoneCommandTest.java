package seedu.travelr.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.travelr.commons.core.index.Index;
import seedu.travelr.model.Model;
import seedu.travelr.model.ModelManager;
import seedu.travelr.model.UserPrefs;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.testutil.EventBuilder;
import seedu.travelr.testutil.TripBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.commons.core.Messages.MESSAGE_RESET_VIEW;
import static seedu.travelr.testutil.Assert.assertThrows;
import static seedu.travelr.testutil.TypicalTrips.getEmptyTravelr;

class AddEventToTripCommandTest {

    private Model model = new ModelManager(getEmptyTravelr(), new UserPrefs());

    @Test
    public void constructor_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventToTripCommand(null, null));
    }

    @Test
    public void execute_display_successful() throws Exception {
        Event validEvent = new EventBuilder().build();
        new AddEventCommand(validEvent).execute(model);
        Trip validTrip = new TripBuilder().build();
        new AddCommand(validTrip).execute(model);
        CommandResult commandResult = new AddEventToTripCommand(
                validEvent.getTitle(), validTrip.getTitle()).execute(model);

        assertEquals(String.format(AddEventToTripCommand.MESSAGE_SUCCESS + "\n"
                        + MESSAGE_RESET_VIEW, validEvent.getTitle(), validTrip.getTitle()),
                commandResult.getFeedbackToUser());
    }

    @Test
    void testEquals() {
        AddEventToTripCommand addEventToTripCommand
                = new AddEventToTripCommand(new Title("abc"), new Title("cba"));
        AddEventToTripCommand addEventToTripCommandcopy
                = new AddEventToTripCommand(new Title("abc"), new Title("cba"));

        // same object -> returns true
        assertTrue(addEventToTripCommand.equals(addEventToTripCommand));

        // same values -> returns true
        assertTrue(addEventToTripCommand.equals(addEventToTripCommandcopy));
    }
}
