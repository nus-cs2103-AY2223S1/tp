package seedu.watson.logic.parser;

import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.watson.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.watson.logic.commands.AttendanceCommand;


public class AttendanceCommandParserTest {

    private final AttendanceCommandParser parser = new AttendanceCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "markAtt",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE));
    }
}
