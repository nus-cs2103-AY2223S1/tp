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
import seedu.travelr.model.event.Event;
import seedu.travelr.testutil.EventBuilder;

class DeleteEventCommandTest {

    private Model model = new ModelManager(getEmptyTravelr(), new UserPrefs());

    @Test
    public void constructor_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteEventCommand(null));
    }

    @Test
    public void execute_display_successful() throws Exception {

        Event validEvent = new EventBuilder().build();
        new AddEventCommand(validEvent).execute(model);
        CommandResult commandResult = new DeleteEventCommand(Index.fromOneBased(1)).execute(model);

        assertEquals(String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS, validEvent),
                commandResult.getFeedbackToUser());
    }

    @Test
    void testEquals() {
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(Index.fromOneBased(1));
        DeleteEventCommand deleteEventCommandCopy = new DeleteEventCommand(Index.fromOneBased(1));

        // same object -> returns true
        assertTrue(deleteEventCommand.equals(deleteEventCommand));

        // same values -> returns true
        assertTrue(deleteEventCommand.equals(deleteEventCommandCopy));
    }
}
