package seedu.travelr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.testutil.TypicalTrips.getEmptyTravelr;

import org.junit.jupiter.api.Test;

import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.Model;
import seedu.travelr.model.ModelManager;
import seedu.travelr.model.UserPrefs;

class ViewCompletedCommandTest {
    private static final String expectedOutput = "Listed completed trips and events";

    private Model model = new ModelManager(getEmptyTravelr(), new UserPrefs());

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
