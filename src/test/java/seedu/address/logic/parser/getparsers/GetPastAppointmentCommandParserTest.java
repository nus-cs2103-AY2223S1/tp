package seedu.address.logic.parser.getparsers;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.getcommands.GetPastAppointmentCommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

public class GetPastAppointmentCommandParserTest {

    private GetPastAppointmentCommandParser parser = new GetPastAppointmentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // empty input
         assertParseFailure(parser, "     ",
                 String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetPastAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        // more than 1 index
        assertParseFailure(parser, "1 5",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetPastAppointmentCommand.MESSAGE_USAGE));

        // negative index
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetPastAppointmentCommand.MESSAGE_USAGE));

        // string inputs
        assertParseFailure(parser, "asdfghjkl",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetPastAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsGetPastAppointmentCommand() {
        // no leading and trailing whitespaces
        GetPastAppointmentCommand expectedGetPastAppointmentCommand = new GetPastAppointmentCommand(
                INDEX_FIRST_PERSON);
        assertParseSuccess(parser, "1", expectedGetPastAppointmentCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n 1 \n \t   ", expectedGetPastAppointmentCommand);
    }
}
