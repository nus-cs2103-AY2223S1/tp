package seedu.trackascholar.logic.parser;

import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.trackascholar.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.logic.commands.PinCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the PinCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the PinCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class PinCommandParserTest {

    private static final PinCommandParser parser = new PinCommandParser();
    private static final String ERROR_MESSAGE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, PinCommand.MESSAGE_USAGE);

    @Test
    public void parse_validArgs_returnsPinCommand() {
        assertParseSuccess(parser, "1", new PinCommand(INDEX_FIRST_APPLICANT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", ERROR_MESSAGE);
    }

    @Test
    public void parse_emptyArgForPinCommand_throwsParseException() {
        assertParseFailure(parser, "", ERROR_MESSAGE);
        assertParseFailure(parser, "     ", ERROR_MESSAGE);
    }
}
