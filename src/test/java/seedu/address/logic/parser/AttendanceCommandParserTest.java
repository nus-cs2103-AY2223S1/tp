package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AttendanceCommand;
import seedu.address.model.person.Attendance;

public class AttendanceCommandParserTest {

    private static final String INVALID_FORMAT_ATTENDANCE = "1 June 2020";
    private static final String INVALID_DATE_ATTENDANCE = "2022-70-99";

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE);

    private AttendanceCommandParser parser = new AttendanceCommandParser();

    @Test
    public void parse_validArgs_returnsAttendanceCommand() {
        String userInput = "1 " + PREFIX_ATTENDANCE + VALID_ATTENDANCE_AMY;
        AttendanceCommand expectedCommand =
                new AttendanceCommand(INDEX_FIRST_PERSON, new Attendance(VALID_ATTENDANCE_AMY));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_noPrefixField_failure() {
        assertParseFailure(parser, "1 " + VALID_ATTENDANCE_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidFormat_failure() {
        assertParseFailure(parser, "1 " + PREFIX_ATTENDANCE + INVALID_FORMAT_ATTENDANCE,
                Attendance.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDate_failure() {
        assertParseFailure(parser, "1 " + PREFIX_ATTENDANCE + INVALID_DATE_ATTENDANCE,
                Attendance.MESSAGE_INVALID_DATE);
    }
}
