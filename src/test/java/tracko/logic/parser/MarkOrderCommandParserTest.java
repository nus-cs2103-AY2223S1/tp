package tracko.logic.parser;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tracko.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tracko.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static tracko.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static tracko.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import tracko.logic.commands.order.MarkOrderCommand;
import tracko.logic.parser.order.MarkOrderCommandParser;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the MarkOrderCommand code. For example, inputs "1 -p" and "1 -p abc" take the
 * same path through the MarkOrderCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class MarkOrderCommandParserTest {

    private MarkOrderCommandParser parser = new MarkOrderCommandParser();

    @Test
    public void parse_validArgs_returnsMarkOrderCommand() {

        //mark order as paid
        assertParseSuccess(parser, "1 -p", new MarkOrderCommand(INDEX_FIRST, true, false));

        //mark order as delivered
        assertParseSuccess(parser, "1 -d", new MarkOrderCommand(INDEX_FIRST, false, true));

        //mark order as both paid and delivered
        assertParseSuccess(parser, "1 -d -p", new MarkOrderCommand(INDEX_FIRST, true, true));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkOrderCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkOrderCommand.MESSAGE_USAGE));

        //preamble is not a valid index
        assertParseFailure(parser, "a -p",
                String.format(MESSAGE_INVALID_INDEX));
    }

    @Test
    public void parse_invalidFlags_throwsParseException() {
        //wrong tag
        assertParseFailure(parser, "a -e",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkOrderCommand.MESSAGE_USAGE));

        //wrong tag but contains the correct tag format
        assertParseFailure(parser, "1 -p-d",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkOrderCommand.MESSAGE_USAGE));

        //wrong tag but contains the correct tag format
        assertParseFailure(parser, "1 -d-p",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkOrderCommand.MESSAGE_USAGE));
    }
}
