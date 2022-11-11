package seedu.pennywise.logic.commands;

import static seedu.pennywise.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.pennywise.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.pennywise.model.GraphConfiguration;
import seedu.pennywise.model.Model;
import seedu.pennywise.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, GraphConfiguration.ofDefault());
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
