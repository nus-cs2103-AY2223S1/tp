package foodwhere.logic.parser;

import static foodwhere.logic.parser.CommandParserTestUtil.assertParseFailure;
import static foodwhere.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import foodwhere.logic.commands.SDeleteCommand;
import foodwhere.testutil.TypicalIndexes;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the SDeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the SDeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class SDeleteCommandParserTest {

    private static final String WHITESPACE = " \t\r\n";

    private SDeleteCommandParser parser = new SDeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new SDeleteCommand(TypicalIndexes.INDEX_FIRST_STALL));
        assertParseSuccess(parser, WHITESPACE + "1" + WHITESPACE,
                new SDeleteCommand(TypicalIndexes.INDEX_FIRST_STALL));

    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                SDeleteCommand.MESSAGE_INVALID_INDEX_ERROR);
    }

    @Test
    public void parse_extraIndex_throwsParseException() {
        assertParseFailure(parser, "1 2",
                SDeleteCommand.MESSAGE_INVALID_INDEX_ERROR);
    }
}
