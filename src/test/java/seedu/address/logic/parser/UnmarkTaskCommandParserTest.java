package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnmarkTaskCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the UnmarkTaskCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the UnmarkTaskCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class UnmarkTaskCommandParserTest {

    private UnmarkTaskCommandParser parser = new UnmarkTaskCommandParser();

    @Test
    public void parse_validArgs_returnsUnmarkTaskCommand() {
        assertParseSuccess(parser, "1", new UnmarkTaskCommand(Index.fromOneBased(1)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(
                parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkTaskCommand.MESSAGE_USAGE));
    }
}
