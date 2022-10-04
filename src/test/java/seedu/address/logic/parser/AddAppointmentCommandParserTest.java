package seedu.address.logic.parser;


import static seedu.address.logic.commands.CommandTestUtil.FIRST_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SECOND_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_22_JAN_2023;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAppointmentCommand;
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
}
