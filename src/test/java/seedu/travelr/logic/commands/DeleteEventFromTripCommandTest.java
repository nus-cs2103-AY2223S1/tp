package seedu.travelr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.commons.core.Messages.MESSAGE_RESET_VIEW;
import static seedu.travelr.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.travelr.testutil.Assert.assertThrows;
import static seedu.travelr.testutil.TypicalTrips.getEmptyTravelr;

import org.junit.jupiter.api.Test;

import seedu.travelr.model.Model;
import seedu.travelr.model.ModelManager;
import seedu.travelr.model.UserPrefs;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.testutil.EventBuilder;
import seedu.travelr.testutil.TripBuilder;

class DeleteEventFromTripCommandTest {

    private Model model = new ModelManager(getEmptyTravelr(), new UserPrefs());

    @Test
    public void constructor_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteEventFromTripCommand(null, null));
    }

    @Test
    public void execute_deleteEventFromTrip_successful() throws Exception {
        Event validEvent = new EventBuilder().build();
        new AddEventCommand(validEvent).execute(model);
        Trip validTrip = new TripBuilder().build();
        new AddCommand(validTrip).execute(model);
        new AddEventToTripCommand(validEvent.getTitle(), validTrip.getTitle()).execute(model);
        CommandResult commandResult = new DeleteEventFromTripCommand(
                validEvent.getTitle(), validTrip.getTitle()).execute(model);

        assertEquals(String.format(
                        DeleteEventFromTripCommand.MESSAGE_SUCCESS + "\n" + MESSAGE_RESET_VIEW,
                        validEvent.getTitle(), validTrip.getTitle()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_inexistantTrip_throwsCommandException() throws Exception {
        Event validEvent = new EventBuilder().build();
        new AddEventCommand(validEvent).execute(model);
        Trip validTrip = new TripBuilder().build();

        Command command = new DeleteEventFromTripCommand(validEvent.getTitle(), validTrip.getTitle());

        assertCommandFailure(command, model, "Please enter a valid Trip");
    }

    @Test
    public void execute_eventNotInTrip_throwsCommandException() throws Exception {
        Event validEvent = new EventBuilder().build();
        new AddEventCommand(validEvent).execute(model);
        Trip validTrip = new TripBuilder().build();
        new AddCommand(validTrip).execute(model);

        Command command = new DeleteEventFromTripCommand(validEvent.getTitle(), validTrip.getTitle());

        assertCommandFailure(command, model, "Event does not exist in trip");
    }

    @Test
    void testEquals() {
        DeleteEventFromTripCommand deleteEventFromTripCommand =
                new DeleteEventFromTripCommand(new Title("abc"), new Title("cba"));
        DeleteEventFromTripCommand deleteEventFromTripCommandCopy =
                new DeleteEventFromTripCommand(new Title("abc"), new Title("cba"));
        AddCommand addCommand = new AddCommand(new TripBuilder().build());

        // same object -> returns true
        assertTrue(deleteEventFromTripCommand.equals(deleteEventFromTripCommand));

        // same values -> returns true
        assertTrue(deleteEventFromTripCommand.equals(deleteEventFromTripCommandCopy));

        // Different class -> returns false
        assertFalse(deleteEventFromTripCommand.equals(addCommand));
    }
}
