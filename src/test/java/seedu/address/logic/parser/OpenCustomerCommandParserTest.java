package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.OpenCustomerCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the OpenCustomerCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the OpenCustomerCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class OpenCustomerCommandParserTest {

    private OpenCustomerCommandParser parser = new OpenCustomerCommandParser();

    @Test
    public void parse_validArgs_returnsOpenCustomerCommand() {
        assertParseSuccess(parser, "1", new OpenCustomerCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, OpenCustomerCommand.MESSAGE_USAGE));
    }
}
