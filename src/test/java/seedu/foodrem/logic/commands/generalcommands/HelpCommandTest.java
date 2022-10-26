package seedu.foodrem.logic.commands.generalcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.enums.CommandType;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.UserPrefs;
import seedu.foodrem.testutil.TypicalFoodRem;

class HelpCommandTest {
    private final Model model = new ModelManager(TypicalFoodRem.getTypicalFoodRem(), new UserPrefs());

    @Test
    void getCommandHelpMessage() {
        String helpMessage = "help: Displays help for FoodRem.\n\n"
                + "Example:\n"
                + "help\n\n"
                + "To receive help for a specific command, enter \"help COMMAND_WORD\" "
                + "in the command box, where COMMAND_WORD is any one of the following:\n"
                + "exit, help, reset, dec, del, edit, find, inc, list, new, rmk, sort, view, "
                + "deletetag, listtag, newtag, renametag, tag, untag.\n\n"
                + "For more information please head to:\n"
                + "https://ay2223s1-cs2103t-w16-2.github.io/tp/UserGuide";
        assertEquals(helpMessage, HelpCommand.getCommandHelpMessage(CommandType.HELP_COMMAND));
    }

    @Test
    void getGeneralHelpMessage() {
        String generalHelpMessage = "To receive help for a specific command, enter \"help COMMAND_WORD\" "
                + "in the command box, where COMMAND_WORD is any one of the following:\n"
                + "exit, help, reset, dec, del, edit, find, inc, list, new, rmk, sort, view, "
                + "deletetag, listtag, newtag, renametag, tag, untag.\n\n"
                + "For more information please head to:\n"
                + "https://ay2223s1-cs2103t-w16-2.github.io/tp/UserGuide";
        assertEquals(generalHelpMessage, HelpCommand.getGeneralHelpMessage());
    }

    @Test
    void testCommandResult() {
        CommandResult<?> commandResult = new HelpCommand("Test").execute(model);
        assertEquals(commandResult.getHelpText(), "Test");
        assertTrue(commandResult.shouldShowHelp());
        assertEquals(commandResult.getOutput(), "Opened help window.");
    }

    @Test
    void testEquals() {
        HelpCommand helpCommandSameFirst = new HelpCommand("Hello");
        HelpCommand helpCommandSameSecond = new HelpCommand("Hello");

        HelpCommand helpCommandDifferentFirst = new HelpCommand("Hello");
        HelpCommand helpCommandDifferentSecond = new HelpCommand("Goodbye");

        // Exactly the same
        assertEquals(helpCommandSameFirst, helpCommandSameSecond);
        // Different
        assertNotEquals(helpCommandDifferentFirst, helpCommandDifferentSecond);
    }

    @Test
    void testCommandResultEquals() {
        CommandResult<?> firstResult = new HelpCommand("Test").execute(model);
        CommandResult<?> secondResult = new HelpCommand("Test").execute(model);

        assertEquals(firstResult, secondResult);
    }
}
