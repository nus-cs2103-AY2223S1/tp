package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MarkCommand;
import seedu.address.model.student.Attendance;



public class MarkCommandParserTest {

    private MarkCommandParser parser = new MarkCommandParser();

    @Test
    public void parse_validArgs_returnUnmarkCommand() {
        String className = "T01";
        assertParseSuccess(parser, "1 absent c/   " + className, new MarkCommand(INDEX_FIRST_PERSON,
                new Attendance(className, true)));
    }

    @Test
    public void parse_invalidOption_throwsParseException() {
        assertParseFailure(parser, "1 asd c/ T01", MarkCommandParser.MESSAGE_INVALID_OPTION);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
    }
}
