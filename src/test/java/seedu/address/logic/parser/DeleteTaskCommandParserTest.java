package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.UnmarkCommand;

import static seedu.address.commons.core.Messages.*;
import static seedu.address.logic.commands.CommandTestUtil.EXAMDESCRIPTIONONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

/**
 * Contains unit tests for {@code DeleteCommandParser}.
 */
public class DeleteTaskCommandParserTest {

    private DeleteTaskCommandParser parser = new DeleteTaskCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteTaskCommand() {
        assertParseSuccess(parser, "1", new DeleteTaskCommand(INDEX_FIRST_TASK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // non-integer
        assertParseFailure(parser, "a b", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE));

        // empty string
        assertParseFailure(parser, "", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE));

        // zero index
        assertParseFailure(parser, "0", MESSAGE_INVALID_TASK_INDEX);

        // negative index
        assertParseFailure(parser, "-2",  MESSAGE_INVALID_TASK_INDEX);

        // positive signed index
        assertParseFailure(parser, "+3",  MESSAGE_INVALID_TASK_INDEX);

        // valid index followed by string
        assertParseFailure(parser, "1 i/ string", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE));

        // number bigger than max integer
        assertParseFailure(parser, "9999999999999999999999", MESSAGE_INVALID_TASK_INDEX);
    }
}
