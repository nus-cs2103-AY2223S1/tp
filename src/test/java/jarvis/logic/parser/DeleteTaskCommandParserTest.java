package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static jarvis.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import jarvis.logic.commands.DeleteTaskCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteTaskCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteTaskCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteTaskCommandParserTest {

    private DeleteTaskCommandParser parser = new DeleteTaskCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteTaskCommand() {
        assertParseSuccess(parser, "1", new DeleteTaskCommand(INDEX_FIRST_TASK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteTaskCommand.MESSAGE_USAGE));
    }
}
