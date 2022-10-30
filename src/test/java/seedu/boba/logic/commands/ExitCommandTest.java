package seedu.boba.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;

import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

public class ExitCommandTest {
    private final BobaBotModel bobaBotModel = new BobaBotModelManager();
    private final BobaBotModel expectedBobaBotModel = new BobaBotModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false,
                true, false, false, false);
        assertCommandSuccess(new ExitCommand(), bobaBotModel, expectedCommandResult, expectedBobaBotModel);
    }
}
