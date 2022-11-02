package taskbook.logic.parser;

import static taskbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static taskbook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.contacts.ContactSortAddedChronologicalCommand;
import taskbook.logic.commands.contacts.ContactSortCommand;
import taskbook.logic.commands.contacts.ContactSortNameAlphabeticalCommand;
import taskbook.logic.commands.contacts.ContactSortNameReverseAlphabeticalCommand;
import taskbook.logic.parser.contacts.ContactSortCommandParser;

public class ContactSortCommandParserTest {
    private ContactSortCommandParser parser = new ContactSortCommandParser();

    @Test
    public void parse_validArgs_returnsContactSortDescriptionAlphabeticalCommand() {
        // Note: the space at the start of the userInput is necessary due to ArgumentTokenizer behavior.
        assertParseSuccess(parser, " s/a", new ContactSortNameAlphabeticalCommand());
    }

    @Test
    public void parse_validArgs_returnsContactSortDescriptionReverseAlphabeticalCommand() {
        // Note: the space at the start of the userInput is necessary due to ArgumentTokenizer behavior.
        assertParseSuccess(parser, " s/ra", new ContactSortNameReverseAlphabeticalCommand());
    }

    @Test
    public void parse_validArgs_returnsContactSortAddedChronologicalCommand() {
        // Note: the space at the start of the userInput is necessary due to ArgumentTokenizer behavior.
        assertParseSuccess(parser, " s/ca", new ContactSortAddedChronologicalCommand());
    }

    @Test
    public void parse_validArgs_returnsContactSortPhoneAscendingCommand() {
        // Note: the space at the start of the userInput is necessary due to ArgumentTokenizer behavior.
        assertParseSuccess(parser, " s/p", new ContactSortPhoneAscendingCommand());
    }

    @Test
    public void parse_validArgs_returnsContactSortPhoneDescendingCommand() {
        // Note: the space at the start of the userInput is necessary due to ArgumentTokenizer behavior.
        assertParseSuccess(parser, " s/rp", new ContactSortPhoneDescendingCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " a",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ContactSortCommand.MESSAGE_USAGE));
    }
}
