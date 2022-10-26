package seedu.foodrem.logic.parser.generalcommandparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.foodrem.commons.enums.CommandType.parseWord;
import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.getCommandHelpMessage;
import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.getGeneralHelpMessage;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.enums.CommandType;
import seedu.foodrem.logic.commands.generalcommands.ExitCommand;
import seedu.foodrem.logic.commands.generalcommands.HelpCommand;
import seedu.foodrem.logic.commands.generalcommands.ResetCommand;
import seedu.foodrem.logic.commands.itemcommands.DecrementCommand;
import seedu.foodrem.logic.commands.itemcommands.DeleteCommand;
import seedu.foodrem.logic.commands.itemcommands.EditCommand;
import seedu.foodrem.logic.commands.itemcommands.FindCommand;
import seedu.foodrem.logic.commands.itemcommands.IncrementCommand;
import seedu.foodrem.logic.commands.itemcommands.ListCommand;
import seedu.foodrem.logic.commands.itemcommands.NewCommand;
import seedu.foodrem.logic.commands.itemcommands.RemarkCommand;
import seedu.foodrem.logic.commands.itemcommands.SortCommand;
import seedu.foodrem.logic.commands.itemcommands.ViewCommand;
import seedu.foodrem.logic.commands.tagcommands.DeleteTagCommand;
import seedu.foodrem.logic.commands.tagcommands.ListTagCommand;
import seedu.foodrem.logic.commands.tagcommands.NewTagCommand;
import seedu.foodrem.logic.commands.tagcommands.RenameTagCommand;
import seedu.foodrem.logic.commands.tagcommands.TagCommand;
import seedu.foodrem.logic.commands.tagcommands.UntagCommand;

class HelpCommandParserTest {
    private static final String EXPECTED_ALL_COMMANDS = "exit, help, reset, " // General commands
            + "dec, del, edit, find, inc, list, new, rmk, sort, view, " // Item commands
            + "filtertag, deletetag, listtag, newtag, renametag, tag, untag"; // Tag commands


    private final HelpCommandParser parser = new HelpCommandParser();

    @Test
    void didYouUpdateThisTest() {
        /*
        If this test fails, it means you most probably did not edit any test cases in here when
        you should have. When creating a new command, ensure that help for that command is available.
        Please only increase the number of commands after making ALL relevant changes to these test case.

        Update
        1) parse_text
        2) parse_TYPEOFCOMMAND
        3) getHelp_TYPEOFCOMMAND

        Where TYPEOFCOMMAND is either generalCommands / itemCommands / tagCommands

        Thank you :D
        */
        int numberOfCommands = 21; // equal to total number of commands + 1 for invalid command
        assertEquals(numberOfCommands, CommandType.values().length);
    }

    @Test
    void parse_text() {
        assertEquals("Please refer to the user guide.", HelpCommand.DEFAULT_HELP_MESSAGE);

        assertEquals(EXPECTED_ALL_COMMANDS, CommandType.listAllCommandWords());

        // Not a command constant
        assertEquals("\"testing\" is not a valid command\n\n"
                        + "To receive help for a specific command, enter "
                        + "\"help COMMAND_WORD\" in the command box, where COMMAND_WORD is any one of the following:\n"
                        + EXPECTED_ALL_COMMANDS
                        + ".\n\nFor more information please head to:\n"
                        + "https://ay2223s1-cs2103t-w16-2.github.io/tp/UserGuide",
                String.format(HelpCommand.NOT_A_COMMAND, "testing"));
    }

    @Test
    void parse_blankCommands() {
        assertParseSuccess(parser, "",
                new HelpCommand(getGeneralHelpMessage()));
    }

    @Test
    void getHelp_blankCommands() {
        assertEquals(HelpCommand.DEFAULT_HELP_MESSAGE, parseWord("").getUsage());
    }

    @Test
    void parse_nonExistentCommand() {
        assertParseSuccess(parser, CommandType.DEFAULT.getCommandWord(),
                new HelpCommand(String.format(HelpCommand.NOT_A_COMMAND, "default")));
        assertParseSuccess(parser, "helps",
                new HelpCommand(String.format(HelpCommand.NOT_A_COMMAND, "helps")));
    }

    @Test
    void getHelp_nonExistentCommand() {
        assertEquals(CommandType.DEFAULT.getUsage(), parseWord("destroytheworld").getUsage());
    }

    @Test
    void parse_generalCommands() {
        assertParseSuccess(parser, CommandType.HELP_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("help"))));
        assertParseSuccess(parser, CommandType.EXIT_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("exit"))));
        assertParseSuccess(parser, CommandType.RESET_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("reset"))));
    }

    // TODO: Rewrite all the tests
    @Test
    void getHelp_generalCommands() {
        assertEquals(ExitCommand.getUsage(), parseWord("exit").getUsage());
        assertEquals(HelpCommand.getUsage(), parseWord("help").getUsage());
        assertEquals(ResetCommand.getUsage(), parseWord("reset").getUsage());
    }

    @Test
    void parse_itemCommands() {
        assertParseSuccess(parser, CommandType.NEW_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("new"))));
        assertParseSuccess(parser, CommandType.EDIT_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("edit"))));
        assertParseSuccess(parser, CommandType.INCREMENT_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("inc"))));
        assertParseSuccess(parser, CommandType.DECREMENT_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("dec"))));
        assertParseSuccess(parser, CommandType.DELETE_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("del"))));
        assertParseSuccess(parser, CommandType.FIND_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("find"))));
        assertParseSuccess(parser, CommandType.LIST_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("list"))));
        assertParseSuccess(parser, CommandType.SORT_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("sort"))));
        assertParseSuccess(parser, CommandType.VIEW_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("view"))));
        assertParseSuccess(parser, CommandType.REMARK_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("rmk"))));
    }

    @Test
    void getHelp_itemCommands() {
        assertEquals(NewCommand.getUsage(), parseWord("new").getUsage());
        assertEquals(EditCommand.getUsage(), parseWord("edit").getUsage());
        assertEquals(IncrementCommand.getUsage(), parseWord("inc").getUsage());
        assertEquals(DecrementCommand.getUsage(), parseWord("dec").getUsage());
        assertEquals(DeleteCommand.getUsage(), parseWord("del").getUsage());
        assertEquals(FindCommand.getUsage(), parseWord("find").getUsage());
        assertEquals(ListCommand.getUsage(), parseWord("list").getUsage());
        assertEquals(SortCommand.getUsage(), parseWord("sort").getUsage());
        assertEquals(ViewCommand.getUsage(), parseWord("view").getUsage());
        assertEquals(RemarkCommand.getUsage(), parseWord("rmk").getUsage());
    }

    @Test
    void parse_tagCommands() {
        assertParseSuccess(parser, CommandType.NEW_TAG_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("newtag"))));
        assertParseSuccess(parser, CommandType.RENAME_TAG_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("renametag"))));
        assertParseSuccess(parser, CommandType.TAG_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("tag"))));
        assertParseSuccess(parser, CommandType.UNTAG_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("untag"))));
        assertParseSuccess(parser, CommandType.DELETE_TAG_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("deletetag"))));
        assertParseSuccess(parser, CommandType.LIST_TAG_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(parseWord("listtag"))));
    }

    @Test
    void getHelp_tagCommands() {
        assertEquals(NewTagCommand.getUsage(), parseWord("newtag").getUsage());
        assertEquals(RenameTagCommand.getUsage(), parseWord("renametag").getUsage());
        assertEquals(TagCommand.getUsage(), parseWord("tag").getUsage());
        assertEquals(UntagCommand.getUsage(), parseWord("untag").getUsage());
        assertEquals(DeleteTagCommand.getUsage(), parseWord("deletetag").getUsage());
        assertEquals(ListTagCommand.getUsage(), parseWord("listtag").getUsage());
    }
}
