package seedu.pennyWise.logic.commands;

import static seedu.pennyWise.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.pennyWise.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.pennyWise.model.GraphConfiguration;
import seedu.pennyWise.model.Model;
import seedu.pennyWise.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(
                SHOWING_HELP_MESSAGE, true, false, GraphConfiguration.ofDefault());
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
