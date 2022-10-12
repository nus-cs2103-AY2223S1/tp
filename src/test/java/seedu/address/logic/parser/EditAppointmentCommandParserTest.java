package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SECOND_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_22_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.testutil.EditPersonDescriptorBuilder;

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
        // negative index
        assertParseFailure(parser, "-5" + VALID_APPOINTMENT_21_JAN_2023, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_APPOINTMENT_22_JAN_2023, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validAppointmentWithOtherField_failure() {
        int targetIndex = INDEX_SECOND_PERSON.getOneBased();
        // edit appointment with tag
        assertParseFailure(parser, targetIndex + TAG_DESC_FRIEND + FIRST_APPOINTMENT_DESC, MESSAGE_INVALID_FORMAT);

        // edit appointment with phone
        assertParseFailure(parser, targetIndex + PHONE_DESC_AMY + FIRST_APPOINTMENT_DESC, MESSAGE_INVALID_FORMAT);

        // edit appointment with email
        assertParseFailure(parser, targetIndex + EMAIL_DESC_AMY + FIRST_APPOINTMENT_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidAppointmentField_failure() {
        int targetIndex = INDEX_SECOND_PERSON.getOneBased();

        // edit appointment with invalid date
        assertParseFailure(parser, targetIndex + INVALID_APPOINTMENT_DESC, MESSAGE_INVALID_FORMAT);

        // edit appointment with no field
        assertParseFailure(parser, targetIndex + "", MESSAGE_INVALID_FORMAT);

        // edit multiple appointments with invalid field in one of them
        assertParseFailure(parser, targetIndex + FIRST_APPOINTMENT_DESC + INVALID_APPOINTMENT_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validSingleAppointmentField_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + FIRST_APPOINTMENT_DESC;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withAppointments(VALID_APPOINTMENT_21_JAN_2023).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validMultipleAppointmentField_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + FIRST_APPOINTMENT_DESC + SECOND_APPOINTMENT_DESC;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withAppointments(VALID_APPOINTMENT_21_JAN_2023, VALID_APPOINTMENT_22_JAN_2023).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
