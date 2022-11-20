package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AttendanceDeleteCommand;

class AttendanceDeleteCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceDeleteCommand.MESSAGE_USAGE);

    private AttendanceDeleteCommandParser parser = new AttendanceDeleteCommandParser();

    @Test
    public void parse_validArgs_returnsAttendanceDeleteCommand() {
        assertParseSuccess(parser, "1", new AttendanceDeleteCommand(INDEX_FIRST_STUDENT));
    }
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", MESSAGE_INVALID_FORMAT);
    }
}
