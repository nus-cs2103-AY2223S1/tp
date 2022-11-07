package seedu.address.logic.commands;

import static seedu.address.logic.commands.BackCommand.MESSAGE_BACK_ACKNOWLEDGEMENT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class BackCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_back_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_BACK_ACKNOWLEDGEMENT, false, false, true);
        assertCommandSuccess(new BackCommand(), model, expectedCommandResult, expectedModel);
    }
}
