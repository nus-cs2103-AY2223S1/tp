package tuthub.logic.parser;

import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tuthub.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tuthub.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static tuthub.testutil.TypicalIndexes.INDEX_FIRST_TUTOR;

import org.junit.jupiter.api.Test;

import tuthub.commons.core.index.Index;
import tuthub.logic.commands.DeleteCommentCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommentCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommentCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommentCommandParserTest {

    private DeleteCommentCommandParser parser = new DeleteCommentCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommentCommand() {
        assertParseSuccess(parser, "1 1", new DeleteCommentCommand(INDEX_FIRST_TUTOR, Index.fromZeroBased(0)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_shorterArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommentCommand.MESSAGE_USAGE));
    }
}
