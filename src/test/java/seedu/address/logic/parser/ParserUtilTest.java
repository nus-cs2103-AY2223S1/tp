package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.FloorNumber;
import seedu.address.model.person.HospitalWing;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.PatientType;
import seedu.address.model.person.PatientType.PatientTypes;
import seedu.address.model.person.Phone;
import seedu.address.model.person.WardNumber;
import seedu.address.model.tag.Medication;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_NEXT_OF_KIN = " ";
    private static final String INVALID_PATIENT_TYPE = "b1";
    private static final String INVALID_HOSPITAL_WING = " ";
    private static final String INVALID_FLOOR_NUMBER = "-1";
    private static final String INVALID_WARD_NUMBER = "bravo1";
    private static final String INVALID_MEDICATION = " ";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "12345678";
    private static final String VALID_EMAIL = "rachelw@example.com";
    private static final String VALID_NEXT_OF_KIN = "John Walker, Husband, 81858493";
    private static final String VALID_PATIENT_TYPE = "i";
    private static final String VALID_HOSPITAL_WING = "South";
    private static final String VALID_FLOOR_NUMBER = "1";
    private static final String VALID_WARD_NUMBER = "A123";
    private static final String VALID_MEDICATION_1 = "Ibuprofen";
    private static final String VALID_MEDICATION_2 = "Xanax";

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
    public void parseNextOfKin_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNextOfKin((String) null));
    }

    @Test
    public void parseNextOfKin_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseNextOfKin(INVALID_NEXT_OF_KIN));
    }

    @Test
    public void parseNextOfKin_validValueWithoutWhitespace_returnsAddress() throws Exception {
        NextOfKin expectedNextOfKin = new NextOfKin(VALID_NEXT_OF_KIN);
        assertEquals(expectedNextOfKin, ParserUtil.parseNextOfKin(VALID_NEXT_OF_KIN));
    }

    @Test
    public void parseNextOfKin_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String nextOfKinWithWhitespace = WHITESPACE + VALID_NEXT_OF_KIN + WHITESPACE;
        NextOfKin expectedNextOfKin = new NextOfKin(VALID_NEXT_OF_KIN);
        assertEquals(expectedNextOfKin, ParserUtil.parseNextOfKin(nextOfKinWithWhitespace));
    }

    @Test
    public void parsePatientType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePatientType((String) null));
    }

    @Test
    public void parsePatientType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePatientType(INVALID_PATIENT_TYPE));
    }

    @Test
    public void parsePatientType_validValueWithoutWhitespace_returnsAddress() throws Exception {
        PatientType expectedPatientType = new PatientType(PatientTypes.parsePatientType(VALID_PATIENT_TYPE));
        assertEquals(expectedPatientType, ParserUtil.parsePatientType(VALID_PATIENT_TYPE));
    }

    @Test
    public void parsePatientType_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String patientTypeWithWhitespace = WHITESPACE + VALID_PATIENT_TYPE + WHITESPACE;
        PatientType expectedPatientType = new PatientType(PatientTypes.parsePatientType(VALID_PATIENT_TYPE));
        assertEquals(expectedPatientType, ParserUtil.parsePatientType(patientTypeWithWhitespace));
    }

    @Test
    public void parseHospitalWing_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseHospitalWing((String) null));
    }

    @Test
    public void parseHospitalWing_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseHospitalWing(INVALID_HOSPITAL_WING));
    }

    @Test
    public void parseHospitalWing_validValueWithoutWhitespace_returnsAddress() throws Exception {
        HospitalWing expectedHospitalWing = new HospitalWing(VALID_HOSPITAL_WING);
        assertEquals(expectedHospitalWing, ParserUtil.parseHospitalWing(VALID_HOSPITAL_WING));
    }

    @Test
    public void parseHospitalWing_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String hospitalWingWithWhitespace = WHITESPACE + VALID_HOSPITAL_WING + WHITESPACE;
        HospitalWing expectedHospitalWing = new HospitalWing(VALID_HOSPITAL_WING);
        assertEquals(expectedHospitalWing, ParserUtil.parseHospitalWing(hospitalWingWithWhitespace));
    }

    @Test
    public void parseFloorNumber_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseFloorNumber((String) null));
    }

    @Test
    public void parseFloorNumber_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFloorNumber(INVALID_FLOOR_NUMBER));
    }

    @Test
    public void parseFloorNumber_validValueWithoutWhitespace_returnsAddress() throws Exception {
        FloorNumber expectedFloorNumber = new FloorNumber(Integer.valueOf(VALID_FLOOR_NUMBER));
        assertEquals(expectedFloorNumber, ParserUtil.parseFloorNumber(VALID_FLOOR_NUMBER));
    }

    @Test
    public void parseFloorNumber_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String floorNumberWithWhitespace = WHITESPACE + VALID_FLOOR_NUMBER + WHITESPACE;
        FloorNumber expectedFloorNumber = new FloorNumber(Integer.valueOf(VALID_FLOOR_NUMBER));
        assertEquals(expectedFloorNumber, ParserUtil.parseFloorNumber(floorNumberWithWhitespace));
    }

    @Test
    public void parseWardNumber_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseWardNumber((String) null));
    }

    @Test
    public void parseWardNumber_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseWardNumber(INVALID_WARD_NUMBER));
    }

    @Test
    public void parseWardNumber_validValueWithoutWhitespace_returnsAddress() throws Exception {
        WardNumber expectedWardNumber = new WardNumber(VALID_WARD_NUMBER);
        assertEquals(expectedWardNumber, ParserUtil.parseWardNumber(VALID_WARD_NUMBER));
    }

    @Test
    public void parseWardNumber_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String WardNumberWithWhitespace = WHITESPACE + VALID_WARD_NUMBER + WHITESPACE;
        WardNumber expectedWardNumber = new WardNumber(VALID_WARD_NUMBER);
        assertEquals(expectedWardNumber, ParserUtil.parseWardNumber(WardNumberWithWhitespace));
    }

    @Test
    public void parseMedication_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMedication(null));
    }

    @Test
    public void parseMedications_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMedication(INVALID_MEDICATION));
    }

    @Test
    public void parseMedications_validValueWithoutWhitespace_returnsMedication() throws Exception {
        Medication expectedMedication = new Medication(VALID_MEDICATION_1);
        assertEquals(expectedMedication, ParserUtil.parseMedication(VALID_MEDICATION_1));
    }

    @Test
    public void parseMedications_validValueWithWhitespace_returnsTrimmedMedication() throws Exception {
        String MedicationWithWhitespace = WHITESPACE + VALID_MEDICATION_1 + WHITESPACE;
        Medication expectedMedication = new Medication(VALID_MEDICATION_1);
        assertEquals(expectedMedication, ParserUtil.parseMedication(MedicationWithWhitespace));
    }

    @Test
    public void parseMedications_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMedications(null));
    }

    @Test
    public void parseMedications_collectionWithInvalidMedications_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseMedications(Arrays.asList(VALID_MEDICATION_1, INVALID_MEDICATION)));
    }

    @Test
    public void parseMedications_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseMedications(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseMedications_collectionWithValidMedications_returnsMedicationSet() throws Exception {
        Set<Medication> actualMedicationSet = ParserUtil.parseMedications(
                Arrays.asList(VALID_MEDICATION_1, VALID_MEDICATION_2));
        Set<Medication> expectedMedicationSet = new HashSet<Medication>(
                Arrays.asList(new Medication(VALID_MEDICATION_1), new Medication(VALID_MEDICATION_2)));

        assertEquals(expectedMedicationSet, actualMedicationSet);
    }
}
