package longtimenosee.logic.parser;

import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.logic.parser.CommandParserTestUtil.assertParseFailure;
import static longtimenosee.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static longtimenosee.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import longtimenosee.logic.commands.PolicyAssignedListCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class PolicyAssignedListCommandParserTest {

    private PolicyAssignedListCommandParser parser = new PolicyAssignedListCommandParser();

    @Test
    public void parse_validArgs_returnsAssignedListCommand() {
        assertParseSuccess(parser, "1",
                new PolicyAssignedListCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PolicyAssignedListCommand.MESSAGE_USAGE));
    }
}
