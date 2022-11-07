package seedu.travelr.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.travelr.logic.parser.SelectCommandParser;
import seedu.travelr.logic.parser.exceptions.ParseException;
import seedu.travelr.model.Model;
import seedu.travelr.model.ModelManager;
import seedu.travelr.model.UserPrefs;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.testutil.TripBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.travelr.testutil.TypicalTrips.getEmptyTravelr;

class SelectCommandTest {

    private Model model = new ModelManager(getEmptyTravelr(), new UserPrefs());

    @Test
    public void execute_display_successful() throws Exception {

        Trip validTrip = new TripBuilder().build();
        new AddCommand(validTrip).execute(model);
        CommandResult commandResult = new SelectCommandParser()
                .parse("1").execute(model);

        assertEquals(String.format(SelectCommand.MESSAGE_SUCCESS, validTrip.getTitle()),
                commandResult.getFeedbackToUser());
    }

    @Test
    void testEquals() throws ParseException{
        SelectCommand selectCommand = new SelectCommandParser()
                .parse("1");
        SelectCommand selectCommandCopy = new SelectCommandParser()
                .parse("1");
        assertTrue(selectCommand.equals(selectCommandCopy));
        assertTrue(selectCommandCopy.equals(selectCommand));
    }
}