package seedu.foodrem.logic.parser.generalcommandparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.foodrem.enums.CommandWord.DECREMENT_COMMAND;
import static seedu.foodrem.enums.CommandWord.DEFAULT;
import static seedu.foodrem.enums.CommandWord.DELETE_COMMAND;
import static seedu.foodrem.enums.CommandWord.EDIT_COMMAND;
import static seedu.foodrem.enums.CommandWord.EXIT_COMMAND;
import static seedu.foodrem.enums.CommandWord.FIND_COMMAND;
import static seedu.foodrem.enums.CommandWord.HELP_COMMAND;
import static seedu.foodrem.enums.CommandWord.INCREMENT_COMMAND;
import static seedu.foodrem.enums.CommandWord.LIST_COMMAND;
import static seedu.foodrem.enums.CommandWord.NEW_COMMAND;
import static seedu.foodrem.enums.CommandWord.NEW_TAG_COMMAND;
import static seedu.foodrem.enums.CommandWord.RENAME_TAG_COMMAND;
import static seedu.foodrem.enums.CommandWord.RESET_COMMAND;
import static seedu.foodrem.enums.CommandWord.SORT_COMMAND;
import static seedu.foodrem.enums.CommandWord.TAG_COMMAND;
import static seedu.foodrem.enums.CommandWord.UNTAG_COMMAND;
import static seedu.foodrem.enums.CommandWord.getCommandWordFromCommandWordString;
import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.HELP_FOR_ALL_COMMANDS;
import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.HELP_FOR_SPECIFIC_COMMAND;
import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.MORE_INFORMATION;
import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.NOT_A_COMMAND;
import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.USER_GUIDE_URL;
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
import seedu.foodrem.logic.commands.tagcommands.DeleteTagCommand;
import seedu.foodrem.logic.commands.tagcommands.ListTagCommand;
import seedu.foodrem.logic.commands.tagcommands.NewTagCommand;
import seedu.foodrem.logic.commands.tagcommands.RenameTagCommand;
import seedu.foodrem.logic.commands.tagcommands.TagCommand;
import seedu.foodrem.logic.commands.tagcommands.UntagCommand;

class HelpCommandParserTest {

    private final HelpCommandParser parser = new HelpCommandParser();

    @Test
    void parse_text() {
        assertEquals("https://se-education.org/addressbook-level3/UserGuide.html",
                USER_GUIDE_URL);
        assertEquals("For more information please head to:\n"
                + "https://se-education.org/addressbook-level3/UserGuide.html", MORE_INFORMATION);

        assertEquals("help\nreset\nexit\nnew\nlist\nfind\nedit\ninc"
                        + "\ndec\nsort\ndel\nnewtag\nrenametag\ntag\nuntag\ndeletetag\nlisttag",
                CommandWord.listAllCommandWords());

        assertEquals("To receive help for a specific command, enter "
                        + "\"help COMMAND_WORD\" in the command box, where COMMAND_WORD is any one of the following:\n"
                        + "help\nreset\nexit\nnew\nlist\nfind\nedit\ninc"
                        + "\ndec\nsort\ndel\nnewtag\nrenametag\ntag\nuntag\ndeletetag\nlisttag\n\n"
                        + "For more information please head to:\n"
                        + "https://se-education.org/addressbook-level3/UserGuide.html",
                HELP_FOR_ALL_COMMANDS);

        assertEquals("hehe\n\nTo receive help for a specific command, enter "
                        + "\"help COMMAND_WORD\" in the command box, where COMMAND_WORD is any one of the following:\n"
                        + "help\nreset\nexit\nnew\nlist\nfind\nedit\ninc"
                        + "\ndec\nsort\ndel\nnewtag\nrenametag\ntag\nuntag\ndeletetag\nlisttag\n\n"
                        + "For more information please head to:\n"
                        + "https://se-education.org/addressbook-level3/UserGuide.html",
                String.format(HELP_FOR_SPECIFIC_COMMAND, "hehe"));

        // Not a command constant
        assertEquals(String.format(NOT_A_COMMAND, "testing"),
                "\"testing\" is not a valid command\n\n"
                        + "To receive help for a specific command, enter "
                        + "\"help COMMAND_WORD\" in the command box, where COMMAND_WORD is any one of the following:\n"
                        + "help\nreset\nexit\nnew\nlist\nfind\nedit\ninc"
                        + "\ndec\nsort\ndel\nnewtag\nrenametag\ntag\nuntag\ndeletetag\nlisttag\n\n"
                        + "For more information please head to:\n"
                        + "https://se-education.org/addressbook-level3/UserGuide.html");
    }

    @Test
    void parse_blankCommands() {
        // Blank
        assertParseSuccess(parser, "",
                new HelpCommand(HELP_FOR_ALL_COMMANDS));
    }

    @Test
    void parse_notCommand() {
        // NOT_A_COMMAND
        assertParseSuccess(parser, DEFAULT.getValue(),
                new HelpCommand(String.format(NOT_A_COMMAND, "default")));
        assertParseSuccess(parser, "helps",
                new HelpCommand(String.format(NOT_A_COMMAND, "helps")));
    }

    @Test
    void parse_generalCommands() {
        // General Commands
        assertParseSuccess(parser, HELP_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getCommandWordFromCommandWordString("help").getHelp())));
        assertParseSuccess(parser, EXIT_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getCommandWordFromCommandWordString("exit").getHelp())));
        assertParseSuccess(parser, RESET_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getCommandWordFromCommandWordString("reset").getHelp())));
    }

    @Test
    void parse_itemCommands() {
        // Item Commands
        assertParseSuccess(parser, NEW_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getCommandWordFromCommandWordString("new").getHelp())));
        assertParseSuccess(parser, EDIT_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getCommandWordFromCommandWordString("edit").getHelp())));
        assertParseSuccess(parser, INCREMENT_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getCommandWordFromCommandWordString("inc").getHelp())));
        assertParseSuccess(parser, DECREMENT_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getCommandWordFromCommandWordString("dec").getHelp())));
        assertParseSuccess(parser, DELETE_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getCommandWordFromCommandWordString("del").getHelp())));
        assertParseSuccess(parser, FIND_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getCommandWordFromCommandWordString("find").getHelp())));
        assertParseSuccess(parser, LIST_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getCommandWordFromCommandWordString("list").getHelp())));
        assertParseSuccess(parser, SORT_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getCommandWordFromCommandWordString("sort").getHelp())));
    }

    @Test
    void parse_tagCommands() {
        // Tag Commands
        assertParseSuccess(parser, NEW_TAG_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getCommandWordFromCommandWordString("newtag").getHelp())));
        assertParseSuccess(parser, RENAME_TAG_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getCommandWordFromCommandWordString("renametag").getHelp())));
        assertParseSuccess(parser, TAG_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getCommandWordFromCommandWordString("tag").getHelp())));
        assertParseSuccess(parser, UNTAG_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getCommandWordFromCommandWordString("untag").getHelp())));
    }

    @Test
    void get_help_success() {
        assertEquals(NewCommand.MESSAGE_USAGE,
                getCommandWordFromCommandWordString("new").getHelp());
        assertEquals(EditCommand.MESSAGE_USAGE,
                getCommandWordFromCommandWordString("edit").getHelp());
        assertEquals(IncrementCommand.MESSAGE_USAGE,
                getCommandWordFromCommandWordString("inc").getHelp());
        assertEquals(DecrementCommand.MESSAGE_USAGE,
                getCommandWordFromCommandWordString("dec").getHelp());
        assertEquals(DeleteCommand.MESSAGE_USAGE,
                getCommandWordFromCommandWordString("del").getHelp());
        assertEquals(NewTagCommand.MESSAGE_USAGE,
                getCommandWordFromCommandWordString("newtag").getHelp());
        assertEquals(RenameTagCommand.MESSAGE_USAGE,
                getCommandWordFromCommandWordString("renametag").getHelp());
        assertEquals(ResetCommand.MESSAGE_USAGE,
                getCommandWordFromCommandWordString("reset").getHelp());
        assertEquals(FindCommand.MESSAGE_USAGE,
                getCommandWordFromCommandWordString("find").getHelp());
        assertEquals(ListCommand.MESSAGE_USAGE,
                getCommandWordFromCommandWordString("list").getHelp());
        assertEquals(SortCommand.MESSAGE_USAGE,
                getCommandWordFromCommandWordString("sort").getHelp());
        assertEquals(ExitCommand.MESSAGE_USAGE,
                getCommandWordFromCommandWordString("exit").getHelp());
        assertEquals(HelpCommand.MESSAGE_USAGE,
                getCommandWordFromCommandWordString("help").getHelp());
        assertEquals(TagCommand.MESSAGE_USAGE,
                getCommandWordFromCommandWordString("tag").getHelp());
        assertEquals(UntagCommand.MESSAGE_USAGE,
                getCommandWordFromCommandWordString("untag").getHelp());
        assertEquals(DeleteTagCommand.MESSAGE_USAGE,
                getCommandWordFromCommandWordString("deletetag").getHelp());
        assertEquals(ListTagCommand.MESSAGE_USAGE,
                getCommandWordFromCommandWordString("listtag").getHelp());
    }

    @Test
    void getHelp_throws_error() {
        assertEquals(DEFAULT.getHelp(),
                getCommandWordFromCommandWordString("helps").getHelp());
    }
}
