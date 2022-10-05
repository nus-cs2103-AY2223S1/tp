package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.HOSPITAL_WING_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FLOOR_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_HOSPITAL_WING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEDICATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NEXT_OF_KIN_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_WARD_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.FLOOR_NUMBER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MEDICATION_DESC_PARACETAMOL;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NEXT_OF_KIN_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NEXT_OF_KIN_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PATIENT_TYPE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PATIENT_TYPE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FLOOR_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOSPITAL_WING_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICATION_IBUPROFEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICATION_PARACETAMOL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICATION_XANAX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WARD_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.WARD_NUMBER_DESC_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.HospitalWing;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Medication;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withMedication("Paracetamol").build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NEXT_OF_KIN_DESC_BOB + PATIENT_TYPE_DESC_BOB
                + MEDICATION_DESC_PARACETAMOL, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NEXT_OF_KIN_DESC_BOB + PATIENT_TYPE_DESC_BOB
                + MEDICATION_DESC_PARACETAMOL, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NEXT_OF_KIN_DESC_BOB + PATIENT_TYPE_DESC_BOB
                + MEDICATION_DESC_PARACETAMOL, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + EMAIL_DESC_BOB
                + NEXT_OF_KIN_DESC_BOB + PATIENT_TYPE_DESC_BOB
                + MEDICATION_DESC_PARACETAMOL, new AddCommand(expectedPerson));

        // multiple next of kin - last next of kin accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NEXT_OF_KIN_DESC_BOB
                + NEXT_OF_KIN_DESC_BOB + PATIENT_TYPE_DESC_BOB
                + MEDICATION_DESC_PARACETAMOL, new AddCommand(expectedPerson));

        // multiple medications - all accepted
        Person expectedPersonMultipleMedications = new PersonBuilder(BOB).withMedication(VALID_MEDICATION_IBUPROFEN,
                VALID_MEDICATION_PARACETAMOL, VALID_MEDICATION_XANAX).build();

        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NEXT_OF_KIN_DESC_BOB+ PATIENT_TYPE_DESC_BOB
                + VALID_MEDICATION_IBUPROFEN + VALID_MEDICATION_PARACETAMOL
                + VALID_MEDICATION_XANAX, new AddCommand(expectedPersonMultipleMedications));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero medications
        Person expectedPerson = new PersonBuilder(AMY).withMedication().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + NEXT_OF_KIN_DESC_AMY
                + PATIENT_TYPE_DESC_AMY + HOSPITAL_WING_DESC_AMY + FLOOR_NUMBER_DESC_AMY
                + WARD_NUMBER_DESC_AMY, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + NEXT_OF_KIN_DESC_AMY + PATIENT_TYPE_DESC_AMY + HOSPITAL_WING_DESC_AMY
                + FLOOR_NUMBER_DESC_AMY + WARD_NUMBER_DESC_AMY , expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_AMY + VALID_PHONE_AMY + EMAIL_DESC_AMY
                + NEXT_OF_KIN_DESC_AMY + PATIENT_TYPE_DESC_AMY + HOSPITAL_WING_DESC_AMY
                + FLOOR_NUMBER_DESC_AMY + WARD_NUMBER_DESC_AMY , expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + VALID_EMAIL_AMY
                + NEXT_OF_KIN_DESC_AMY + PATIENT_TYPE_DESC_AMY + HOSPITAL_WING_DESC_AMY
                + FLOOR_NUMBER_DESC_AMY + WARD_NUMBER_DESC_AMY , expectedMessage);

        // missing next of kin prefix
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + VALID_NEXT_OF_KIN_AMY + PATIENT_TYPE_DESC_AMY + HOSPITAL_WING_DESC_AMY
                + FLOOR_NUMBER_DESC_AMY + WARD_NUMBER_DESC_AMY , expectedMessage);

        // missing patient type prefix
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + NEXT_OF_KIN_DESC_AMY + VALID_PATIENT_TYPE_AMY + HOSPITAL_WING_DESC_AMY
                + FLOOR_NUMBER_DESC_AMY + WARD_NUMBER_DESC_AMY , expectedMessage);

        // missing hospital wing prefix
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + NEXT_OF_KIN_DESC_AMY + PATIENT_TYPE_DESC_AMY + VALID_HOSPITAL_WING_AMY
                + FLOOR_NUMBER_DESC_AMY + WARD_NUMBER_DESC_AMY , expectedMessage);

        // missing floor number prefix
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + NEXT_OF_KIN_DESC_AMY + PATIENT_TYPE_DESC_AMY + VALID_HOSPITAL_WING_AMY
                + VALID_FLOOR_NUMBER_AMY + WARD_NUMBER_DESC_AMY , expectedMessage);

        // missing ward number prefix
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + NEXT_OF_KIN_DESC_AMY + PATIENT_TYPE_DESC_AMY + VALID_HOSPITAL_WING_AMY
                + FLOOR_NUMBER_DESC_AMY + VALID_WARD_NUMBER_AMY , expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_AMY + VALID_PHONE_AMY + VALID_EMAIL_AMY
                + VALID_NEXT_OF_KIN_AMY + VALID_PATIENT_TYPE_AMY + VALID_HOSPITAL_WING_AMY
                + VALID_FLOOR_NUMBER_AMY + VALID_WARD_NUMBER_AMY, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + NEXT_OF_KIN_DESC_AMY + PATIENT_TYPE_DESC_AMY + HOSPITAL_WING_DESC_AMY
                + FLOOR_NUMBER_DESC_AMY + WARD_NUMBER_DESC_AMY
                + VALID_MEDICATION_IBUPROFEN + VALID_MEDICATION_PARACETAMOL, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_AMY + INVALID_PHONE_DESC + EMAIL_DESC_AMY
                + NEXT_OF_KIN_DESC_AMY + PATIENT_TYPE_DESC_AMY + HOSPITAL_WING_DESC_AMY
                + FLOOR_NUMBER_DESC_AMY + WARD_NUMBER_DESC_AMY
                + VALID_MEDICATION_IBUPROFEN + VALID_MEDICATION_PARACETAMOL, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + INVALID_EMAIL_DESC
                + NEXT_OF_KIN_DESC_AMY + PATIENT_TYPE_DESC_AMY + HOSPITAL_WING_DESC_AMY
                + FLOOR_NUMBER_DESC_AMY + WARD_NUMBER_DESC_AMY
                + VALID_MEDICATION_IBUPROFEN + VALID_MEDICATION_PARACETAMOL, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + INVALID_NEXT_OF_KIN_DESC + PATIENT_TYPE_DESC_AMY + HOSPITAL_WING_DESC_AMY
                + FLOOR_NUMBER_DESC_AMY + WARD_NUMBER_DESC_AMY
                + VALID_MEDICATION_IBUPROFEN + VALID_MEDICATION_PARACETAMOL, NextOfKin.MESSAGE_CONSTRAINTS);

        // invalid hospital wing
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + NEXT_OF_KIN_DESC_AMY + PATIENT_TYPE_DESC_AMY + INVALID_HOSPITAL_WING_DESC
                + FLOOR_NUMBER_DESC_AMY + WARD_NUMBER_DESC_AMY
                + VALID_MEDICATION_IBUPROFEN + VALID_MEDICATION_PARACETAMOL, HospitalWing.MESSAGE_CONSTRAINTS);

        // invalid floor number
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + NEXT_OF_KIN_DESC_AMY + PATIENT_TYPE_DESC_AMY + INVALID_HOSPITAL_WING_DESC
                + INVALID_FLOOR_NUMBER_DESC + WARD_NUMBER_DESC_AMY
                + VALID_MEDICATION_IBUPROFEN + VALID_MEDICATION_PARACETAMOL, HospitalWing.MESSAGE_CONSTRAINTS);

        // invalid ward number
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + NEXT_OF_KIN_DESC_AMY + PATIENT_TYPE_DESC_AMY + INVALID_HOSPITAL_WING_DESC
                + FLOOR_NUMBER_DESC_AMY + INVALID_WARD_NUMBER_DESC
                + VALID_MEDICATION_IBUPROFEN + VALID_MEDICATION_PARACETAMOL, HospitalWing.MESSAGE_CONSTRAINTS);

        // invalid medication
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + NEXT_OF_KIN_DESC_AMY + PATIENT_TYPE_DESC_AMY + HOSPITAL_WING_DESC_AMY
                + FLOOR_NUMBER_DESC_AMY + WARD_NUMBER_DESC_AMY
                + INVALID_MEDICATION_DESC, Medication.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + INVALID_NEXT_OF_KIN_DESC + PATIENT_TYPE_DESC_AMY + HOSPITAL_WING_DESC_AMY
                + FLOOR_NUMBER_DESC_AMY + WARD_NUMBER_DESC_AMY
                + VALID_MEDICATION_IBUPROFEN + VALID_MEDICATION_PARACETAMOL, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + NEXT_OF_KIN_DESC_AMY + PATIENT_TYPE_DESC_AMY + HOSPITAL_WING_DESC_AMY
                + FLOOR_NUMBER_DESC_AMY + WARD_NUMBER_DESC_AMY
                + VALID_MEDICATION_IBUPROFEN + VALID_MEDICATION_PARACETAMOL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
