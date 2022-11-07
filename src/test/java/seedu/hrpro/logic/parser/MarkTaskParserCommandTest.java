package seedu.hrpro.logic.parser;

import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_MAX_TASK;

import org.junit.jupiter.api.Test;

import seedu.hrpro.logic.commands.MarkTaskCommand;

/**
 * Contains test cases for MarkTaskCommandParser.
 */
public class MarkTaskParserCommandTest {
    private MarkTaskCommandParser parser = new MarkTaskCommandParser();

    @Test
    public void parse_validArgs_returnMarkTaskCommand() {
        // first index
        assertParseSuccess(parser, "1", new MarkTaskCommand(INDEX_FIRST_TASK));

        // max index
        assertParseSuccess(parser, Long.toString(Integer.MAX_VALUE), new MarkTaskCommand(INDEX_MAX_TASK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkTaskCommand.MESSAGE_USAGE);

        // alphanumeric input
        assertParseFailure(parser, "CS2103T TP", expectedMessage);

        // random character input
        assertParseFailure(parser, "!!!", expectedMessage);

        // blank input
        assertParseFailure(parser, "", expectedMessage);

        // zero index
        assertParseFailure(parser, "0", expectedMessage);

        // negative index
        assertParseFailure(parser, "-9", expectedMessage);

        // index larger than max integer
        assertParseFailure(parser, Long.toString(Integer.MAX_VALUE + 1), expectedMessage);
    }
}
