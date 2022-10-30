package seedu.boba.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;

import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

public class HelpCommandTest {
    private final BobaBotModel bobaBotModel = new BobaBotModelManager();
    private final BobaBotModel expectedBobaBotModel = new BobaBotModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false, false, false, false);
        assertCommandSuccess(new HelpCommand(), bobaBotModel, expectedCommandResult, expectedBobaBotModel);
    }
}
