package bookface.logic.parser.list;

import static bookface.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookface.logic.parser.CommandParserTestUtil.assertParseFailure;
import static bookface.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import bookface.logic.commands.list.ListBooksCommand;
import bookface.logic.commands.list.ListCommand;
import bookface.logic.commands.list.ListUsersCommand;


public class ListCommandParserTest {
    private final ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_validArgs_returnsListUsersCommand() {
        assertParseSuccess(parser, ListUsersCommand.COMMAND_WORD, new ListUsersCommand());
    }

    @Test
    public void parse_validArgs_returnsListBooksCommand() {
        assertParseSuccess(parser, ListBooksCommand.COMMAND_WORD, new ListBooksCommand());
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "  ", String
                .format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "test", String
                .format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }
}
