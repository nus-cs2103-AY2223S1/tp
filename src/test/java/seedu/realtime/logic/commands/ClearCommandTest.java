package seedu.realtime.logic.commands;

import static seedu.realtime.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.realtime.testutil.TypicalPersons.getTypicalRealTime;

import org.junit.jupiter.api.Test;

import seedu.realtime.model.Model;
import seedu.realtime.model.ModelManager;
import seedu.realtime.model.RealTime;
import seedu.realtime.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyRealTime_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyRealTime_success() {
        Model model = new ModelManager(getTypicalRealTime(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalRealTime(), new UserPrefs());
        expectedModel.setRealTime(new RealTime());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
