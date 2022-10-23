package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.uninurse.logic.commands.DeleteConditionCommand;

/**
 * Contains unit tests for {@code DeleteConditionCommandParser}.
 */
public class DeleteConditionCommandParserTest {

    private final DeleteConditionCommandParser parser = new DeleteConditionCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_validArgs_success() {
        assertParseSuccess(parser, "1 1", new DeleteConditionCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE));
        assertParseSuccess(parser, " 1 1 ", new DeleteConditionCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE));
    }

    @Test
    public void parse_invalidPatientIndex_failure() {
        assertParseFailure(parser, "a 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteConditionCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "0 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteConditionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidTaskIndex_failure() {
        assertParseFailure(parser, "1 a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteConditionCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteConditionCommand.MESSAGE_USAGE));
    }
}
