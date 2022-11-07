package seedu.watson.logic.parser;

import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.watson.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.watson.logic.commands.AttendanceCommand;

public class AttendanceCommandParserTest {

    private final AttendanceCommandParser parser = new AttendanceCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "markAtt",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE);

        // missing date
        assertParseFailure(parser, " c/1.2 ind/1 2 3 4", expectedMessage);
    }
}
