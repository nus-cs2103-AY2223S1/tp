package seedu.boba.logic.commands;

import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;

public class ExitCommandTest {
    private BobaBotModel bobaBotModel = new BobaBotModelManager();
    private BobaBotModel expectedBobaBotModel = new BobaBotModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false,
                true, false, false, false);
        assertCommandSuccess(new ExitCommand(), bobaBotModel, expectedCommandResult, expectedBobaBotModel);
    }
}
