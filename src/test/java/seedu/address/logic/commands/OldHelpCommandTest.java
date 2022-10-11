package seedu.address.logic.commands;

import static seedu.address.logic.commands.OldCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.OldHelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.OldModel;
import seedu.address.model.OldModelManager;

public class OldHelpCommandTest {
    private OldModel model = new OldModelManager();
    private OldModel expectedModel = new OldModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new OldHelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
