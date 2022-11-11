package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.studmap.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.studmap.commons.core.index.SingleIndexGenerator;
import seedu.studmap.logic.commands.MarkCommand;
import seedu.studmap.model.student.Attendance;


public class MarkCommandParserTest {

    private MarkCommandParser parser = new MarkCommandParser();

    @Test
    public void parse_validArgs_returnUnmarkCommand() {
        String className = "T01";
        assertParseSuccess(parser, "1 absent c/   " + className,
                new MarkCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                        new MarkCommand.MarkCommandStudentEditor(
                                new Attendance(className, Attendance.Status.ABSENT))));
    }

    @Test
    public void parse_invalidOption_throwsParseException() {
        assertParseFailure(parser, "1 asd c/ T01", Attendance.MESSAGE_STATUS_CONSTRAINTS);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
    }
}
