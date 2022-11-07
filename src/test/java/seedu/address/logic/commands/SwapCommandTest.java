package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SwapCommand.MESSAGE_SWAP_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class SwapCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_swap_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SWAP_ACKNOWLEDGEMENT, null,
                false, false, true, false);
        assertCommandSuccess(new SwapCommand(), model, expectedCommandResult, expectedModel);
    }
}
