package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EXAM_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteExamCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteExamCommandParserTest {
    private DeleteExamCommandParser parser = new DeleteExamCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteExamCommand() {
        assertParseSuccess(parser, "1", new DeleteExamCommand(FIRST_INDEX));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // empty string
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteExamCommand.MESSAGE_USAGE));

        // wrong type
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteExamCommand.MESSAGE_USAGE));

        // big integer
        assertParseFailure(parser, "2147483648", MESSAGE_INVALID_EXAM_INDEX);

        // negative integer
        assertParseFailure(parser, "-1", MESSAGE_INVALID_EXAM_INDEX);
    }
}
