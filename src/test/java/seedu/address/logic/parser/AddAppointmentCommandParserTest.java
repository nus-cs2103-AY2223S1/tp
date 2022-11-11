package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.SECOND_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAppointmentCommand;

class AddAppointmentCommandParserTest {
    private AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_validArgs_returnsAddAppointmentCommand() {
        assertParseSuccess(parser, "1" + APPOINTMENT_DATE_AMY,
                new AddAppointmentCommand(FIRST_INDEX, VALID_APPOINTMENT_DATE_AMY));
        assertParseSuccess(parser, "2" + APPOINTMENT_DATE_BOB,
                new AddAppointmentCommand(SECOND_INDEX, VALID_APPOINTMENT_DATE_BOB));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAppointmentCommand.MESSAGE_USAGE));

        // incorrect date
        assertParseFailure(parser, "1" + INVALID_APPOINTMENT_DATE, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAppointmentCommand.MESSAGE_USAGE));

        // incorrect index
        assertParseFailure(parser, "b" + APPOINTMENT_DATE_AMY, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAppointmentCommand.MESSAGE_USAGE));
    }
}
