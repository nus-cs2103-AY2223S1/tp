package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ELEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteClientCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteClientCommandParserTest {

    private DeleteClientCommandParser parser = new DeleteClientCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " i/a",
                ParserUtil.MESSAGE_INVALID_INDEX);
    }

    //Here onwards are tests for equivalent partition
    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, " i/1", new DeleteClientCommand(INDEX_FIRST_ELEMENT));
    }


    @Test
    public void parse_negativeIndex_throwsParseException() {
        assertParseFailure(parser, " i/-1",
                ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_zeroIndex_throwsParseException() {
        assertParseFailure(parser, " i/0",
                ParserUtil.MESSAGE_INVALID_INDEX);
    }
}
