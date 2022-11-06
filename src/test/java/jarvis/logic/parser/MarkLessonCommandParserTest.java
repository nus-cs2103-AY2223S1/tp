package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static jarvis.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import jarvis.logic.commands.MarkLessonCommand;

public class MarkLessonCommandParserTest {

    private MarkLessonCommandParser parser = new MarkLessonCommandParser();

    @Test
    public void parse_validArgs_returnsMarkLessonCommand() {
        assertParseSuccess(parser, "1", new MarkLessonCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkLessonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkLessonCommand.MESSAGE_USAGE));
    }
}
