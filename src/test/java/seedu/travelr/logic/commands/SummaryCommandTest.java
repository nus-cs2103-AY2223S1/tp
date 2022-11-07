package seedu.travelr.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.Model;
import seedu.travelr.model.ModelManager;
import seedu.travelr.model.UserPrefs;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.testutil.TypicalTrips.getTypicalTravelr;

class SummaryCommandTest {

    private Model model = new ModelManager(getTypicalTravelr(), new UserPrefs());

    @Test
    void commandResultTest() throws CommandException {
        SummaryCommand sc = new SummaryCommand();
        CommandResult cr = sc.execute(model);
        assertTrue(cr.isShowSummary());
    }
}
