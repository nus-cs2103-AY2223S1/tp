package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnlinkExamCommand;

/**
 * Contains unit tests for {@code UnmarkCommandParser}.
 */
public class UnlinkExamCommandParserTest {
    private UnlinkExamCommandParser parser = new UnlinkExamCommandParser();

    @Test
    public void parse_validArgs_returnsUnlinkExamCommand() {
        assertParseSuccess(parser, "1", new UnlinkExamCommand(INDEX_FIRST_TASK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // empty string
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnlinkExamCommand.MESSAGE_USAGE));

        // wrong type
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnlinkExamCommand.MESSAGE_USAGE));

        // big integer
        assertParseFailure(parser, "2147483648", MESSAGE_INVALID_TASK_INDEX);

        // negative integer
        assertParseFailure(parser, "-1", MESSAGE_INVALID_TASK_INDEX);
    }
}
