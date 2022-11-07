package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DOCTOR_DESC_7;
import static seedu.address.logic.commands.CommandTestUtil.DOCTOR_DESC_8;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DOCTOR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEDICAL_TEST_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SLOT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEDICAL_TEST_DESC_7;
import static seedu.address.logic.commands.CommandTestUtil.MEDICAL_TEST_DESC_8;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SLOT_DESC_7;
import static seedu.address.logic.commands.CommandTestUtil.SLOT_DESC_8;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOCTOR_CAITIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_TEST_7;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SLOT_7;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_APPOINTMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.model.appointment.Doctor;
import seedu.address.model.appointment.MedicalTest;
import seedu.address.model.appointment.Slot;
import seedu.address.model.patient.Name;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

public class EditAppointmentCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE);

    private EditAppointmentCommandParser parser = new EditAppointmentCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditAppointmentCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1"
                + INVALID_MEDICAL_TEST_DESC, MedicalTest.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_SLOT_DESC, Slot.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_DOCTOR_DESC, Doctor.MESSAGE_CONSTRAINTS); // invalid address

        // invalid name followed by valid information
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + MEDICAL_TEST_DESC_7 + SLOT_DESC_7 + DOCTOR_DESC_7,
                Name.MESSAGE_CONSTRAINTS);

        // valid name followed by invalid name. The test case for invalid name followed by valid name
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + NAME_DESC_AMY + INVALID_NAME_DESC + MEDICAL_TEST_DESC_7
                + SLOT_DESC_7 + DOCTOR_DESC_7, Name.MESSAGE_CONSTRAINTS);

        // valid medical test followed by invalid medical test.
        // The test case for invalid medical test followed by valid medical test
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + NAME_DESC_AMY + MEDICAL_TEST_DESC_7 + INVALID_MEDICAL_TEST_DESC
                + SLOT_DESC_7 + DOCTOR_DESC_7, MedicalTest.MESSAGE_CONSTRAINTS);

        // valid slot followed by invalid slot. The test case for invalid slot followed by valid slot
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + NAME_DESC_AMY + MEDICAL_TEST_DESC_7 + SLOT_DESC_7
                + INVALID_SLOT_DESC + DOCTOR_DESC_7, Slot.MESSAGE_CONSTRAINTS);


        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_SLOT_DESC + INVALID_DOCTOR_DESC
                + INVALID_MEDICAL_TEST_DESC, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_APPOINTMENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY + DOCTOR_DESC_7 + MEDICAL_TEST_DESC_7
                + SLOT_DESC_7;

        EditAppointmentCommand.EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withName(VALID_NAME_AMY).withDoctor(VALID_DOCTOR_CAITIE)
                .withMedicalTest(VALID_MEDICAL_TEST_7).withSlot(VALID_SLOT_7)
                .build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_APPOINTMENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY + DOCTOR_DESC_7;

        EditAppointmentCommand.EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withName(VALID_NAME_AMY)
                .withDoctor(VALID_DOCTOR_CAITIE).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_APPOINTMENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // doctor
        userInput = targetIndex.getOneBased() + DOCTOR_DESC_7;
        descriptor = new EditAppointmentDescriptorBuilder().withDoctor(VALID_DOCTOR_CAITIE).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // medical test
        userInput = targetIndex.getOneBased() + MEDICAL_TEST_DESC_7;
        descriptor = new EditAppointmentDescriptorBuilder().withMedicalTest(VALID_MEDICAL_TEST_7).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // slot
        userInput = targetIndex.getOneBased() + SLOT_DESC_7;
        descriptor = new EditAppointmentDescriptorBuilder().withSlot(VALID_SLOT_7).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_APPOINTMENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY + NAME_DESC_AMY + NAME_DESC_BOB
                + DOCTOR_DESC_8 + DOCTOR_DESC_7 + MEDICAL_TEST_DESC_8 + MEDICAL_TEST_DESC_7
                + SLOT_DESC_8 + SLOT_DESC_7;

        EditAppointmentCommand.EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withName(VALID_NAME_BOB).withDoctor(VALID_DOCTOR_CAITIE)
                .withMedicalTest(VALID_MEDICAL_TEST_7).withSlot(VALID_SLOT_7).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_APPOINTMENT;
        String userInput = targetIndex.getOneBased() + INVALID_NAME_DESC + NAME_DESC_AMY;
        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + NAME_DESC_AMY + INVALID_DOCTOR_DESC + MEDICAL_TEST_DESC_7
                + DOCTOR_DESC_7;
        descriptor = new EditAppointmentDescriptorBuilder().withName(VALID_NAME_AMY).withDoctor(VALID_DOCTOR_CAITIE)
                .withMedicalTest(VALID_MEDICAL_TEST_7).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
