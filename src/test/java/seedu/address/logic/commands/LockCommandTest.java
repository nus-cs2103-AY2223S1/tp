package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.LockCommand.SHOWING_LOCK_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class LockCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_lock_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_LOCK_MESSAGE, false, false, true, 0);
        assertCommandSuccess(new LockCommand(), model, expectedCommandResult, expectedModel);
    }
}
