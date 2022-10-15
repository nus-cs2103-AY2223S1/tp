package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SECOND_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_22_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_JURONGPOINT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_NUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class AddAppointmentCommandParserTest {
    private AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresentOneAppointment_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + FIRST_APPOINTMENT_DESC;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withAppointment(new AppointmentBuilder()
                                .withDateTime(VALID_DATETIME_21_JAN_2023)
                                .withLocation(VALID_LOCATION_NUS).build())
                .build();

        AddAppointmentCommand expectedCommand = new AddAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_allFieldsPresentMultipleAppointments_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + FIRST_APPOINTMENT_DESC + SECOND_APPOINTMENT_DESC;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withAppointment(new AppointmentBuilder()
                                .withDateTime(VALID_DATETIME_21_JAN_2023)
                                .withLocation(VALID_LOCATION_NUS).build())
                .withAppointment(new AppointmentBuilder()
                                .withDateTime(VALID_DATETIME_22_JAN_2023)
                                .withLocation(VALID_LOCATION_JURONGPOINT).build())
                .build();
        AddAppointmentCommand expectedCommand = new AddAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidAppointmentField_failure() {
        String expectedFailureMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAppointmentCommand.MESSAGE_USAGE);
        int targetIndex = INDEX_SECOND_PERSON.getOneBased();

        // add appointment with invalid date
        assertParseFailure(parser, targetIndex + INVALID_APPOINTMENT_DESC, expectedFailureMessage);

        // add appointment with no field
        assertParseFailure(parser, targetIndex + "", expectedFailureMessage);

        // add multiple appointments with invalid field in one of them
        assertParseFailure(parser,
                targetIndex + FIRST_APPOINTMENT_DESC + INVALID_APPOINTMENT_DESC, expectedFailureMessage);
    }

    @Test
    public void parse_validAppointmentWithOtherField_failure() {
        String expectedFailureMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAppointmentCommand.MESSAGE_USAGE);
        int targetIndex = INDEX_SECOND_PERSON.getOneBased();

        // add appointment with tag
        assertParseFailure(parser, targetIndex + TAG_DESC_FRIEND + FIRST_APPOINTMENT_DESC, expectedFailureMessage);

        // add appointment with phone
        assertParseFailure(parser, targetIndex + PHONE_DESC_AMY + FIRST_APPOINTMENT_DESC, expectedFailureMessage);

        // add appointment with email
        assertParseFailure(parser, targetIndex + EMAIL_DESC_AMY + FIRST_APPOINTMENT_DESC, expectedFailureMessage);

    }
}
