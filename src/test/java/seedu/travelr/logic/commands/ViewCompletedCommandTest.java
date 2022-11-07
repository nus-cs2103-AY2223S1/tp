package seedu.travelr.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.Model;
import seedu.travelr.model.ModelManager;
import seedu.travelr.model.UserPrefs;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.travelr.testutil.TypicalTrips.getEmptyTravelr;

class ViewCompletedCommandTest {
    private Model model = new ModelManager(getEmptyTravelr(), new UserPrefs());
    private static final String expectedOutput = "Listed completed trips and events";

    @Test
    void execute() throws CommandException {
        CommandResult commandResult = new ViewCompletedCommand().execute(model);

        assertEquals(expectedOutput,
                commandResult.getFeedbackToUser());
    }

    @Test
    void testEquals() {
        ViewCompletedCommand viewCompletedCommand = new ViewCompletedCommand();
        ViewCompletedCommand viewCompletedCommandCopy = new ViewCompletedCommand();
        assertTrue(viewCompletedCommand.equals(viewCompletedCommandCopy));
        assertTrue(viewCompletedCommandCopy.equals(viewCompletedCommand));
    }
}