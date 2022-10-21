package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.studmap.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.studmap.logic.commands.UnmarkCommand;
import seedu.studmap.model.student.Attendance;

public class UnmarkCommandParserTest {

    private UnmarkCommandParser parser = new UnmarkCommandParser();

    @Test
    public void parse_validArgs_returnUnmarkCommand() {
        String className = "T01";
        assertParseSuccess(parser, "1 c/  " + className, new UnmarkCommand(INDEX_FIRST_STUDENT,
                new Attendance(className, true)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE));
    }

}
