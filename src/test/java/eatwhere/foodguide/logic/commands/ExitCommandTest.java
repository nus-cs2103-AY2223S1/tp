package eatwhere.foodguide.logic.commands;

import static eatwhere.foodguide.logic.commands.CommandTestUtil.assertCommandSuccess;
import static eatwhere.foodguide.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
