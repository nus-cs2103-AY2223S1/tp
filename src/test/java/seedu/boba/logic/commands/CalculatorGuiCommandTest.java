package seedu.boba.logic.commands;

import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;

public class CalculatorGuiCommandTest {
    private BobaBotModel bobaBotModel = new BobaBotModelManager();
    private BobaBotModel expectedBobaBotModel = new BobaBotModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false, false, false, false);
        assertCommandSuccess(new HelpCommand(), bobaBotModel, expectedCommandResult, expectedBobaBotModel);
    }
}
