package swift.logic.parser;

import static swift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static swift.logic.parser.CommandParserTestUtil.assertParseFailure;
import static swift.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static swift.testutil.TypicalTaskIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import swift.logic.commands.SelectContactCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the SelectContactCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the SelectContactCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class SelectContactCommandParserTest {

    private SelectContactCommandParser parser = new SelectContactCommandParser();

    @Test
    public void parse_validArgs_returnsSelectContactCommand() {
        assertParseSuccess(parser, "1", new SelectContactCommand(INDEX_FIRST_TASK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String
                .format(MESSAGE_INVALID_COMMAND_FORMAT, SelectContactCommand.MESSAGE_USAGE));
    }
}
