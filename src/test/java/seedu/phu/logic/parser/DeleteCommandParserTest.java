package seedu.phu.logic.parser;

import static seedu.phu.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.phu.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.phu.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.phu.testutil.TypicalIndexes.INDEXES_FIRST_AND_SECOND_INTERNSHIP;
import static seedu.phu.testutil.TypicalIndexes.INDEXES_FIRST_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.phu.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validOneArg_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(INDEXES_FIRST_INTERNSHIP));
    }

    @Test
    public void parse_validMultipleArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1 2", new DeleteCommand(INDEXES_FIRST_AND_SECOND_INTERNSHIP));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
