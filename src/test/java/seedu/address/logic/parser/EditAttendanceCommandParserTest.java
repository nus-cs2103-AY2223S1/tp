package seedu.address.logic.parser;

        import org.junit.jupiter.api.Test;
        import seedu.address.logic.commands.AttendanceCommand;
        import seedu.address.model.person.position.Student;

        import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
        import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
        import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
        import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

public class EditAttendanceCommandParserTest {

    private AttendanceCommandParser parser = new AttendanceCommandParser();

    @Test
    public void parse_validArgs_returnsAttendanceCommand() {
        assertParseSuccess(parser, "1 attendance/0/1", new AttendanceCommand(INDEX_FIRST_PERSON, "0/1"));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "1 attendance/1/0", String.format(Student.ATTENDANCE_CONSTRAINTS));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE));
    }

}
