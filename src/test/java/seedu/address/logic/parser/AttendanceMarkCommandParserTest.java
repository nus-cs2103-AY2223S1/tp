package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MARK_ATTENDANCE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AttendanceMarkCommand;
import seedu.address.model.attendance.Attendance;

class AttendanceMarkCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceMarkCommand.MESSAGE_USAGE);

    private AttendanceMarkCommandParser parser = new AttendanceMarkCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + VALID_LESSON_ONE + VALID_MARK_ATTENDANCE;
        AttendanceMarkCommand expectedCommand = new AttendanceMarkCommand(targetIndex, "1", new Attendance("1"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        // no student index specified
        assertParseFailure(parser, VALID_LESSON_ONE + VALID_MARK_ATTENDANCE, MESSAGE_INVALID_FORMAT);

        // negative index
        assertParseFailure(parser, "-1" + VALID_LESSON_ONE + VALID_MARK_ATTENDANCE, MESSAGE_INVALID_FORMAT);

        // missing lesson number
        assertParseFailure(parser, "2" + VALID_MARK_ATTENDANCE, MESSAGE_INVALID_FORMAT);

        // missing attendance value
        assertParseFailure(parser, "2" + VALID_LESSON_ONE, MESSAGE_INVALID_FORMAT);

        // missing all 3
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

}
