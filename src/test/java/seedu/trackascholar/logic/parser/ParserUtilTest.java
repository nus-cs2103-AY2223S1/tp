package seedu.trackascholar.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackascholar.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.trackascholar.testutil.Assert.assertThrows;
import static seedu.trackascholar.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.logic.parser.exceptions.ParseException;
import seedu.trackascholar.model.applicant.Email;
import seedu.trackascholar.model.applicant.Name;
import seedu.trackascholar.model.applicant.Phone;
import seedu.trackascholar.model.applicant.Scholarship;
import seedu.trackascholar.model.major.Major;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+65123456";
    private static final String INVALID_SCHOLARSHIP = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_MAJOR = "#mathematics";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "12345678";
    private static final String VALID_SCHOLARSHIP = "NUS Global Merit";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_MAJOR_1 = "Computer Science";
    private static final String VALID_MAJOR_2 = "Mathematics";

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
        assertEquals(INDEX_FIRST_APPLICANT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_APPLICANT, ParserUtil.parseIndex("  1  "));
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
    public void parseScholarship_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseScholarship((String) null));
    }

    @Test
    public void parseScholarship_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseScholarship(INVALID_SCHOLARSHIP));
    }

    @Test
    public void parseScholarship_validValueWithoutWhitespace_returnsScholarship() throws Exception {
        Scholarship expectedScholarship = new Scholarship(VALID_SCHOLARSHIP);
        assertEquals(expectedScholarship, ParserUtil.parseScholarship(VALID_SCHOLARSHIP));
    }

    @Test
    public void parseScholarship_validValueWithWhitespace_returnsTrimmedScholarship() throws Exception {
        String scholarshipWithWhitespace = WHITESPACE + VALID_SCHOLARSHIP + WHITESPACE;
        Scholarship expectedScholarship = new Scholarship(VALID_SCHOLARSHIP);
        assertEquals(expectedScholarship, ParserUtil.parseScholarship(scholarshipWithWhitespace));
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
    public void parseMajor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMajor(null));
    }

    @Test
    public void parseMajor_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMajor(INVALID_MAJOR));
    }

    @Test
    public void parseMajor_validValueWithoutWhitespace_returnsMajor() throws Exception {
        Major expectedMajor = new Major(VALID_MAJOR_1);
        assertEquals(expectedMajor, ParserUtil.parseMajor(VALID_MAJOR_1));
    }

    @Test
    public void parseMajor_validValueWithWhitespace_returnsTrimmedMajor() throws Exception {
        String majorWithWhitespace = WHITESPACE + VALID_MAJOR_1 + WHITESPACE;
        Major expectedMajor = new Major(VALID_MAJOR_1);
        assertEquals(expectedMajor, ParserUtil.parseMajor(majorWithWhitespace));
    }

    @Test
    public void parseMajors_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMajors(null));
    }

    @Test
    public void parseMajors_collectionWithInvalidMajors_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMajors(Arrays.asList(VALID_MAJOR_1, INVALID_MAJOR)));
    }

    @Test
    public void parseMajors_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseMajors(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseMajors_collectionWithValidMajors_returnsMajorSet() throws Exception {
        Set<Major> actualMajorSet = ParserUtil.parseMajors(Arrays.asList(VALID_MAJOR_1, VALID_MAJOR_2));
        Set<Major> expectedMajorSet = new HashSet<Major>(Arrays.asList(new Major(VALID_MAJOR_1),
            new Major(VALID_MAJOR_2)));

        assertEquals(expectedMajorSet, actualMajorSet);
    }
}
