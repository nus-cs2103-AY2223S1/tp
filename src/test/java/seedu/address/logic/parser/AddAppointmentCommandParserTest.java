package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INCOME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BOTH_FIELD_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_FIELD_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_FIELD_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MONTHLY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.RISKTAG_DESC_HIGH;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_22_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_NUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.AppointmentBuilder;

public class AddAppointmentCommandParserTest {
    private AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresentOneAppointment_success() {
        Index targetPersonIndex = INDEX_SECOND_PERSON;
        String userInput = targetPersonIndex.getOneBased() + FIRST_APPOINTMENT_DESC;

        Appointment appointmentToAdd = new AppointmentBuilder()
                                    .withDateTime(VALID_DATETIME_21_JAN_2023)
                                    .withLocation(VALID_LOCATION_NUS).build();

        AddAppointmentCommand expectedCommand = new AddAppointmentCommand(targetPersonIndex, appointmentToAdd);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        String expectedFailureMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAppointmentCommand.MESSAGE_USAGE);
        // negative index
        assertParseFailure(parser, "-5" + VALID_DATETIME_21_JAN_2023, expectedFailureMessage);

        // zero index
        assertParseFailure(parser, "0" + VALID_DATETIME_22_JAN_2023, expectedFailureMessage);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", expectedFailureMessage);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", expectedFailureMessage);
    }
    @Test
    public void parse_invalidAppointmentField_failure() {
        String expectedFailureMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAppointmentCommand.MESSAGE_USAGE);
        int targetPersonIndex = INDEX_SECOND_PERSON.getOneBased();
        int targetAppointmentIndex = INDEX_FIRST_APPOINTMENT.getOneBased();

        // add appointment with invalid date
        assertParseFailure(parser, targetPersonIndex + "." + targetAppointmentIndex
                + INVALID_DATE_FIELD_APPOINTMENT_DESC, expectedFailureMessage);

        // add appointment with invalid location
        assertParseFailure(parser, targetPersonIndex + "." + targetAppointmentIndex
                + INVALID_LOCATION_FIELD_APPOINTMENT_DESC, expectedFailureMessage);

        // add appointment with invalid location and invalid date
        assertParseFailure(parser, targetPersonIndex + "." + targetAppointmentIndex
                + INVALID_BOTH_FIELD_APPOINTMENT_DESC, expectedFailureMessage);

        // add appointment with no field
        assertParseFailure(parser, targetPersonIndex + "." + targetAppointmentIndex, expectedFailureMessage);
    }

    @Test
    public void parse_validAppointmentWithOtherField_failure() {
        String expectedFailureMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAppointmentCommand.MESSAGE_USAGE);
        int targetIndex = INDEX_SECOND_PERSON.getOneBased();

        // add appointment with name
        assertParseFailure(parser, targetIndex + NAME_DESC_AMY + FIRST_APPOINTMENT_DESC, expectedFailureMessage);

        // add appointment with phone
        assertParseFailure(parser, targetIndex + PHONE_DESC_AMY + FIRST_APPOINTMENT_DESC, expectedFailureMessage);

        // add appointment with email
        assertParseFailure(parser, targetIndex + EMAIL_DESC_AMY + FIRST_APPOINTMENT_DESC, expectedFailureMessage);

        // add appointment with address
        assertParseFailure(parser, targetIndex + ADDRESS_DESC_AMY + FIRST_APPOINTMENT_DESC, expectedFailureMessage);

        // add appointment with normal tag
        assertParseFailure(parser, targetIndex + TAG_DESC_FRIEND + FIRST_APPOINTMENT_DESC, expectedFailureMessage);

        // add appointment with risk tag
        assertParseFailure(parser, targetIndex + RISKTAG_DESC_HIGH + FIRST_APPOINTMENT_DESC, expectedFailureMessage);

        // add appointment with monthly
        assertParseFailure(parser, targetIndex + MONTHLY_DESC_AMY + FIRST_APPOINTMENT_DESC, expectedFailureMessage);

        // add appointment with income
        assertParseFailure(parser, targetIndex + INCOME_DESC_AMY + FIRST_APPOINTMENT_DESC, expectedFailureMessage);
    }
}
