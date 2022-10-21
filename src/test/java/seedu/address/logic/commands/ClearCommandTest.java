package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalApplicants.getTypicalTrackAScholar;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void confirmClear_nonEmptyTrackAScholar_success() {
        ClearCommand clearCommand = new ClearCommand();
        Model model = new ModelManager(getTypicalTrackAScholar(), new UserPrefs());
        CommandResult commandResult = clearCommand.confirmClear(model);
        CommandResult successResult = new CommandResult(ClearCommand.MESSAGE_CLEAR_SUCCESS);
        assertEquals(commandResult, successResult);

    }

    @Test
    public void confirmClear_emptyTrackAScholar_success() {
        ClearCommand clearCommand = new ClearCommand();
        Model model = new ModelManager();
        CommandResult commandResult = clearCommand.confirmClear(model);
        CommandResult successResult = new CommandResult(ClearCommand.MESSAGE_CLEAR_SUCCESS);
        assertEquals(commandResult, successResult);

    }

    @Test
    public void cancelClear_trackAScholar_success() {
        ClearCommand clearCommand = new ClearCommand();
        CommandResult commandResult = clearCommand.cancelClear();
        CommandResult terminationResult = new CommandResult(ClearCommand.MESSAGE_CLEAR_TERMINATION);
        assertEquals(commandResult, terminationResult);

    }

}
