package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CLASS_GROUP_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_LIST_SIZE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_GROUP_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AttendanceAddCommand;


class AttendanceAddCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceAddCommand.MESSAGE_USAGE);
    private AttendanceAddCommandParser parser = new AttendanceAddCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + CLASS_GROUP_DESC_BOB + VALID_ATTENDANCE_LIST_SIZE;
        AttendanceAddCommand expectedCommand = new AttendanceAddCommand(targetIndex, VALID_CLASS_GROUP_BOB, "5");

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        // no student index specified
        assertParseFailure(parser, CLASS_GROUP_DESC_BOB + VALID_ATTENDANCE_LIST_SIZE, MESSAGE_INVALID_FORMAT);

        // negative index
        assertParseFailure(parser, "-1" + CLASS_GROUP_DESC_BOB + VALID_ATTENDANCE_LIST_SIZE, MESSAGE_INVALID_FORMAT);

        // missing class group
        assertParseFailure(parser, "1" + VALID_ATTENDANCE_LIST_SIZE, MESSAGE_INVALID_FORMAT);

        // missing attendance list size
        assertParseFailure(parser, "1" + CLASS_GROUP_DESC_BOB, MESSAGE_INVALID_FORMAT);

        // missing all 3
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }
}
