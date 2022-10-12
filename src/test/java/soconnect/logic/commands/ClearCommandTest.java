package soconnect.logic.commands;

import static soconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static soconnect.testutil.TypicalPersons.getTypicalSoConnect;

import org.junit.jupiter.api.Test;

import soconnect.model.SoConnect;
import soconnect.model.Model;
import soconnect.model.ModelManager;
import soconnect.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptySoConnect_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptySoConnect_success() {
        Model model = new ModelManager(getTypicalSoConnect(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalSoConnect(), new UserPrefs());
        expectedModel.setSoConnect(new SoConnect());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
