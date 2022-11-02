package seedu.masslinkers.logic.commands;

import static seedu.masslinkers.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.masslinkers.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.ModelManager;
//@@author
public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, true, false);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
