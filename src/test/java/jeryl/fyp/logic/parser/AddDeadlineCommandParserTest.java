package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jeryl.fyp.logic.commands.CommandTestUtil.DEADLINE_DATETIME_DESC_IP;
import static jeryl.fyp.logic.commands.CommandTestUtil.DEADLINE_DATETIME_DESC_TP;
import static jeryl.fyp.logic.commands.CommandTestUtil.DEADLINE_NAME_DESC_IP;
import static jeryl.fyp.logic.commands.CommandTestUtil.DEADLINE_NAME_DESC_TP;
import static jeryl.fyp.logic.commands.CommandTestUtil.INVALID_DEADLINE_DATETIME_DESC;
import static jeryl.fyp.logic.commands.CommandTestUtil.INVALID_DEADLINE_NAME_DESC;
import static jeryl.fyp.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static jeryl.fyp.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static jeryl.fyp.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static jeryl.fyp.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.STUDENT_ID_DESC_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_DEADLINE_DATETIME_IP;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_DEADLINE_DATETIME_TP;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_DEADLINE_NAME_IP;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_DEADLINE_NAME_TP;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static jeryl.fyp.logic.parser.ParserUtil.MESSAGE_INVALID_DATETIME;

import org.junit.jupiter.api.Test;

import jeryl.fyp.logic.commands.AddDeadlineCommand;
import jeryl.fyp.model.student.Deadline;
import jeryl.fyp.model.student.DeadlineName;
import jeryl.fyp.model.student.StudentId;
import jeryl.fyp.model.util.SampleDataUtil;

public class AddDeadlineCommandParserTest {
    private AddDeadlineCommandParser parser = new AddDeadlineCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Deadline expectedDeadline = SampleDataUtil.getDeadline(VALID_DEADLINE_NAME_TP, VALID_DEADLINE_DATETIME_TP);
        StudentId bobStudentId = new StudentId(VALID_STUDENT_ID_BOB);
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + STUDENT_ID_DESC_BOB + DEADLINE_NAME_DESC_TP
                + DEADLINE_DATETIME_DESC_TP, new AddDeadlineCommand(expectedDeadline, bobStudentId));

        // multiple names - last name accepted
        assertParseSuccess(parser, STUDENT_ID_DESC_BOB + DEADLINE_NAME_DESC_IP + DEADLINE_DATETIME_DESC_TP
                + DEADLINE_NAME_DESC_TP, new AddDeadlineCommand(expectedDeadline, bobStudentId));

        // multiple student IDs - last student ID accepted
        assertParseSuccess(parser, STUDENT_ID_DESC_AMY + DEADLINE_NAME_DESC_TP + DEADLINE_DATETIME_DESC_TP
                + STUDENT_ID_DESC_BOB, new AddDeadlineCommand(expectedDeadline, bobStudentId));

        // multiple student DateTimes - last DateTime accepted
        assertParseSuccess(parser, STUDENT_ID_DESC_BOB + DEADLINE_NAME_DESC_TP + DEADLINE_DATETIME_DESC_IP
                + DEADLINE_DATETIME_DESC_TP, new AddDeadlineCommand(expectedDeadline, bobStudentId));
    }
    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDeadlineCommand.MESSAGE_USAGE);

        // missing DeadlineName prefix
        assertParseFailure(parser, STUDENT_ID_DESC_BOB + VALID_DEADLINE_NAME_IP
                + DEADLINE_DATETIME_DESC_IP, expectedMessage);

        // missing student ID prefix
        assertParseFailure(parser, VALID_STUDENT_ID_BOB + DEADLINE_NAME_DESC_TP
                + DEADLINE_DATETIME_DESC_TP, expectedMessage);

        // missing Deadline Datetime prefix
        assertParseFailure(parser, STUDENT_ID_DESC_BOB + DEADLINE_NAME_DESC_TP
                + VALID_DEADLINE_DATETIME_TP, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_STUDENT_ID_BOB + VALID_DEADLINE_NAME_IP
                + VALID_DEADLINE_DATETIME_IP, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid student ID
        assertParseFailure(parser, INVALID_STUDENT_ID_DESC + DEADLINE_NAME_DESC_TP
                + DEADLINE_DATETIME_DESC_TP, StudentId.MESSAGE_CONSTRAINTS);

        // invalid deadline name
        assertParseFailure(parser, STUDENT_ID_DESC_BOB + INVALID_DEADLINE_NAME_DESC
                + DEADLINE_DATETIME_DESC_TP, DeadlineName.MESSAGE_CONSTRAINTS);

        // invalid deadline datetime
        assertParseFailure(parser, STUDENT_ID_DESC_BOB + DEADLINE_NAME_DESC_TP
                + INVALID_DEADLINE_DATETIME_DESC, MESSAGE_INVALID_DATETIME);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_STUDENT_ID_DESC + INVALID_DEADLINE_NAME_DESC
                + DEADLINE_DATETIME_DESC_TP, StudentId.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + STUDENT_ID_DESC_BOB + DEADLINE_NAME_DESC_TP
                        + DEADLINE_DATETIME_DESC_TP,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDeadlineCommand.MESSAGE_USAGE));
    }
}
