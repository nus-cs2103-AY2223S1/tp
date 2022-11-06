package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static jarvis.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import jarvis.logic.commands.UnmarkTaskCommand;

public class UnmarkTaskCommandParserTest {

    private UnmarkTaskCommandParser parser = new UnmarkTaskCommandParser();

    @Test
    public void parse_validArgs_returnsUnmarkTaskCommand() {
        assertParseSuccess(parser, "1", new UnmarkTaskCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnmarkTaskCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnmarkTaskCommand.MESSAGE_USAGE));
    }
}
