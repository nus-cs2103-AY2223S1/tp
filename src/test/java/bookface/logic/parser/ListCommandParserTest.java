package bookface.logic.parser;

import static bookface.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookface.logic.parser.CommandParserTestUtil.assertParseFailure;
import static bookface.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import bookface.logic.commands.ListUsersCommand;


public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_validArgs_returnsListUsersCommand() {
        assertParseSuccess(parser, ListUsersCommand.COMMAND_WORD_USER, new ListUsersCommand());
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "  ", String
                .format(MESSAGE_INVALID_COMMAND_FORMAT, ListUsersCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "test", String
                .format(MESSAGE_INVALID_COMMAND_FORMAT, ListUsersCommand.MESSAGE_USAGE));
    }
}
