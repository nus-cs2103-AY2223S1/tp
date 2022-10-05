package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Email;
import seedu.address.model.person.FloorNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.PatientType;
import seedu.address.model.person.PatientType.PatientTypes;
import seedu.address.model.person.Phone;
import seedu.address.model.person.WardNumber;
import seedu.address.model.tag.Medication;
import seedu.address.testutil.EditPersonDescriptorBuilder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FLOOR_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_HOSPITAL_WING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEDICATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NEXT_OF_KIN_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PATIENT_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_WARD_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEDICATION_DESC_IBUPROFEN;
import static seedu.address.logic.commands.CommandTestUtil.MEDICATION_DESC_PARACETAMOL;
import static seedu.address.logic.commands.CommandTestUtil.MEDICATION_DESC_XANAX;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NEXT_OF_KIN_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NEXT_OF_KIN_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PATIENT_TYPE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PATIENT_TYPE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICATION_IBUPROFEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICATION_PARACETAMOL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

public class EditCommandParserTest {

    private static final String MEDICATION_EMPTY = " " + PREFIX_MEDICATION;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

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
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_NEXT_OF_KIN_DESC, NextOfKin.MESSAGE_CONSTRAINTS); // invalid nok
        assertParseFailure(parser, "1" + INVALID_PATIENT_TYPE_DESC, PatientType.MESSAGE_CONSTRAINTS); // invalid type
        assertParseFailure(parser, "1" + INVALID_HOSPITAL_WING_DESC, NextOfKin.MESSAGE_CONSTRAINTS); // invalid wing
        assertParseFailure(parser, "1" + INVALID_FLOOR_NUMBER_DESC, FloorNumber.MESSAGE_CONSTRAINTS); // invalid floor
        assertParseFailure(parser, "1" + INVALID_WARD_NUMBER_DESC, WardNumber.MESSAGE_CONSTRAINTS); // invalid ward
        assertParseFailure(parser, "1" + INVALID_MEDICATION_DESC, Medication.MESSAGE_CONSTRAINTS); // invalid med

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_MEDICATION} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + VALID_MEDICATION_IBUPROFEN + VALID_MEDICATION_PARACETAMOL
                + MEDICATION_EMPTY, Medication.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + VALID_MEDICATION_IBUPROFEN + MEDICATION_EMPTY
                + VALID_MEDICATION_PARACETAMOL, Medication.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + MEDICATION_EMPTY + VALID_MEDICATION_IBUPROFEN
                + VALID_MEDICATION_PARACETAMOL, Medication.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC
                + VALID_PHONE_AMY + VALID_NEXT_OF_KIN_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_AMY
                + NEXT_OF_KIN_DESC_AMY + PATIENT_TYPE_DESC_BOB + MEDICATION_DESC_PARACETAMOL;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withNextOfKin(VALID_NEXT_OF_KIN_AMY)
                .withPatientType(PatientTypes.parsePatientType(VALID_PATIENT_TYPE_BOB))
                .withMedication(VALID_MEDICATION_PARACETAMOL).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // next of kin
        userInput = targetIndex.getOneBased() + NEXT_OF_KIN_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withNextOfKin(VALID_NEXT_OF_KIN_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + MEDICATION_DESC_IBUPROFEN;
        descriptor = new EditPersonDescriptorBuilder().withMedication(VALID_MEDICATION_IBUPROFEN).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + NEXT_OF_KIN_DESC_AMY + EMAIL_DESC_AMY
                + MEDICATION_DESC_IBUPROFEN + PHONE_DESC_AMY + NEXT_OF_KIN_DESC_AMY + EMAIL_DESC_AMY
                + MEDICATION_DESC_IBUPROFEN + PHONE_DESC_BOB + NEXT_OF_KIN_DESC_BOB
                + EMAIL_DESC_BOB + MEDICATION_DESC_PARACETAMOL;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withNextOfKin(VALID_NEXT_OF_KIN_BOB)
                .withMedication(VALID_MEDICATION_IBUPROFEN, VALID_MEDICATION_PARACETAMOL).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + NEXT_OF_KIN_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withNextOfKin(VALID_NEXT_OF_KIN_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + MEDICATION_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withMedication().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
