package seedu.foodrem.logic.parser.generalcommandparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import static seedu.foodrem.logic.parser.generalcommandparser.HelpCommandParser.getHelp;

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
import seedu.foodrem.logic.commands.tagcommands.NewTagCommand;
import seedu.foodrem.logic.commands.tagcommands.RenameTagCommand;
import seedu.foodrem.logic.commands.tagcommands.TagCommand;
import seedu.foodrem.logic.commands.tagcommands.UntagCommand;
import seedu.foodrem.logic.parser.exceptions.ParseException;

class HelpCommandParserTest {

    private final HelpCommandParser parser = new HelpCommandParser();

    @Test
    void parse_text() {
        assertEquals("https://se-education.org/addressbook-level3/UserGuide.html",
                USER_GUIDE_URL);
        assertEquals("For more information please head to:\n"
                + "https://se-education.org/addressbook-level3/UserGuide.html", MORE_INFORMATION);

        assertEquals("help\nreset\nexit\nnew\nlist\nfind\nedit\ninc"
                        + "\ndec\nsort\ndel\nnewtag\nrenametag\ntag\nuntag",
                CommandWord.listAllCommandWords());

        assertEquals("To receive help for a specific command, enter "
                        + "\"help COMMAND_WORD\" in the command box, where COMMAND_WORD is any one of the following:\n"
                        + "help\nreset\nexit\nnew\nlist\nfind\nedit\ninc"
                        + "\ndec\nsort\ndel\nnewtag\nrenametag\ntag\nuntag\n\n"
                        + "For more information please head to:\n"
                        + "https://se-education.org/addressbook-level3/UserGuide.html",
                HELP_FOR_ALL_COMMANDS);

        assertEquals("hehe\n\nTo receive help for a specific command, enter "
                        + "\"help COMMAND_WORD\" in the command box, where COMMAND_WORD is any one of the following:\n"
                        + "help\nreset\nexit\nnew\nlist\nfind\nedit\ninc"
                        + "\ndec\nsort\ndel\nnewtag\nrenametag\ntag\nuntag\n\n"
                        + "For more information please head to:\n"
                        + "https://se-education.org/addressbook-level3/UserGuide.html",
                String.format(HELP_FOR_SPECIFIC_COMMAND, "hehe"));

        // Not a command constant
        assertEquals(String.format(NOT_A_COMMAND, "testing"),
                "\"testing\" is not a valid command\n\n"
                        + "To receive help for a specific command, enter "
                        + "\"help COMMAND_WORD\" in the command box, where COMMAND_WORD is any one of the following:\n"
                        + "help\nreset\nexit\nnew\nlist\nfind\nedit\ninc\ndec\nsort\ndel\nnewtag\nrenametag\ntag\nuntag\n\n"
                        + "For more information please head to:\n"
                        + "https://se-education.org/addressbook-level3/UserGuide.html");
    }

    @Test
    void parse_blank_commands() {
        // Blank
        assertParseSuccess(parser, "",
                new HelpCommand(HELP_FOR_ALL_COMMANDS));
    }

    @Test
    void parse_not_a_command() {
        // NOT_A_COMMAND
        assertParseSuccess(parser, DEFAULT.getValue(),
                new HelpCommand(String.format(NOT_A_COMMAND, "default")));
        assertParseSuccess(parser, "helps",
                new HelpCommand(String.format(NOT_A_COMMAND, "helps")));
    }

    @Test
    void parse_general_commands() {
        // General Commands
        assertParseSuccess(parser, HELP_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getHelp(getCommandWordFromCommandWordString("help")))));
        assertParseSuccess(parser, EXIT_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getHelp(getCommandWordFromCommandWordString("exit")))));
        assertParseSuccess(parser, RESET_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getHelp(getCommandWordFromCommandWordString("reset")))));
    }

    @Test
    void parse_item_commands() {
        // Item Commands
        assertParseSuccess(parser, NEW_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getHelp(getCommandWordFromCommandWordString("new")))));
        assertParseSuccess(parser, EDIT_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getHelp(getCommandWordFromCommandWordString("edit")))));
        assertParseSuccess(parser, INCREMENT_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getHelp(getCommandWordFromCommandWordString("inc")))));
        assertParseSuccess(parser, DECREMENT_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getHelp(getCommandWordFromCommandWordString("dec")))));
        assertParseSuccess(parser, DELETE_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getHelp(getCommandWordFromCommandWordString("del")))));
        assertParseSuccess(parser, FIND_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getHelp(getCommandWordFromCommandWordString("find")))));
        assertParseSuccess(parser, LIST_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getHelp(getCommandWordFromCommandWordString("list")))));
        assertParseSuccess(parser, SORT_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getHelp(getCommandWordFromCommandWordString("sort")))));
    }

    @Test
    void parse_tag_commands() {
        // Tag Commands
        assertParseSuccess(parser, NEW_TAG_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getHelp(getCommandWordFromCommandWordString("newtag")))));
        assertParseSuccess(parser, RENAME_TAG_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getHelp(getCommandWordFromCommandWordString("renametag")))));
        assertParseSuccess(parser, TAG_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getHelp(getCommandWordFromCommandWordString("tag")))));
        assertParseSuccess(parser, UNTAG_COMMAND.getValue(),
                new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND,
                        getHelp(getCommandWordFromCommandWordString("untag")))));
    }

    @Test
    void get_help_success() {
        assertEquals(NewCommand.MESSAGE_USAGE,
                getHelp(getCommandWordFromCommandWordString("new")));
        assertEquals(EditCommand.MESSAGE_USAGE,
                getHelp(getCommandWordFromCommandWordString("edit")));
        assertEquals(IncrementCommand.MESSAGE_USAGE,
                getHelp(getCommandWordFromCommandWordString("inc")));
        assertEquals(DecrementCommand.MESSAGE_USAGE,
                getHelp(getCommandWordFromCommandWordString("dec")));
        assertEquals(DeleteCommand.MESSAGE_USAGE,
                getHelp(getCommandWordFromCommandWordString("del")));
        assertEquals(NewTagCommand.MESSAGE_USAGE,
                getHelp(getCommandWordFromCommandWordString("newtag")));
        assertEquals(RenameTagCommand.MESSAGE_USAGE,
                getHelp(getCommandWordFromCommandWordString("renametag")));
        assertEquals(ResetCommand.MESSAGE_USAGE,
                getHelp(getCommandWordFromCommandWordString("reset")));
        assertEquals(FindCommand.MESSAGE_USAGE,
                getHelp(getCommandWordFromCommandWordString("find")));
        assertEquals(ListCommand.MESSAGE_USAGE,
                getHelp(getCommandWordFromCommandWordString("list")));
        assertEquals(SortCommand.MESSAGE_USAGE,
                getHelp(getCommandWordFromCommandWordString("sort")));
        assertEquals(ExitCommand.MESSAGE_USAGE,
                getHelp(getCommandWordFromCommandWordString("exit")));
        assertEquals(HelpCommand.MESSAGE_USAGE,
                getHelp(getCommandWordFromCommandWordString("help")));
        assertEquals(TagCommand.MESSAGE_USAGE,
                getHelp(getCommandWordFromCommandWordString("tag")));
        assertEquals(UntagCommand.MESSAGE_USAGE,
                getHelp(getCommandWordFromCommandWordString("untag")));
    }

    @Test
    void get_help_throws_error() {
        assertThrows(ParseException.class,
                () -> getHelp(getCommandWordFromCommandWordString("helps")));
    }
}
