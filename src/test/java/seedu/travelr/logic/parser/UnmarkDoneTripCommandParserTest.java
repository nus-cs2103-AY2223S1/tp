package seedu.travelr.logic.parser;

import static seedu.travelr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.travelr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.travelr.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.travelr.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.travelr.commons.core.index.Index;
import seedu.travelr.logic.commands.UnmarkDoneTripCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class UnmarkDoneTripCommandParserTest {

    private UnmarkDoneTripCommandParser parser = new UnmarkDoneTripCommandParser();

    @Test
    public void parse_validArgs_returnsMarkTripDoneCommand() {
        assertParseSuccess(parser, "1", new UnmarkDoneTripCommand(Index.fromOneBased(1)));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format("Index cannot be empty."
                + "\n\n" + MESSAGE_INVALID_COMMAND_FORMAT, UnmarkDoneTripCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "n/asd", String.format(MESSAGE_INVALID_INDEX
                + "\n\n" + MESSAGE_INVALID_COMMAND_FORMAT, UnmarkDoneTripCommand.MESSAGE_USAGE));
    }

}
