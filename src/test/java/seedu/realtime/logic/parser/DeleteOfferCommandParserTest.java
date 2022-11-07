package seedu.realtime.logic.parser;

import static seedu.realtime.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.realtime.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.realtime.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.realtime.testutil.TypicalIndexes.FIRST_INDEX;

import org.junit.jupiter.api.Test;

import seedu.realtime.logic.commands.DeleteOfferCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteOfferCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteOfferCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteOfferCommandParserTest {

    private DeleteOfferCommandParser parser = new DeleteOfferCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteOfferCommand() {
        assertParseSuccess(parser, "1", new DeleteOfferCommand(FIRST_INDEX));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOfferCommand.MESSAGE_USAGE));
    }
}
