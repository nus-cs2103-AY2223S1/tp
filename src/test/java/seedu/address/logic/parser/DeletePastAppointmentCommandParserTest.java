package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeletePastAppointmentCommand;

public class DeletePastAppointmentCommandParserTest {

    private DeletePastAppointmentCommandParser parser = new DeletePastAppointmentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePastAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        // more than 1 index
        assertParseFailure(parser, "1 5",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePastAppointmentCommand.MESSAGE_USAGE));

        // negative index
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePastAppointmentCommand.MESSAGE_USAGE));

        // string inputs
        assertParseFailure(parser, "asdfghjkl",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePastAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsDeletePastAppointmentCommand() {
        // no leading and trailing whitespaces
        DeletePastAppointmentCommand expectedDeletePastAppointmentCommand = new DeletePastAppointmentCommand(
                INDEX_FIRST_PERSON);
        assertParseSuccess(parser, "1", expectedDeletePastAppointmentCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n 1 \n \t   ", expectedDeletePastAppointmentCommand);
    }
}
