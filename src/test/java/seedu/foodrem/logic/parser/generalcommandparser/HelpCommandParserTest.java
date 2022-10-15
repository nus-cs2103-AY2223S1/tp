package seedu.foodrem.logic.parser.generalcommandparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.getCommandHelpMessage;
import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.getGeneralHelpMessage;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.foodrem.enums.CommandWord;
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
        assertEquals(numberOfCommands, CommandWord.values().length);
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

        assertEquals(EXPECTED_ALL_COMMANDS, CommandWord.listAllCommandWords());

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
        assertEquals(HelpCommand.DEFAULT_HELP_MESSAGE, CommandWord.parseWord("").getUsage());
    }

    @Test
    void parse_nonExistentCommand() {
        assertParseSuccess(parser, CommandWord.DEFAULT.getCommandWord(),
                new HelpCommand(String.format(HelpCommand.NOT_A_COMMAND, "default")));
        assertParseSuccess(parser, "helps",
                new HelpCommand(String.format(HelpCommand.NOT_A_COMMAND, "helps")));
    }

    @Test
    void getHelp_nonExistentCommand() {
        assertEquals(CommandWord.DEFAULT.getUsage(), CommandWord.parseWord("destroytheworld").getUsage());
    }

    @Test
    void parse_generalCommands() {
        assertParseSuccess(parser, CommandWord.HELP_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandWord.parseWord("help"))));
        assertParseSuccess(parser, CommandWord.EXIT_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandWord.parseWord("exit"))));
        assertParseSuccess(parser, CommandWord.RESET_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandWord.parseWord("reset"))));
    }

    // TODO: Rewrite all the tests
    @Test
    void getHelp_generalCommands() {
        assertEquals(ExitCommand.getUsage(), CommandWord.parseWord("exit").getUsage());
        assertEquals(HelpCommand.getUsage(), CommandWord.parseWord("help").getUsage());
        assertEquals(ResetCommand.getUsage(), CommandWord.parseWord("reset").getUsage());
    }

    @Test
    void parse_itemCommands() {
        assertParseSuccess(parser, CommandWord.NEW_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandWord.parseWord("new"))));
        assertParseSuccess(parser, CommandWord.EDIT_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandWord.parseWord("edit"))));
        assertParseSuccess(parser, CommandWord.INCREMENT_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandWord.parseWord("inc"))));
        assertParseSuccess(parser, CommandWord.DECREMENT_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandWord.parseWord("dec"))));
        assertParseSuccess(parser, CommandWord.DELETE_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandWord.parseWord("del"))));
        assertParseSuccess(parser, CommandWord.FIND_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandWord.parseWord("find"))));
        assertParseSuccess(parser, CommandWord.LIST_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandWord.parseWord("list"))));
        assertParseSuccess(parser, CommandWord.SORT_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandWord.parseWord("sort"))));
        assertParseSuccess(parser, CommandWord.VIEW_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandWord.parseWord("view"))));
    }

    @Test
    void getHelp_itemCommands() {
        assertEquals(NewCommand.getUsage(), CommandWord.parseWord("new").getUsage());
        assertEquals(EditCommand.getUsage(), CommandWord.parseWord("edit").getUsage());
        assertEquals(IncrementCommand.getUsage(), CommandWord.parseWord("inc").getUsage());
        assertEquals(DecrementCommand.getUsage(), CommandWord.parseWord("dec").getUsage());
        assertEquals(DeleteCommand.getUsage(), CommandWord.parseWord("del").getUsage());
        assertEquals(FindCommand.getUsage(), CommandWord.parseWord("find").getUsage());
        assertEquals(ListCommand.getUsage(), CommandWord.parseWord("list").getUsage());
        assertEquals(SortCommand.getUsage(), CommandWord.parseWord("sort").getUsage());
        assertEquals(ViewCommand.getUsage(), CommandWord.parseWord("view").getUsage());
    }

    @Test
    void parse_tagCommands() {
        assertParseSuccess(parser, CommandWord.NEW_TAG_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandWord.parseWord("newtag"))));
        assertParseSuccess(parser, CommandWord.RENAME_TAG_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandWord.parseWord("renametag"))));
        assertParseSuccess(parser, CommandWord.TAG_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandWord.parseWord("tag"))));
        assertParseSuccess(parser, CommandWord.UNTAG_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandWord.parseWord("untag"))));
        assertParseSuccess(parser, CommandWord.DELETE_TAG_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandWord.parseWord("deletetag"))));
        assertParseSuccess(parser, CommandWord.LIST_TAG_COMMAND.getCommandWord(),
                new HelpCommand(getCommandHelpMessage(CommandWord.parseWord("listtag"))));
    }

    @Test
    void getHelp_tagCommands() {
        assertEquals(NewTagCommand.getUsage(), CommandWord.parseWord("newtag").getUsage());
        assertEquals(RenameTagCommand.getUsage(), CommandWord.parseWord("renametag").getUsage());
        assertEquals(TagCommand.getUsage(), CommandWord.parseWord("tag").getUsage());
        assertEquals(UntagCommand.getUsage(), CommandWord.parseWord("untag").getUsage());
        assertEquals(DeleteTagCommand.getUsage(), CommandWord.parseWord("deletetag").getUsage());
        assertEquals(ListTagCommand.getUsage(), CommandWord.parseWord("listtag").getUsage());
    }
}
