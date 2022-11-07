package tuthub.logic.commands;

import static tuthub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tuthub.testutil.TypicalTutors.getTypicalTuthub;

import org.junit.jupiter.api.Test;

import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.Tuthub;
import tuthub.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyTuthub_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTuthub_success() {
        Model model = new ModelManager(getTypicalTuthub(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTuthub(), new UserPrefs());
        expectedModel.setTuthub(new Tuthub());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
