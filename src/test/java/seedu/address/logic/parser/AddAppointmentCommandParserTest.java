package seedu.address.logic.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SECOND_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_22_JAN_2023;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class AddAppointmentCommandParserTest {
    private AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresentOneAppointment_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + FIRST_APPOINTMENT_DESC;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withAppointments(VALID_APPOINTMENT_21_JAN_2023).build();
        AddAppointmentCommand expectedCommand = new AddAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_allFieldsPresentMultipleAppointments_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + FIRST_APPOINTMENT_DESC + SECOND_APPOINTMENT_DESC;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withAppointments(VALID_APPOINTMENT_21_JAN_2023, VALID_APPOINTMENT_22_JAN_2023).build();
        AddAppointmentCommand expectedCommand = new AddAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validAppointmentWithTags_failure() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + FIRST_APPOINTMENT_DESC + TAG_DESC_FRIEND;

        try {
            parser.parse(userInput);
            fail();
        } catch (ParseException parseException) {
            assertEquals("Invalid command format! \naddappt: Schedules an appointment with "
                    + "a specific client by the index number used in the displayed person listParameters: "
                    + "Parameters: INDEX (must be a positive integer) [d/DATE AND TIME]...\n"
                    + "Example: addappt 1 d/21-Jan-2023 12:30 PM " , parseException.getMessage());
        }
    }

    @Test
    public void parse_validAppointmentWithPhone_failure() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + FIRST_APPOINTMENT_DESC;

        try {
            parser.parse(userInput);
            fail();
        } catch (ParseException parseException) {
            assertEquals("Invalid command format! \naddappt: Schedules an appointment with "
                    + "a specific client by the index number used in the displayed person listParameters: "
                    + "Parameters: INDEX (must be a positive integer) [d/DATE AND TIME]...\n"
                    + "Example: addappt 1 d/21-Jan-2023 12:30 PM " , parseException.getMessage());
        }
    }

    @Test
    public void parse_validAppointmentWithName_failure() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY + FIRST_APPOINTMENT_DESC;

        try {
            parser.parse(userInput);
            fail();
        } catch (ParseException parseException) {
            assertEquals("Invalid command format! \naddappt: Schedules an appointment with "
                    + "a specific client by the index number used in the displayed person listParameters: "
                    + "Parameters: INDEX (must be a positive integer) [d/DATE AND TIME]...\n"
                    + "Example: addappt 1 d/21-Jan-2023 12:30 PM " , parseException.getMessage());
        }
    }

    @Test
    public void parse_validAppointmentWithEmail_failure() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY + FIRST_APPOINTMENT_DESC;

        try {
            parser.parse(userInput);
            fail();
        } catch (ParseException parseException) {
            assertEquals("Invalid command format! \naddappt: Schedules an appointment with "
                    + "a specific client by the index number used in the displayed person listParameters: "
                    + "Parameters: INDEX (must be a positive integer) [d/DATE AND TIME]...\n"
                    + "Example: addappt 1 d/21-Jan-2023 12:30 PM " , parseException.getMessage());
        }
    }
}
