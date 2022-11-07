package seedu.uninurse.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.uninurse.logic.commands.EditMedicationCommand.EditMedicationDescriptor;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.condition.Condition;
import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.medication.Medication;
import seedu.uninurse.model.medication.MedicationList;
import seedu.uninurse.model.person.Address;
import seedu.uninurse.model.person.Email;
import seedu.uninurse.model.person.Name;
import seedu.uninurse.model.person.Phone;
import seedu.uninurse.model.tag.Tag;
import seedu.uninurse.model.tag.TagList;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_CONDITION = " ";
    private static final String INVALID_TAG = " ";
    private static final String INVALID_MEDICATION_1 = " | ";
    private static final String INVALID_MEDICATION_2 = "medication| ";
    private static final String INVALID_MEDICATION_3 = " |dosage";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";

    private static final String VALID_CONDITION_1 = "Wolff-Parkinson-White Syndrome";
    private static final String VALID_CONDITION_2 = "COVID-19";
    private static final String VALID_TAG_1 = "high-risk";
    private static final String VALID_TAG_2 = "A20 Nursing Home";

    private static final String VALID_MEDICATION_1 = "Amoxicillin | 0.5 g every 8 hours";
    private static final String VALID_MEDICATION_2 = "Ampicillin | 0.5 IV every 6 hours";
    private static final String VALID_MEDICATION_DOSAGE_1 = "0.5 g every 8 hours";
    private static final String VALID_MEDICATION_DOSAGE_2 = "0.5 IV every 6 hours";
    private static final String VALID_MEDICATION_TYPE_1 = "Amoxicillin";
    private static final String VALID_MEDICATION_TYPE_2 = "Ampicillin";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseCondition_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCondition(null));
    }

    @Test
    public void parseCondition_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCondition(INVALID_CONDITION));
    }

    @Test
    public void parseCondition_validValueWithoutWhitespace_returnsCondition() throws Exception {
        Condition expectedCondition = new Condition(VALID_CONDITION_1);
        assertEquals(expectedCondition, ParserUtil.parseCondition(VALID_CONDITION_1));
    }

    @Test
    public void parseCondition_validValueWithWhitespace_returnsTrimmedCondition() throws Exception {
        String conditionWithWhitespace = WHITESPACE + VALID_CONDITION_1 + WHITESPACE;
        Condition expectedCondition = new Condition(VALID_CONDITION_1);
        assertEquals(expectedCondition, ParserUtil.parseCondition(conditionWithWhitespace));
    }

    @Test
    public void parseConditions_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseConditions(null));
    }

    @Test
    public void parseConditions_collectionWithInvalidConditions_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseConditions(Arrays.asList(VALID_CONDITION_1, INVALID_CONDITION)));
    }

    @Test
    public void parseConditions_emptyCollection_returnsEmptyList() throws Exception {
        assertTrue(ParserUtil.parseConditions(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseConditions_collectionWithValidConditions_returnsConditionList() throws Exception {
        ConditionList actualConditionList = ParserUtil.parseConditions(
                Arrays.asList(VALID_CONDITION_1, VALID_CONDITION_2));
        ConditionList expectedConditionList = new ConditionList(
                Arrays.asList(new Condition(VALID_CONDITION_1), new Condition(VALID_CONDITION_2)));

        assertEquals(expectedConditionList, actualConditionList);
    }

    @Test
    public void parseMedication_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMedication(null));
    }

    @Test
    public void parseMedication_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMedication(INVALID_MEDICATION_1));
        assertThrows(ParseException.class, () -> ParserUtil.parseMedication(INVALID_MEDICATION_2));
        assertThrows(ParseException.class, () -> ParserUtil.parseMedication(INVALID_MEDICATION_3));
    }

    @Test
    public void parseMedication_validValueWithoutWhitespace_returnsMedication() throws Exception {
        Medication expectedMedication = new Medication(VALID_MEDICATION_TYPE_1, VALID_MEDICATION_DOSAGE_1);
        assertEquals(expectedMedication, ParserUtil.parseMedication(VALID_MEDICATION_1));
    }

    @Test
    public void parseMedication_validValueWithWhitespace_returnsTrimmedMedication() throws Exception {
        String medicationWithWhitespace = WHITESPACE + VALID_MEDICATION_1 + WHITESPACE;
        Medication expectedMedication = new Medication(VALID_MEDICATION_TYPE_1, VALID_MEDICATION_DOSAGE_1);
        assertEquals(expectedMedication, ParserUtil.parseMedication(medicationWithWhitespace));
    }

    @Test
    public void parseMedications_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMedications(null));
    }

    @Test
    public void parseMedications_collectionWithInvalidMedications_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseMedications(Arrays.asList(VALID_MEDICATION_1, INVALID_MEDICATION_1)));
    }

    @Test
    public void parseMedications_emptyCollection_returnsEmptyList() throws Exception {
        assertTrue(ParserUtil.parseMedications(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseMedications_collectionWithValidMedications_returnsMedicationList() throws Exception {
        MedicationList actualMedicationList = ParserUtil.parseMedications(
                Arrays.asList(VALID_MEDICATION_1, VALID_MEDICATION_2));
        MedicationList expectedMedicationList = new MedicationList(
                Arrays.asList(new Medication(VALID_MEDICATION_TYPE_1, VALID_MEDICATION_DOSAGE_1),
                    new Medication(VALID_MEDICATION_TYPE_2, VALID_MEDICATION_DOSAGE_2)));

        assertEquals(expectedMedicationList, actualMedicationList);
    }

    @Test
    public void parseEditMedicationDescriptor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEditMedicationDescriptor(null));
    }

    @Test
    public void parseEditMedicationDescriptor_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEditMedicationDescriptor(INVALID_MEDICATION_1));
    }

    @Test
    public void parseEditMedicationDescriptor_validValueWithoutWhitespace_returnsEditMedicationDescriptor()
            throws Exception {
        EditMedicationDescriptor expectedDescriptor =
                new EditMedicationDescriptor(
                        Optional.of(VALID_MEDICATION_TYPE_1), Optional.of(VALID_MEDICATION_DOSAGE_1));
        assertEquals(expectedDescriptor, ParserUtil.parseEditMedicationDescriptor(VALID_MEDICATION_1));
    }

    @Test
    public void parseEditMedicationDescriptor_validValueWithWhitespace_returnsEditMedicationDescriptor()
            throws Exception {
        String medicationWithWhitespace = WHITESPACE + VALID_MEDICATION_1 + WHITESPACE;
        EditMedicationDescriptor expectedDescriptor =
                new EditMedicationDescriptor(
                        Optional.of(VALID_MEDICATION_TYPE_1), Optional.of(VALID_MEDICATION_DOSAGE_1));
        assertEquals(expectedDescriptor, ParserUtil.parseEditMedicationDescriptor(medicationWithWhitespace));
    }

    @Test
    public void parseEditMedicationDescriptor_validValueWithoutMedicationDosage_returnsEditMedicationDescriptor()
            throws Exception {
        String medicationWithoutDosage = VALID_MEDICATION_TYPE_1 + " | ";
        EditMedicationDescriptor expectedDescriptor =
                new EditMedicationDescriptor(Optional.of(VALID_MEDICATION_TYPE_1), Optional.empty());
        assertEquals(expectedDescriptor, ParserUtil.parseEditMedicationDescriptor(medicationWithoutDosage));
    }

    @Test
    public void parseEditMedicationDescriptor_validValueWithoutSeparator_returnsEditMedicationDescriptor()
            throws Exception {
        String medicationWithoutSeparator = VALID_MEDICATION_TYPE_1;
        EditMedicationDescriptor expectedDescriptor =
                new EditMedicationDescriptor(Optional.of(VALID_MEDICATION_TYPE_1), Optional.empty());
        assertEquals(expectedDescriptor, ParserUtil.parseEditMedicationDescriptor(medicationWithoutSeparator));
    }

    @Test
    public void parseEditMedicationDescriptor_validValueWithoutMedicationType_returnsEditMedicationDescriptor()
            throws Exception {
        String medicationWithoutType = " | " + VALID_MEDICATION_DOSAGE_1;
        EditMedicationDescriptor expectedDescriptor =
                new EditMedicationDescriptor(Optional.empty(), Optional.of(VALID_MEDICATION_DOSAGE_1));
        assertEquals(expectedDescriptor, ParserUtil.parseEditMedicationDescriptor(medicationWithoutType));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptyList() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagList() throws Exception {
        TagList actualTagList = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        TagList expectedTagList = new TagList(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagList, actualTagList);
    }
}
