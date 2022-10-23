package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;


/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the AddMemberCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class AddTaskCommandParserTest {

    private static final String PLACEHOLDER_TASK_NAME = "Test task";

    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_validArgs_returnsAddTaskCommand() {
        assertParseSuccess(parser, PLACEHOLDER_TASK_NAME, new AddTaskCommand(PLACEHOLDER_TASK_NAME, null, null));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            AddTaskCommand.MESSAGE_TASK_NAME_FORMAT_ERROR));
    }
}
