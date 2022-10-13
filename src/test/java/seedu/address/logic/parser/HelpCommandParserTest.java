package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.BuyCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CreateCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SellCommand;
import seedu.address.logic.commands.UserGuideCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class HelpCommandParserTest {

    private final HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_addArg_returnsHelpCommandWithAddMessageUsage() {
        HelpCommand expectedHelpCommand = new HelpCommand(AddCommand.MESSAGE_USAGE);

        assertParseSuccess(parser, AddCommand.COMMAND_WORD, expectedHelpCommand);
    }

    @Test
    public void parse_deleteArg_returnsHelpCommandWithDeleteMessageUsage() {
        HelpCommand expectedHelpCommand = new HelpCommand(DeleteCommand.MESSAGE_USAGE);

        assertParseSuccess(parser, DeleteCommand.COMMAND_WORD, expectedHelpCommand);
    }

    @Test
    public void parse_exitArg_returnsHelpCommandWithExitMessageUsage() {
        HelpCommand expectedHelpCommand = new HelpCommand(ExitCommand.MESSAGE_USAGE);

        assertParseSuccess(parser, ExitCommand.COMMAND_WORD, expectedHelpCommand);
    }

    @Test
    public void parse_helpArg_returnsHelpCommandWithHelpMessageUsage() {
        HelpCommand expectedHelpCommand = new HelpCommand(HelpCommand.MESSAGE_USAGE);

        assertParseSuccess(parser, HelpCommand.COMMAND_WORD, expectedHelpCommand);
    }

    @Test
    public void parse_listArg_returnsHelpCommandWithListMessageUsage() {
        HelpCommand expectedHelpCommand = new HelpCommand(ListCommand.MESSAGE_USAGE);

        assertParseSuccess(parser, ListCommand.COMMAND_WORD, expectedHelpCommand);
    }

    @Test
    public void parse_userGuideArg_returnsHelpCommandWithUserGuideMessageUsage() {
        HelpCommand expectedHelpCommand = new HelpCommand(UserGuideCommand.MESSAGE_USAGE);

        assertParseSuccess(parser, UserGuideCommand.COMMAND_WORD, expectedHelpCommand);
    }

    @Test
    public void parse_clearArg_returnsHelpCommandWithClearMessageUsage() {
        HelpCommand expectedHelpCommand = new HelpCommand(ClearCommand.MESSAGE_USAGE);

        assertParseSuccess(parser, ClearCommand.COMMAND_WORD, expectedHelpCommand);
    }

    @Test
    public void parse_noArgs_returnsHelpCommandWithAllCommandsUsage() {
        HelpCommand expectedHelpCommand = new HelpCommand();

        assertParseSuccess(parser, "", expectedHelpCommand);
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        String errorMessage = HelpCommandParser.INVALID_ARGUMENT_MESSAGE;

        assertThrows(ParseException.class, errorMessage, () -> parser.parse("help unknownCommand"));
    }

    @Test
    public void parse_buyArg_returnsHelpCommandWithBuyMessageUsage() {
        HelpCommand expectedHelpCommand = new HelpCommand(BuyCommand.MESSAGE_USAGE);

        assertParseSuccess(parser, BuyCommand.COMMAND_WORD, expectedHelpCommand);
    }

    @Test
    public void parse_createArg_returnsHelpCommandWithCreateMessageUsage() {
        HelpCommand expectedHelpCommand = new HelpCommand(CreateCommand.MESSAGE_USAGE);

        assertParseSuccess(parser, CreateCommand.COMMAND_WORD, expectedHelpCommand);
    }

    @Test
    public void parse_editArg_returnsHelpCommandWithEditMessageUsage() {
        HelpCommand expectedHelpCommand = new HelpCommand(EditCommand.MESSAGE_USAGE);

        assertParseSuccess(parser, EditCommand.COMMAND_WORD, expectedHelpCommand);
    }

    @Test
    public void parse_findArg_returnsHelpCommandWithFindMessageUsage() {
        HelpCommand expectedHelpCommand = new HelpCommand(FindCommand.MESSAGE_USAGE);

        assertParseSuccess(parser, FindCommand.COMMAND_WORD, expectedHelpCommand);
    }
    @Test
    public void parse_sellArg_returnsHelpCommandWithSellMessageUsage() {
        HelpCommand expectedHelpCommand = new HelpCommand(SellCommand.MESSAGE_USAGE);

        assertParseSuccess(parser, SellCommand.COMMAND_WORD, expectedHelpCommand);
    }
    @Test
    public void parse_viewArg_returnsHelpCommandWithViewMessageUsage() {
        HelpCommand expectedHelpCommand = new HelpCommand(ViewCommand.MESSAGE_USAGE);

        assertParseSuccess(parser, ViewCommand.COMMAND_WORD, expectedHelpCommand);
    }

}
