package seedu.travelr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.commons.core.Messages.MESSAGE_RESET_VIEW;
import static seedu.travelr.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.travelr.testutil.Assert.assertThrows;
import static seedu.travelr.testutil.TypicalTrips.getEmptyTravelr;

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

class AddEventToTripCommandTest {

    private Model model = new ModelManager(getEmptyTravelr(), new UserPrefs());

    @Test
    public void constructor_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventToTripCommand(null, null));
    }


    @Test
    public void execute_addEventToTrip_successful() throws Exception {
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
    public void execute_addEventToInexistantTrip_throwsCommandException() throws Exception {
        Event validEvent = new EventBuilder().build();
        new AddEventCommand(validEvent).execute(model);
        Trip validTrip = new TripBuilder().build();
        Command command = new AddEventToTripCommand(validEvent.getTitle(), validTrip.getTitle());

        assertCommandFailure(command, model, "Please enter a valid Trip");
    }

    @Test
    public void execute_doubleAddingEventToTrip_throwsCommandException() throws Exception {
        Event validEvent = new EventBuilder().build();
        new AddEventCommand(validEvent).execute(model);
        Trip validTrip = new TripBuilder().build();
        new AddCommand(validTrip).execute(model);
        new AddEventToTripCommand(validEvent.getTitle(), validTrip.getTitle()).execute(model);

        Command command = new AddEventToTripCommand(validEvent.getTitle(), validTrip.getTitle());

        assertCommandFailure(command, model, AddEventToTripCommand.MESSAGE_DUPLICATE_EVENT_IN_TRIP);
    }

    @Test
    public void execute_addInexistantEventToTrip_throwsCommandException() throws Exception {
        Event validEvent = new EventBuilder().build();
        Trip validTrip = new TripBuilder().build();
        new AddCommand(validTrip).execute(model);

        Command command = new AddEventToTripCommand(validEvent.getTitle(), validTrip.getTitle());

        assertCommandFailure(command, model, "Event does not exist in bucket list");
    }

    @Test
    void testEquals() {
        AddEventToTripCommand addEventToTripCommand =
                new AddEventToTripCommand(new Title("abc"), new Title("cba"));
        AddEventToTripCommand addEventToTripCommandcopy =
                new AddEventToTripCommand(new Title("abc"), new Title("cba"));
        DeleteCommand deleteCommand = new DeleteCommand(Index.fromOneBased(1));

        // same object -> returns true
        assertTrue(addEventToTripCommand.equals(addEventToTripCommand));

        // same values -> returns true
        assertTrue(addEventToTripCommand.equals(addEventToTripCommandcopy));

        // Different class -> returns false
        assertFalse(addEventToTripCommand.equals(deleteCommand));
    }
}
