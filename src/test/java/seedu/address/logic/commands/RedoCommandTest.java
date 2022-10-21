package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class RedoCommandTest {
    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    @Test
    public void execute_redo_success() {
        CommandResult expectedCommandResult =
                new CommandResult(RedoCommand.MESSAGE_REDO_ACKNOWLEDGEMENT,
                        false, false, true, false);
        assertCommandSuccess(new RedoCommand(), model, expectedCommandResult, expectedModel);
    }
}
