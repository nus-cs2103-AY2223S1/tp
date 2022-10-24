package seedu.application.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.application.logic.commands.DeleteInterviewCommand;

import static seedu.application.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteInterviewCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteInterviewCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteInterviewCommandParserTest {

    private DeleteInterviewCommandParser parser = new DeleteInterviewCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteInterviewCommand() {
        assertParseSuccess(parser, "1", new DeleteInterviewCommand(INDEX_FIRST_APPLICATION));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteInterviewCommand.MESSAGE_USAGE));
    }
}
