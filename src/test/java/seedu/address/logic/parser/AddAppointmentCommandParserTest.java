package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INCOME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BOTH_FIELD_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_FIELD_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NON_INTEGER_DATE_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_OUT_OF_BOUNDS_MONTH_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_WRONGLENGTH_DAY_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MONTHLY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.RISKTAG_DESC_HIGH;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_NUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.appointment.DateTime.DEFAULT_MONTH_OUT_OF_BOUNDS_ERROR_MESSAGE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Location;
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
        assertParseFailure(parser, "-5" + FIRST_APPOINTMENT_DESC, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, "0" + FIRST_APPOINTMENT_DESC, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string" + FIRST_APPOINTMENT_DESC, expectedFailureMessage);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string" + FIRST_APPOINTMENT_DESC, expectedFailureMessage);
    }
    @Test
    public void parse_invalidAppointmentField_failure() {
        String expectedFailureMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAppointmentCommand.MESSAGE_USAGE);
        int targetPersonIndex = INDEX_SECOND_PERSON.getOneBased();

        // edit appointment with invalid date with wrong length day
        assertParseFailure(parser, targetPersonIndex
                + INVALID_WRONGLENGTH_DAY_APPOINTMENT_DESC, DateTime.MESSAGE_CONSTRAINTS);

        // edit appointment with invalid date with out of bounds month
        assertParseFailure(parser, targetPersonIndex
                + INVALID_OUT_OF_BOUNDS_MONTH_APPOINTMENT_DESC, DEFAULT_MONTH_OUT_OF_BOUNDS_ERROR_MESSAGE + "13");

        // edit appointment with invalid date with non integer values
        assertParseFailure(parser, targetPersonIndex
                + INVALID_NON_INTEGER_DATE_APPOINTMENT_DESC, DateTime.MESSAGE_CONSTRAINTS);


        // add appointment with invalid location
        assertParseFailure(parser, targetPersonIndex
                + INVALID_LOCATION_FIELD_APPOINTMENT_DESC, Location.MESSAGE_CONSTRAINTS);

        // add appointment with invalid location and invalid date
        assertParseFailure(parser, targetPersonIndex
                + INVALID_BOTH_FIELD_APPOINTMENT_DESC, DateTime.MESSAGE_CONSTRAINTS);

        // add appointment with no field
        assertParseFailure(parser, targetPersonIndex + "",
                expectedFailureMessage);
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
