package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BOTH_FIELD_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_FIELD_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_FIELD_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_22_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_NUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Location;

public class EditAppointmentCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE);
    private EditAppointmentCommandParser parser = new EditAppointmentCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative person index
        assertParseFailure(parser, "-5.5" + VALID_DATETIME_21_JAN_2023, MESSAGE_INVALID_FORMAT);

        // negative appointment index
        assertParseFailure(parser, "5.-5" + VALID_DATETIME_21_JAN_2023, MESSAGE_INVALID_FORMAT);

        // only one index
        assertParseFailure(parser, "1" + VALID_DATETIME_21_JAN_2023, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_DATETIME_22_JAN_2023, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble for person index
        assertParseFailure(parser, "1 some random string.1", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble for appointment index
        assertParseFailure(parser, "1.1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validAppointmentWithOtherField_failure() {
        int targetPersonIndex = INDEX_SECOND_PERSON.getOneBased();
        int targetAppointmentIndex = INDEX_SECOND_APPOINTMENT.getOneBased();
        // edit appointment with tag
        assertParseFailure(parser, targetPersonIndex + "." + targetAppointmentIndex
                + TAG_DESC_FRIEND + FIRST_APPOINTMENT_DESC, MESSAGE_INVALID_FORMAT);

        // edit appointment with phone
        assertParseFailure(parser, targetPersonIndex + "." + targetAppointmentIndex
                + PHONE_DESC_AMY + FIRST_APPOINTMENT_DESC, MESSAGE_INVALID_FORMAT);

        // edit appointment with email
        assertParseFailure(parser, targetPersonIndex + "." + targetAppointmentIndex
                + EMAIL_DESC_AMY + FIRST_APPOINTMENT_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidAppointmentField_failure() {
        int targetIndex = INDEX_SECOND_PERSON.getOneBased();
        int targetAppointmentIndex = INDEX_SECOND_APPOINTMENT.getOneBased();

        // edit appointment with invalid date
        assertParseFailure(parser, targetIndex + "." + targetAppointmentIndex
                + INVALID_DATE_FIELD_APPOINTMENT_DESC, DateTime.MESSAGE_CONSTRAINTS);

        // edit appointment with invalid location
        assertParseFailure(parser, targetIndex + "." + targetAppointmentIndex
                + INVALID_LOCATION_FIELD_APPOINTMENT_DESC, Location.MESSAGE_CONSTRAINTS);

        // edit appointment with invalid date and location
        assertParseFailure(parser, targetIndex + "." + targetAppointmentIndex
                + INVALID_BOTH_FIELD_APPOINTMENT_DESC, DateTime.MESSAGE_CONSTRAINTS);

        // edit appointment with no field
        assertParseFailure(parser, targetIndex + "." + targetAppointmentIndex
                + "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validSingleAppointmentField_success() {
        Index targetPersonIndex = INDEX_SECOND_PERSON;
        Index targetAppointmentIndex = INDEX_FIRST_APPOINTMENT;
        String userInput = targetPersonIndex.getOneBased() + "."
                + targetAppointmentIndex.getOneBased() + FIRST_APPOINTMENT_DESC;

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptor();
        descriptor.setDateTime(ParserUtil.parseDateTime(VALID_DATETIME_21_JAN_2023));
        descriptor.setLocation(new Location(VALID_LOCATION_NUS));

        EditAppointmentCommand expectedCommand =
                new EditAppointmentCommand(targetPersonIndex, targetAppointmentIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
