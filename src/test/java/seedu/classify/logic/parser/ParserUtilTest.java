package seedu.classify.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.classify.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.classify.testutil.Assert.assertThrows;
import static seedu.classify.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.classify.logic.parser.exceptions.ParseException;
import seedu.classify.model.exam.Exam;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.Email;
import seedu.classify.model.student.Name;
import seedu.classify.model.student.Phone;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_CLASS_SPECIAL_CHARACTER = "13@2";
    private static final String INVALID_EMAIL_FORMAT = "example.com";
    private static final String INVALID_EMAIL_CHARACTER = "example@@example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_CLASS = "CLASS1";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "CA1 90";
    private static final String VALID_TAG_2 = "CA2 80";

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
    public void parseClass_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseClass((String) null));
    }

    @Test
    public void parseClass_blank_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseClass(" "));
    }

    @Test
    public void parseClass_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseClass(INVALID_CLASS_SPECIAL_CHARACTER));
    }

    @Test
    public void parseClass_validValueWithoutWhitespace_returnsClassName() throws Exception {
        Class expectedClassName = new Class(VALID_CLASS);
        assertEquals(expectedClassName, ParserUtil.parseClass(VALID_CLASS));
    }

    @Test
    public void parseClass_validValueWithWhitespace_returnsTrimmedClassName() throws Exception {
        String classNameWithWhitespace = WHITESPACE + VALID_CLASS + WHITESPACE;
        Class expectedClassName = new Class(VALID_CLASS);
        assertEquals(expectedClassName, ParserUtil.parseClass(classNameWithWhitespace));
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
    public void parseEmail_blank_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(" "));
    }

    @Test
    public void parseEmail_invalidValueFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL_FORMAT));
    }

    @Test
    public void parseEmail_invalidValueSpecialCharacter_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL_CHARACTER));
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
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExam(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExam(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Exam expectedTag = new Exam(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseExam(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Exam expectedTag = new Exam(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseExam(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExams(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExams(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseExams(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Exam> actualTagSet = ParserUtil.parseExams(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Exam> expectedTagSet = new HashSet<>(Arrays.asList(new Exam(VALID_TAG_1), new Exam(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseFilter_invalidFilter_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFilter("sdfsdf"));
    }

    @Test
    public void parseFilter_validFilterValue_returnsTrue() throws ParseException {
        assertTrue(ParserUtil.parseFilter("ON"));
    }

    @Test
    public void parseFilter_validFilterValue_returnsFalse() throws ParseException {
        assertFalse(ParserUtil.parseFilter("OFF"));
    }
}
