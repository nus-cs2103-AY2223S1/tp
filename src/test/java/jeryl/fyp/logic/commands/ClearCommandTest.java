package jeryl.fyp.logic.commands;

import static jeryl.fyp.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jeryl.fyp.testutil.TypicalStudents.getTypicalFypManager;

import org.junit.jupiter.api.Test;

import jeryl.fyp.model.FypManager;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.ModelManager;
import jeryl.fyp.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyFypManager_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyFypManager_success() {
        Model model = new ModelManager(getTypicalFypManager(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalFypManager(), new UserPrefs());
        expectedModel.setFypManager(new FypManager());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
