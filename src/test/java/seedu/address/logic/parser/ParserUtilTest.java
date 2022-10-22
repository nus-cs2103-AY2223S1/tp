package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.model.person.Cap.CAP_SEPARATOR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFilePaths.PATH_TO_JERRY_JPG;
import static seedu.address.testutil.TypicalFilePaths.PATH_TO_JERRY_WITH_SPACE_JPG;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.job.Id;
import seedu.address.model.job.Title;
import seedu.address.model.person.Address;
import seedu.address.model.person.Cap;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GraduationDate;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.University;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GENDER = "4Male";
    private static final String INVALID_GRADUATION_DATE = "asdf-12";
    private static final String INVALID_TAG = "#friend";
    private static final double INVALID_CAP_VALUE = 6;
    private static final double MAXIMUM_CAP_VALUE = 5.0;
    private static final String INVALID_CAP_VALUE_2 = "a";
    private static final String INVALID_MAXIMUM_CAP_VALUE = "b";
    private static final String INVALID_CAP_1 = INVALID_CAP_VALUE + CAP_SEPARATOR + MAXIMUM_CAP_VALUE;
    private static final String INVALID_CAP_2 = INVALID_CAP_VALUE_2 + CAP_SEPARATOR + INVALID_MAXIMUM_CAP_VALUE;
    private static final String INVALID_CAP_3 = "@#!";
    private static final String INVALID_UNIVERSITY = "n()S";
    private static final String INVALID_MAJOR = "C0MPUT3R $C13NC3";
    private static final String INVALID_ID = "J9021-1";
    private static final String INVALID_TITLE = "Intern | Software Engineer";
    private static final String INVALID_FILE_PATH = "src\0a";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_GENDER = "Male";
    private static final String VALID_GRADUATION_DATE = "05-2024";
    private static final double VALID_CAP_VALUE = 3.3;
    private static final String VALID_CAP = VALID_CAP_VALUE + CAP_SEPARATOR + MAXIMUM_CAP_VALUE;
    private static final String VALID_UNIVERSITY = "ite";
    private static final String VALID_MAJOR = "Computer Science";
    private static final String VALID_ID = "J90211";
    private static final String VALID_TITLE = "Intern - Software Engineer";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_FILE_PATH = "src/folder/jerry.jpg";
    private static final String VALID_FILE_PATH_WITH_SPACE = "src/folder/jerry with space.jpg";

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
    public void parseFilePath_invalidFilePath_throwsParseException() {
        // Empty string
        assertThrows(ParseException.class, () -> ParserUtil.parseFilePath(""));

        // Invalid filename
        assertThrows(ParseException.class, () -> ParserUtil.parseFilePath(INVALID_FILE_PATH));
    }

    @Test
    public void parseFilePath_validFilePath_success() throws Exception {
        // File name without space
        assertEquals(PATH_TO_JERRY_JPG, ParserUtil.parseFilePath(VALID_FILE_PATH));

        // File name with space
        assertEquals(PATH_TO_JERRY_WITH_SPACE_JPG, ParserUtil.parseFilePath(VALID_FILE_PATH_WITH_SPACE));
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
    public void parseGender_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGender((String) null));
    }

    @Test
    public void parseGender_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGender(INVALID_GENDER));
    }

    @Test
    public void parseGender_validValueWithoutWhitespace_returnsGender() throws Exception {
        Gender expectedGender = new Gender(VALID_GENDER);
        assertEquals(expectedGender, ParserUtil.parseGender(VALID_GENDER));
    }

    @Test
    public void parseGender_validValueWithWhitespace_returnsTrimmedGender() throws Exception {
        String genderWithWhitespace = WHITESPACE + VALID_GENDER + WHITESPACE;
        Gender expectedGender = new Gender(VALID_GENDER);
        assertEquals(expectedGender, ParserUtil.parseGender(genderWithWhitespace));
    }

    @Test
    public void parseGraduationDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGraduationDate((String) null));
    }

    @Test
    public void parseGraduationDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGraduationDate(INVALID_GRADUATION_DATE));
    }

    @Test
    public void parseGraduationDate_validValueWithoutWhitespace_returnsGraduationDate() throws Exception {
        GraduationDate expectedGraduationDate = new GraduationDate(VALID_GRADUATION_DATE);
        assertEquals(expectedGraduationDate, ParserUtil.parseGraduationDate(VALID_GRADUATION_DATE));
    }

    @Test
    public void parseGraduationDate_validValueWithWhitespace_returnsTrimmedGraduationDate() throws Exception {
        String graduationDateWithWhitespace = WHITESPACE + VALID_GRADUATION_DATE + WHITESPACE;
        GraduationDate expectedGraduationDate = new GraduationDate(VALID_GRADUATION_DATE);
        assertEquals(expectedGraduationDate, ParserUtil.parseGraduationDate(graduationDateWithWhitespace));
    }

    @Test
    public void parseCap_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCap((String) null));
    }

    @Test
    public void parseCap_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCap(INVALID_CAP_1));
        assertThrows(ParseException.class, () -> ParserUtil.parseCap(INVALID_CAP_2));
        assertThrows(ParseException.class, () -> ParserUtil.parseCap(INVALID_CAP_3));
    }

    @Test
    public void parseCap_validValueWithoutWhitespace_returnsCap() throws Exception {
        Cap expectedCap = new Cap(VALID_CAP_VALUE, MAXIMUM_CAP_VALUE);
        assertEquals(expectedCap, ParserUtil.parseCap(VALID_CAP));
    }

    @Test
    public void parseCap_validValueWithWhitespace_returnsTrimmedCap() throws Exception {
        String capWithWhitespace = WHITESPACE + VALID_CAP + WHITESPACE; // " 3.3/5.0 "
        String anotherCapWithWhitespace = WHITESPACE + VALID_CAP_VALUE // " 3.3 / 5.0 "
                + WHITESPACE + CAP_SEPARATOR + WHITESPACE + MAXIMUM_CAP_VALUE;
        Cap expectedCap = new Cap(VALID_CAP_VALUE, MAXIMUM_CAP_VALUE);
        assertEquals(expectedCap, ParserUtil.parseCap(capWithWhitespace));
        assertEquals(expectedCap, ParserUtil.parseCap(anotherCapWithWhitespace));
    }

    @Test
    public void parseUniversity_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseUniversity((String) null));
    }

    @Test
    public void parseUniversity_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversity(INVALID_UNIVERSITY));
    }

    @Test
    public void parseUniversity_validValueWithoutWhitespace_returnsUniversity() throws Exception {
        University expectedUniversity = new University(VALID_UNIVERSITY);
        assertEquals(expectedUniversity, ParserUtil.parseUniversity(VALID_UNIVERSITY));
    }

    @Test
    public void parseUniversity_validValueWithWhitespace_returnsTrimmedUniversity() throws Exception {
        String universityWithWhitespace = WHITESPACE + VALID_UNIVERSITY + WHITESPACE;
        University expectedUniversity = new University(VALID_UNIVERSITY);
        assertEquals(expectedUniversity, ParserUtil.parseUniversity(universityWithWhitespace));
    }

    @Test
    public void parseMajor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMajor((String) null));
    }

    @Test
    public void parseMajor_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMajor(INVALID_MAJOR));
    }

    @Test
    public void parseMajor_validValueWithoutWhitespace_returnsMajor() throws Exception {
        Major expectedMajor = new Major(VALID_MAJOR);
        assertEquals(expectedMajor, ParserUtil.parseMajor(VALID_MAJOR));
    }

    @Test
    public void parseMajor_validValueWithWhitespace_returnsTrimmedMajor() throws Exception {
        String majorWithWhitespace = WHITESPACE + VALID_MAJOR + WHITESPACE;
        Major expectedMajor = new Major(VALID_MAJOR);
        assertEquals(expectedMajor, ParserUtil.parseMajor(majorWithWhitespace));
    }

    @Test
    public void parseId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseId((String) null));
    }

    @Test
    public void parseId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseId(INVALID_ID));
    }

    @Test
    public void parseId_validValueWithoutWhitespace_returnsId() throws Exception {
        Id expectedId = new Id(VALID_ID);
        assertEquals(expectedId, ParserUtil.parseId(VALID_ID));
    }

    @Test
    public void parseId_validValueWithWhitespace_returnsTrimmedId() throws Exception {
        String idWithWhitespace = WHITESPACE + VALID_ID + WHITESPACE;
        Id expectedId = new Id(VALID_ID);
        assertEquals(expectedId, ParserUtil.parseId(idWithWhitespace));
    }

    @Test
    public void parseTitle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTitle((String) null));
    }

    @Test
    public void parseTitle_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTitle(INVALID_TITLE));
    }

    @Test
    public void parseTitle_validValueWithoutWhitespace_returnsTitle() throws Exception {
        Title expectedTitle = new Title(VALID_TITLE);
        assertEquals(expectedTitle, ParserUtil.parseTitle(VALID_TITLE));
    }

    @Test
    public void parseTitle_validValueWithWhitespace_returnsTrimmedTitle() throws Exception {
        String titleWithWhitespace = WHITESPACE + VALID_TITLE + WHITESPACE;
        Title expectedTitle = new Title(VALID_TITLE);
        assertEquals(expectedTitle, ParserUtil.parseTitle(titleWithWhitespace));
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
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}
