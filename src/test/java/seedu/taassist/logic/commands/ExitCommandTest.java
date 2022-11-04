package seedu.taassist.logic.commands;

import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taassist.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.taassist.logic.commands.actions.UiAction;
import seedu.taassist.model.Model;
import seedu.taassist.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, UiAction.EXIT);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
