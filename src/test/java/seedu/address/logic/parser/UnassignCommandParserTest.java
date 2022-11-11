package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnassignCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the UnassignCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the UnassignCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class UnassignCommandParserTest {
    private UnassignCommandParser parser = new UnassignCommandParser();

    @Test
    public void parse_validArgs_returnsAssignCommand() {
        assertParseSuccess(parser, "1 n/P2MATH",
                new UnassignCommand(INDEX_FIRST_PERSON, TUITIONCLASS1.getName()));
    }

    @Test
    public void parse_invalidArgs_throwParseException() {
        assertParseFailure(parser, "abc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignCommand.FEEDBACK_MESSAGE));
    }
}
