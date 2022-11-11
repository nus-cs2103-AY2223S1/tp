package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearAppointmentCommand;

class ClearAppointmentCommandParserTest {

    private ClearAppointmentCommandParser parser = new ClearAppointmentCommandParser();

    @Test
    public void parse_validArgs_returnsClearAppointmentCommand() {
        assertParseSuccess(parser, "1", new ClearAppointmentCommand(FIRST_INDEX));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ClearAppointmentCommand.MESSAGE_USAGE));
    }
}
