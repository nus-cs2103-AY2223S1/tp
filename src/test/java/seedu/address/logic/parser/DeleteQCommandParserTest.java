package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_QUESTION;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteQCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteQCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteQCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteQCommandParserTest {

    private DeleteQCommandParser parser = new DeleteQCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteQCommand(INDEX_FIRST_QUESTION));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX,
                DeleteQCommand.MESSAGE_USAGE));
    }
}
