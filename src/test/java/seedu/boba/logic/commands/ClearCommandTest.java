package seedu.boba.logic.commands;

import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;

import org.junit.jupiter.api.Test;

import seedu.boba.model.BobaBot;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;
import seedu.boba.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyBobaBot_success() {
        BobaBotModel bobaBotModel = new BobaBotModelManager();
        BobaBotModel expectedBobaBotModel = new BobaBotModelManager();

        assertCommandSuccess(new ClearCommand(), bobaBotModel, ClearCommand.MESSAGE_SUCCESS, expectedBobaBotModel);
    }

    @Test
    public void execute_nonEmptyBobaBot_success() {
        BobaBotModel bobaBotModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
        BobaBotModel expectedBobaBotModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
        expectedBobaBotModel.setBobaBot(new BobaBot());

        assertCommandSuccess(new ClearCommand(), bobaBotModel, ClearCommand.MESSAGE_SUCCESS, expectedBobaBotModel);
    }

}
