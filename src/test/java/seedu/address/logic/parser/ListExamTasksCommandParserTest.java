package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListExamTasksCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the ListExamTasksCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the ListExamTasksCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class ListExamTasksCommandParserTest {
    private ListExamTasksCommandParser parser = new ListExamTasksCommandParser();

    @Test
    public void parse_validArgs_returnsListExamTasksCommand() {
        assertParseSuccess(parser, "1", new ListExamTasksCommand(FIRST_INDEX));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListExamTasksCommand.MESSAGE_USAGE));
    }
}
