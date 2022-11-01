package bookface.logic.parser;

import static bookface.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookface.logic.parser.CommandParserTestUtil.assertParseFailure;
import static bookface.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import bookface.logic.commands.ReturnCommand;
import bookface.testutil.TypicalIndexes;

public class ReturnCommandParserTest {
    private final ReturnCommandParser parser = new ReturnCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new ReturnCommand(TypicalIndexes.INDEX_FIRST_BOOK));
    }

    @Test
    public void parse_validArgsWhitespace_returnsDeleteCommand() {
        assertParseSuccess(parser, "  1  ", new ReturnCommand(TypicalIndexes.INDEX_FIRST_BOOK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String
                .format(MESSAGE_INVALID_COMMAND_FORMAT, ReturnCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleArgs_throwsParseException() {
        assertParseFailure(parser, "1 1", String
                .format(MESSAGE_INVALID_COMMAND_FORMAT, ReturnCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_zeroIndex_throwsParseException() {
        assertParseFailure(parser, "0", String
                .format(MESSAGE_INVALID_COMMAND_FORMAT, ReturnCommand.MESSAGE_USAGE));
    }
}
