package seedu.address.logic.parser.property;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.property.DeletePropertyCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteBuyerCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteBuyerCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeletePropertyCommandParserTest {

    private DeletePropertyCommandParser parser = new DeletePropertyCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeletePropertyCommand(INDEX_FIRST_ITEM));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // alphabetical input
        assertParseFailure(parser, "a", MESSAGE_INVALID_INDEX);

        // 0 is not a valid input
        assertParseFailure(parser, "0", MESSAGE_INVALID_INDEX);
        // out of range input
        assertParseFailure(parser, "99999999999999999999999999999999999999", MESSAGE_INVALID_INDEX);
    }
}
