package jeryl.fyp.logic.commands;

import static jeryl.fyp.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jeryl.fyp.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jeryl.fyp.model.Model;
import jeryl.fyp.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false, false, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpSingleCommand_success() {
        CommandResult expectedCommandResult = new CommandResult(
                AddStudentCommand.MESSAGE_USAGE, false, false, false, false);
        assertCommandSuccess(new HelpCommand(
                AddStudentCommand.MESSAGE_USAGE), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        HelpCommand helpCommand = new HelpCommand();
        HelpCommand anotherHelpCommand = new HelpCommand();
        HelpCommand helpCommandCopy = new HelpCommand(SHOWING_HELP_MESSAGE);
        HelpCommand listHelpCommand = new HelpCommand(ListCommand.MESSAGE_USAGE);

        // same object -> returns true
        assertTrue(helpCommand.equals(helpCommand));

        // same values -> returns true
        assertTrue(helpCommand.equals(anotherHelpCommand));

        // different types -> returns false
        assertFalse(helpCommand.equals(1));

        // null -> returns false
        assertFalse(helpCommand.equals(null));

        // different message -> returns false
        assertFalse(helpCommand.equals(listHelpCommand));

        // different show help status -> returns false
        assertFalse(helpCommand.equals(helpCommandCopy));
    }
}
