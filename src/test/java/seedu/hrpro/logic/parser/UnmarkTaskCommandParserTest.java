package seedu.hrpro.logic.parser;

import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_MAX_TASK;
import static seedu.hrpro.testutil.TypicalIndexes.INVALID_INDEX_MAX_PLUS_ONE;
import static seedu.hrpro.testutil.TypicalIndexes.VALID_INDEX_MAX;
import static seedu.hrpro.testutil.TypicalIndexes.VALID_INDEX_ONE;

import org.junit.jupiter.api.Test;

import seedu.hrpro.logic.commands.UnmarkTaskCommand;

/**
 * Contains test cases for UnmarkTaskCommandParser.
 */
public class UnmarkTaskCommandParserTest {
    private UnmarkTaskCommandParser parser = new UnmarkTaskCommandParser();

    @Test
    public void parse_validArgs_returnUnmarkTaskCommand() {
        // first index
        assertParseSuccess(parser, VALID_INDEX_ONE, new UnmarkTaskCommand(INDEX_FIRST_TASK));

        // max index
        assertParseSuccess(parser, VALID_INDEX_MAX, new UnmarkTaskCommand(INDEX_MAX_TASK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkTaskCommand.MESSAGE_USAGE);

        // alphanumeric input
        assertParseFailure(parser, "CS2103T TP", expectedMessage);

        // random characters input
        assertParseFailure(parser, "!!!", expectedMessage);

        // blank input
        assertParseFailure(parser, "", expectedMessage);

        // zero index input
        assertParseFailure(parser, "0", expectedMessage);

        // negative index input
        assertParseFailure(parser, "-1", expectedMessage);

        // index larger than max integer
        assertParseFailure(parser, INVALID_INDEX_MAX_PLUS_ONE, expectedMessage);
    }
}
