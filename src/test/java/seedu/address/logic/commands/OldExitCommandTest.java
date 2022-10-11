package seedu.address.logic.commands;

import static seedu.address.logic.commands.OldCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.OldExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.OldModel;
import seedu.address.model.OldModelManager;

public class OldExitCommandTest {
    private OldModel model = new OldModelManager();
    private OldModel expectedModel = new OldModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new OldExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
