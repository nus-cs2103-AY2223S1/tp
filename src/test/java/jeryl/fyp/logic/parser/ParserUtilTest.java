package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static jeryl.fyp.testutil.Assert.assertThrows;
import static jeryl.fyp.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import jeryl.fyp.logic.parser.exceptions.ParseException;
import jeryl.fyp.model.student.DeadlineName;
import jeryl.fyp.model.student.Email;
import jeryl.fyp.model.student.ProjectName;
import jeryl.fyp.model.student.ProjectStatus;
import jeryl.fyp.model.student.StudentId;
import jeryl.fyp.model.student.StudentName;
import jeryl.fyp.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_STUDENT_NAME = "R@chel";
    private static final String INVALID_STUDENT_ID = "A012T456X";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_PROJECT_NAME = "@CSSE$";
    private static final String INVALID_PROJECT_STATUS = "QSWDOH";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_DEADLINE_NAME = "Do @this!?^+";
    private static final String INVALID_RANK = "-abc";

    private static final String VALID_STUDENT_NAME = "Rachel Walker";
    private static final String VALID_STUDENT_ID = "A0123456X";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_PROJECT_NAME = "CSSE test";
    private static final String VALID_PROJECT_STATUS = "IP";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_DEADLINE_NAME = "Buy coffee";
    private static final String VALID_RANK = "3";

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
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseStudentName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStudentName((String) null));
    }

    @Test
    public void parseStudentName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStudentName(INVALID_STUDENT_NAME));
    }

    @Test
    public void parseStudentName_validValueWithoutWhitespace_returnsName() throws Exception {
        StudentName expectedStudentName = new StudentName(VALID_STUDENT_NAME);
        assertEquals(expectedStudentName, ParserUtil.parseStudentName(VALID_STUDENT_NAME));
    }

    @Test
    public void parseStudentName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_STUDENT_NAME + WHITESPACE;
        StudentName expectedStudentName = new StudentName(VALID_STUDENT_NAME);
        assertEquals(expectedStudentName, ParserUtil.parseStudentName(nameWithWhitespace));
    }

    @Test
    public void parseStudentId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStudentId((String) null));
    }

    @Test
    public void parseStudentId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStudentId(INVALID_STUDENT_ID));
    }

    @Test
    public void parseStudentId_validValueWithoutWhitespace_returnsStudentId() throws Exception {
        StudentId expectedStudentId = new StudentId(VALID_STUDENT_ID);
        assertEquals(expectedStudentId, ParserUtil.parseStudentId(VALID_STUDENT_ID));
    }

    @Test
    public void parseStudentId_validValueWithWhitespace_returnsTrimmedStudentId() throws Exception {
        String studentIdWithWhitespace = WHITESPACE + VALID_STUDENT_ID + WHITESPACE;
        StudentId expectedStudentId = new StudentId(VALID_STUDENT_ID);
        assertEquals(expectedStudentId, ParserUtil.parseStudentId(studentIdWithWhitespace));
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
    public void parseProjectName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseProjectName((String) null));
    }

    @Test
    public void parseProjectName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseProjectName(INVALID_PROJECT_NAME));
    }

    @Test
    public void parseProjectName_validValueWithoutWhitespace_returnsProjectName() throws Exception {
        ProjectName expectedProjectName = new ProjectName(VALID_PROJECT_NAME);
        assertEquals(expectedProjectName, ParserUtil.parseProjectName(VALID_PROJECT_NAME));
    }

    @Test
    public void parseProjectName_validValueWithWhitespace_returnsTrimmedProjectName() throws Exception {
        String projectNameWithWhitespace = WHITESPACE + VALID_PROJECT_NAME + WHITESPACE;
        ProjectName expectedProjectName = new ProjectName(VALID_PROJECT_NAME);
        assertEquals(expectedProjectName, ParserUtil.parseProjectName(projectNameWithWhitespace));
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

    @Test
    public void parseProjectStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseProjectStatus((String) null));
    }

    @Test
    public void parseProjectStatus_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseProjectStatus(INVALID_PROJECT_STATUS));
    }

    @Test
    public void parseProjectStatus_validValueWithoutWhitespace_returnsProjectStatus() throws Exception {
        ProjectStatus expectedProjectStatus = new ProjectStatus(VALID_PROJECT_STATUS);
        assertEquals(expectedProjectStatus, ParserUtil.parseProjectStatus(VALID_PROJECT_STATUS));
    }

    @Test
    public void parseProjectStatus_validValueWithWhitespace_returnsTrimmedProjectStatus() throws Exception {
        String projectStatusWithWhitespace = WHITESPACE + VALID_PROJECT_STATUS + WHITESPACE;
        ProjectStatus expectedProjectStatus = new ProjectStatus(VALID_PROJECT_STATUS);
        assertEquals(expectedProjectStatus, ParserUtil.parseProjectStatus(projectStatusWithWhitespace));
    }

    @Test
    public void parseDeadlineName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeadlineName((String) null));
    }

    @Test
    public void parseDeadlineName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadlineName(INVALID_DEADLINE_NAME));
    }

    @Test
    public void parseDeadlineName_validValueWithoutWhitespace_returnsDeadlineName() throws Exception {
        DeadlineName expectedDeadlineName = new DeadlineName(VALID_DEADLINE_NAME);
        assertEquals(expectedDeadlineName, ParserUtil.parseDeadlineName(VALID_DEADLINE_NAME));
    }

    @Test
    public void parseDeadlineName_validValueWithWhitespace_returnsTrimmedDeadlineName() throws Exception {
        String deadlineNameWithWhitespace = WHITESPACE + VALID_DEADLINE_NAME + WHITESPACE;
        DeadlineName expectedDeadlineName = new DeadlineName(VALID_DEADLINE_NAME);
        assertEquals(expectedDeadlineName, ParserUtil.parseDeadlineName(deadlineNameWithWhitespace));
    }

    @Test
    public void parseDeadlineDatetime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeadlineDatetime((String) null));
    }

    @Test
    public void parseDeadlineDatetime_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadlineDatetime("abc")); // actually invalid
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadlineDatetime("32-01-2021 10:00")); // wrong day
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadlineDatetime("29-13-2021 10:00")); // wrong month
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadlineDatetime("29-01-2021 24:00")); // wrong hour
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadlineDatetime("29-01-2021 27:00")); // wrong hour
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadlineDatetime("29-01-2021 10:60")); // wrong minute
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadlineDatetime("29-01-2021 10:65")); // wrong minute
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadlineDatetime("29-02-2021 10:00")); // invalid date
    }

    @Test
    public void parseDeadlineDatetime_validValueWithoutWhitespace_returnsDeadlineDatetime() throws Exception {
        LocalDateTime expectedDateTime = LocalDateTime.of(2021, 9, 4, 9, 34);
        assertEquals(expectedDateTime, ParserUtil.parseDeadlineDatetime("Sep 04 2021 0934"));
        assertEquals(expectedDateTime, ParserUtil.parseDeadlineDatetime("Sep 04 2021 09:34"));
        assertEquals(expectedDateTime, ParserUtil.parseDeadlineDatetime("04/09/2021 0934"));
        assertEquals(expectedDateTime, ParserUtil.parseDeadlineDatetime("04/09/2021 09:34"));
        assertEquals(expectedDateTime, ParserUtil.parseDeadlineDatetime("04-09-2021 0934"));
        assertEquals(expectedDateTime, ParserUtil.parseDeadlineDatetime("04-09-2021 09:34"));
        assertEquals(expectedDateTime, ParserUtil.parseDeadlineDatetime("2021/09/04 0934"));
        assertEquals(expectedDateTime, ParserUtil.parseDeadlineDatetime("2021/09/04 09:34"));
        assertEquals(expectedDateTime, ParserUtil.parseDeadlineDatetime("2021/09/04T0934"));
        assertEquals(expectedDateTime, ParserUtil.parseDeadlineDatetime("2021/09/04T09:34"));
        assertEquals(expectedDateTime, ParserUtil.parseDeadlineDatetime("2021-09-04 0934"));
        assertEquals(expectedDateTime, ParserUtil.parseDeadlineDatetime("2021-09-04 09:34"));
        assertEquals(expectedDateTime, ParserUtil.parseDeadlineDatetime("04 Sep 2021 0934"));
        assertEquals(expectedDateTime, ParserUtil.parseDeadlineDatetime("04 Sep 2021 09:34"));
        assertEquals(expectedDateTime, ParserUtil.parseDeadlineDatetime("Sep 04, 2021 0934"));
        assertEquals(expectedDateTime, ParserUtil.parseDeadlineDatetime("Sep 04, 2021 09:34"));
    }

    @Test
    public void parseDeadlineDatetime_validValueWithWhitespace_returnsTrimmedDeadlineDatetime() throws Exception {
        LocalDateTime expectedDateTime = LocalDateTime.of(2021, 9, 4, 9, 34);
        assertEquals(expectedDateTime, ParserUtil.parseDeadlineDatetime(WHITESPACE + "Sep 04 2021 0934" + WHITESPACE));
        assertEquals(expectedDateTime, ParserUtil.parseDeadlineDatetime(WHITESPACE + "Sep 04 2021 09:34" + WHITESPACE));
    }

    @Test
    public void parseRank_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRank((String) null));
    }

    @Test
    public void parseRank_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRank(INVALID_RANK));
    }

    @Test
    public void parseRank_validValueWithoutWhitespace_returnsRank() throws Exception {
        Integer expectedRank = Integer.valueOf(VALID_RANK);
        assertEquals(expectedRank, ParserUtil.parseRank(VALID_RANK));
    }

    @Test
    public void parseRank_validValueWithWhitespace_returnsTrimmedRank() throws Exception {
        String rankWithWhitespace = WHITESPACE + VALID_RANK + WHITESPACE;
        Integer expectedRank = Integer.valueOf(VALID_RANK);
        assertEquals(expectedRank, ParserUtil.parseRank(rankWithWhitespace));
    }
}
