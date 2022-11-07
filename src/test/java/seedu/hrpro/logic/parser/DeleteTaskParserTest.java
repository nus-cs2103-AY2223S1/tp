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

import seedu.hrpro.logic.commands.DeleteTaskCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteTaskCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteTaskCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteTaskParserTest {

    private DeleteTaskCommandParser parser = new DeleteTaskCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        // first index
        assertParseSuccess(parser, VALID_INDEX_ONE, new DeleteTaskCommand(INDEX_FIRST_TASK));

        // max index
        assertParseSuccess(parser, VALID_INDEX_MAX, new DeleteTaskCommand(INDEX_MAX_TASK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE);

        // random character
        assertParseFailure(parser, "a", expectedMessage);

        // zero index
        assertParseFailure(parser, "0", expectedMessage);

        // negative index
        assertParseFailure(parser, "-1", expectedMessage);

        // index larger than max integer
        assertParseFailure(parser, INVALID_INDEX_MAX_PLUS_ONE, expectedMessage);
    }
}
