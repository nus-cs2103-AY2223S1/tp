package hobbylist.logic.commands;

import static hobbylist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static hobbylist.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import hobbylist.model.Model;
import hobbylist.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void setCommandWord_validWord_success() {
        ExitCommand.setCommandWord("test");
        assertEquals(ExitCommand.getCommandWord(), "test");
        ExitCommand.setCommandWord("exit");
    }
}
