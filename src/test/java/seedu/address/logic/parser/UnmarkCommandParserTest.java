package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.model.student.Attendance;

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
