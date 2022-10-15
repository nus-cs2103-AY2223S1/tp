package seedu.foodrem.logic.parser.generalcommandparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.getCommandHelpMessage;
import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.getGeneralHelpMessage;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.foodrem.enums.CommandType;
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
            + "dec, del, edit, find, inc, list, new, sort, view, " // Item commands
            + "deletetag, listtag, newtag, renametag, tag, untag"; // Tag commands

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
        int numberOfCommands = 19; // equal to total number of commands + 1 for invalid command
        assertEquals(numberOfCommands, CommandType.values().length);
    }

    @Test
    void parse_text() {
        assertEquals("Please refer to the user guide.", HelpCommand.DEFAULT_HELP_MESSAGE);

        // // Commented out pending reevaluation of usefulness of test case
        // assertEquals("https://se-education.org/addressbook-level3/UserGuide.html",
        //         HelpCommand.USER_GUIDE_URL);

        // // Commented out pending reevaluation of usefulness of test case
        // assertEquals("For more information please head to:\n"
        //         + "https://se-education.org/addressbook-level3/UserGuide.html", HelpCommand.MORE_INFORMATION);

        assertEquals(EXPECTED_ALL_COMMANDS, CommandType.listAllCommandWords());

        // // Commented out pending reevaluation of usefulness of test case
        // assertEquals("To receive help for a specific command, enter "
        //                 + "\"help COMMAND_WORD\" in the command box, where COMMAND_WORD is any "
        //                 + "one of the following:\n"
        //                 + "help\nreset\nexit\nnew\nlist\nfind\nedit\ninc"
        //                 + "\ndec\nsort\ndel\nview\nnewtag\nrenametag\ntag\nuntag\ndeletetag\nlisttag\n\n"
        //                 + "For more information please head to:\n"
        //                 + "https://se-education.org/addressbook-level3/UserGuide.html",
        //         HelpCommand.HELP_FORMAT_GENERAL);

        // // Commented out pending reevaluation of usefulness of test case
        // assertEquals("hehe\n\nTo receive help for a specific command, enter "
        //                 + "\"help COMMAND_WORD\" in the command box, "
        //                 + "where COMMAND_WORD is any one of the following:\n"
        //                 + "help\nreset\nexit\nnew\nlist\nfind\nedit\ninc"
        //                 + "\ndec\nsort\ndel\nview\nnewtag\nrenametag\ntag\nuntag\ndeletetag\nlisttag\n\n"
        //                 + "For more information please head to:\n"
        //                 + "https://se-education.org/addressbook-level3/UserGuide.html",
        //         String.format(HelpCommand.HELP_FOR_SPECIFIC_COMMAND, "hehe"));

        // Not a command constant
        assertEquals("\"testing\" is not a valid command\n\n"
                        + "To receive help for a specific command, enter "
                        + "\"help COMMAND_WORD\" in the command box, where COMMAND_WORD is any one of the following:\n"
                        + EXPECTED_ALL_COMMANDS
                        + "\n\nFor more information please head to:\n"
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
        assertEquals(HelpCommand.DEFAULT_HELP_MESSAGE, CommandType.parseWord("").getUsage());
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
        assertEquals(CommandType.DEFAULT.getUsage(), CommandType.parseWord("destroytheworld").getUsage());
    }

    @Test
    void parse_generalCommands() {
        assertParseSuccess(parser, CommandType.HELP_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandType.parseWord("help"))));
        assertParseSuccess(parser, CommandType.EXIT_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandType.parseWord("exit"))));
        assertParseSuccess(parser, CommandType.RESET_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandType.parseWord("reset"))));
    }

    // TODO: Rewrite all the tests
    @Test
    void getHelp_generalCommands() {
        assertEquals(ExitCommand.getUsage(), CommandType.parseWord("exit").getUsage());
        assertEquals(HelpCommand.getUsage(), CommandType.parseWord("help").getUsage());
        assertEquals(ResetCommand.getUsage(), CommandType.parseWord("reset").getUsage());
    }

    @Test
    void parse_itemCommands() {
        assertParseSuccess(parser, CommandType.NEW_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandType.parseWord("new"))));
        assertParseSuccess(parser, CommandType.EDIT_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandType.parseWord("edit"))));
        assertParseSuccess(parser, CommandType.INCREMENT_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandType.parseWord("inc"))));
        assertParseSuccess(parser, CommandType.DECREMENT_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandType.parseWord("dec"))));
        assertParseSuccess(parser, CommandType.DELETE_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandType.parseWord("del"))));
        assertParseSuccess(parser, CommandType.FIND_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandType.parseWord("find"))));
        assertParseSuccess(parser, CommandType.LIST_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandType.parseWord("list"))));
        assertParseSuccess(parser, CommandType.SORT_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandType.parseWord("sort"))));
        assertParseSuccess(parser, CommandType.VIEW_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandType.parseWord("view"))));
    }

    @Test
    void getHelp_itemCommands() {
        assertEquals(NewCommand.getUsage(), CommandType.parseWord("new").getUsage());
        assertEquals(EditCommand.getUsage(), CommandType.parseWord("edit").getUsage());
        assertEquals(IncrementCommand.getUsage(), CommandType.parseWord("inc").getUsage());
        assertEquals(DecrementCommand.getUsage(), CommandType.parseWord("dec").getUsage());
        assertEquals(DeleteCommand.getUsage(), CommandType.parseWord("del").getUsage());
        assertEquals(FindCommand.getUsage(), CommandType.parseWord("find").getUsage());
        assertEquals(ListCommand.getUsage(), CommandType.parseWord("list").getUsage());
        assertEquals(SortCommand.getUsage(), CommandType.parseWord("sort").getUsage());
        assertEquals(ViewCommand.getUsage(), CommandType.parseWord("view").getUsage());
    }

    @Test
    void parse_tagCommands() {
        assertParseSuccess(parser, CommandType.NEW_TAG_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandType.parseWord("newtag"))));
        assertParseSuccess(parser, CommandType.RENAME_TAG_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandType.parseWord("renametag"))));
        assertParseSuccess(parser, CommandType.TAG_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandType.parseWord("tag"))));
        assertParseSuccess(parser, CommandType.UNTAG_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandType.parseWord("untag"))));
        assertParseSuccess(parser, CommandType.DELETE_TAG_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandType.parseWord("deletetag"))));
        assertParseSuccess(parser, CommandType.LIST_TAG_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandType.parseWord("listtag"))));
    }

    @Test
    void getHelp_tagCommands() {
        assertEquals(NewTagCommand.getUsage(), CommandType.parseWord("newtag").getUsage());
        assertEquals(RenameTagCommand.getUsage(), CommandType.parseWord("renametag").getUsage());
        assertEquals(TagCommand.getUsage(), CommandType.parseWord("tag").getUsage());
        assertEquals(UntagCommand.getUsage(), CommandType.parseWord("untag").getUsage());
        assertEquals(DeleteTagCommand.getUsage(), CommandType.parseWord("deletetag").getUsage());
        assertEquals(ListTagCommand.getUsage(), CommandType.parseWord("listtag").getUsage());
    }
}
