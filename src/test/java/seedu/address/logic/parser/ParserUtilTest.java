package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
import seedu.address.model.person.Attendance;
import seedu.address.model.person.GradeProgress;
import seedu.address.model.person.Homework;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Session;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ATTENDANCE = "year 2021";
    private static final String INVALID_SESSION = "Tues 09:30";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_INDEXED_EDIT = "Science workbook";
    private static final String INVALID_ATTENDANCE_DATE = "2022-02-29";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_HOMEWORK = "English compo";
    private static final String VALID_GRADE_PROGRESS = "B+";
    private static final String VALID_ATTENDANCE = "2021-09-10";
    private static final String VALID_SESSION = "Tue 09:30";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_INDEXED_EDIT = "1 Science workbook";

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
    public void parseHomework_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseHomework((String) null));
    }

    @Test
    public void parseHomework_validValueWithoutWhitespace_returnsHomework() throws Exception {
        Homework expectedHomework = new Homework(VALID_HOMEWORK);
        assertEquals(expectedHomework, ParserUtil.parseHomework(VALID_HOMEWORK));
    }

    @Test
    public void parseHomework_validValueWithWhitespace_returnsTrimmedHomework() throws Exception {
        String homeworkWithWhitespace = WHITESPACE + VALID_HOMEWORK + WHITESPACE;
        Homework expectedHomework = new Homework(VALID_HOMEWORK);
        assertEquals(expectedHomework, ParserUtil.parseHomework(homeworkWithWhitespace));
    }

    @Test
    public void parseGradeProgress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGradeProgress((String) null));
    }

    @Test
    public void parseGradeProgress_validValueWithoutWhitespace_returnsGradeProgress() throws Exception {
        GradeProgress expectedGradeProgress = new GradeProgress(VALID_GRADE_PROGRESS);
        assertEquals(expectedGradeProgress, ParserUtil.parseGradeProgress(VALID_GRADE_PROGRESS));
    }

    @Test
    public void parseGradeProgress_validValueWithWhitespace_returnsTrimmedGradeProgress() throws Exception {
        String gradeProgressWithWhitespace = WHITESPACE + VALID_GRADE_PROGRESS + WHITESPACE;
        GradeProgress expectedGradeProgress = new GradeProgress(VALID_GRADE_PROGRESS);
        assertEquals(expectedGradeProgress, ParserUtil.parseGradeProgress(gradeProgressWithWhitespace));
    }

    @Test
    public void parseAttendance_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAttendance((String) null));
    }

    @Test
    public void parseAttendance_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAttendance(INVALID_ATTENDANCE));
    }

    @Test
    public void parseAttendance_validValueWithoutWhitespace_returnsAttendance() throws Exception {
        Attendance expectedAttendance = new Attendance(VALID_ATTENDANCE);
        assertEquals(expectedAttendance, ParserUtil.parseAttendance(VALID_ATTENDANCE));
    }

    @Test
    public void parseAttendance_validValueWithWhitespace_returnsTrimmedAttendance() throws Exception {
        String attendanceWithWhitespace = WHITESPACE + VALID_ATTENDANCE + WHITESPACE;
        Attendance expectedAttendance = new Attendance(VALID_ATTENDANCE);
        assertEquals(expectedAttendance, ParserUtil.parseAttendance(attendanceWithWhitespace));
    }

    @Test
    public void parseSession_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSession((String) null));
    }

    @Test
    public void parseSession_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSession(INVALID_SESSION));
    }

    @Test
    public void parseSession_validValueWithoutWhitespace_returnsSession() throws Exception {
        Session expectedSession = new Session(VALID_SESSION);
        assertEquals(expectedSession, ParserUtil.parseSession(VALID_SESSION));
    }

    @Test
    public void parseSession_validValueWithWhitespace_returnsTrimmedAttendance() throws Exception {
        String sessionWithWhitespace = WHITESPACE + VALID_SESSION + WHITESPACE;
        Session expectedSession = new Session(VALID_SESSION);
        assertEquals(expectedSession, ParserUtil.parseSession(sessionWithWhitespace));
    }

    @Test
    public void parseIndexedEdit_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndexedEdit(INVALID_INDEXED_EDIT));
    }

    @Test
    public void parseIndexedEdit_validValueWithoutWhitespace_returnsArray() throws Exception {
        String[] expectedArray = VALID_INDEXED_EDIT.split(" ", 2);
        assertArrayEquals(expectedArray, ParserUtil.parseIndexedEdit(VALID_INDEXED_EDIT));
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

    @Test
    public void parseAttendance_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAttendance(INVALID_ATTENDANCE));
    }
}
