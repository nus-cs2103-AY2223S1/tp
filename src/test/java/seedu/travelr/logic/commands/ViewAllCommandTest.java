package seedu.travelr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.travelr.testutil.TypicalTrips.getEmptyTravelr;

import org.junit.jupiter.api.Test;

import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.Model;
import seedu.travelr.model.ModelManager;
import seedu.travelr.model.UserPrefs;

class ViewAllCommandTest {

    private static final String expectedOutput = "Listed all trips and events";

    private Model model = new ModelManager(getEmptyTravelr(), new UserPrefs());
    @Test
    void execute() throws CommandException {
        CommandResult commandResult = new ViewAllCommand().execute(model);

        assertEquals(expectedOutput,
                commandResult.getFeedbackToUser());
    }
}
