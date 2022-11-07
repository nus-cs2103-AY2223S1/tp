package seedu.hrpro.logic.parser;

import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_MAX_PROJECT;
import static seedu.hrpro.testutil.TypicalIndexes.INVALID_INDEX_MAX_PLUS_ONE;
import static seedu.hrpro.testutil.TypicalIndexes.VALID_INDEX_MAX;
import static seedu.hrpro.testutil.TypicalIndexes.VALID_INDEX_ONE;

import org.junit.jupiter.api.Test;

import seedu.hrpro.logic.commands.ViewCommand;

/**
 * Contains test cases for ViewCommandParser.
 */
public class ViewCommandParserTest {
    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_validArgs_returnViewCommand() {
        // first index
        assertParseSuccess(parser, VALID_INDEX_ONE, new ViewCommand(INDEX_FIRST_PROJECT));

        // max index
        assertParseSuccess(parser, VALID_INDEX_MAX, new ViewCommand(INDEX_MAX_PROJECT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE);

        // alphanumeric input
        assertParseFailure(parser, "CS2103T TP", expectedMessage);

        // random characters
        assertParseFailure(parser, "!!!", expectedMessage);

        // empty input
        assertParseFailure(parser, "", expectedMessage);

        // zero index
        assertParseFailure(parser, "0", expectedMessage);

        // negative index
        assertParseFailure(parser, "-1", expectedMessage);

        // index larger than max integer
        assertParseFailure(parser, INVALID_INDEX_MAX_PLUS_ONE, expectedMessage);

    }
}
