package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Unit test for HelpCommand.
 */
public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_noArguments_showsAllCommandsOverview() {
        String expectedMessage = HelpCommand.ALL_COMMANDS_MESSAGE;

        HelpCommand command = new HelpCommand();

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_helpArgument_showsHelpUsage() {
        String expectedMessage = HelpCommand.MESSAGE_USAGE;

        HelpCommand command = new HelpCommand(HelpCommand.MESSAGE_USAGE);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteArgument_showsDeleteUsage() {
        String expectedMessage = DeleteCommand.MESSAGE_USAGE;

        HelpCommand command = new HelpCommand(DeleteCommand.MESSAGE_USAGE);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_exitArgument_showsExitUsage() {
        String expectedMessage = ExitCommand.MESSAGE_USAGE;

        HelpCommand command = new HelpCommand(ExitCommand.MESSAGE_USAGE);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_listArgument_showsListUsage() {
        String expectedMessage = ListCommand.MESSAGE_USAGE;

        HelpCommand command = new HelpCommand(ListCommand.MESSAGE_USAGE);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_userGuideArgument_showsUserGuideUsage() {
        String expectedMessage = UserGuideCommand.MESSAGE_USAGE;

        HelpCommand command = new HelpCommand(UserGuideCommand.MESSAGE_USAGE);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearArgument_showsClearUsage() {
        String expectedMessage = ClearCommand.MESSAGE_USAGE;

        HelpCommand command = new HelpCommand(ClearCommand.MESSAGE_USAGE);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        HelpCommand addHelpCommand = new HelpCommand(AddCommand.MESSAGE_USAGE);
        HelpCommand editHelpCommand = new HelpCommand(EditCommand.MESSAGE_USAGE);
        HelpCommand deleteHelpCommand = new HelpCommand(DeleteCommand.MESSAGE_USAGE);

        assertNotEquals(addHelpCommand, editHelpCommand);
        assertEquals(addHelpCommand, addHelpCommand);
        assertEquals(editHelpCommand, editHelpCommand);
        assertNotEquals(editHelpCommand, deleteHelpCommand);
    }
}
