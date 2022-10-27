package seedu.boba.logic.commands;

import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;

import org.junit.jupiter.api.Test;

import seedu.boba.model.BobaBot;
import seedu.boba.model.Model;
import seedu.boba.model.ModelManager;
import seedu.boba.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyBobaBot_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyBobaBot_success() {
        Model model = new ModelManager(getTypicalBobaBot(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalBobaBot(), new UserPrefs());
        expectedModel.setBobaBot(new BobaBot());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
