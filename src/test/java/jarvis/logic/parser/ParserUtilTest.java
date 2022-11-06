package jarvis.logic.parser;

import static jarvis.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import jarvis.commons.core.index.Index;
import jarvis.logic.parser.exceptions.ParseException;
import jarvis.model.LessonDesc;
import jarvis.model.StudentName;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";

    private static final String VALID_NAME = "Rachel Walker";

    private static final String INVALID_LESSON_DESC = " ";

    private static final String VALID_LESSON_DESC = "Consult";

    private static final String INVALID_DATE = "08-08-2022";

    private static final String VALID_DATE = "2022-12-12";

    private static final String INVALID_TIME = "12pm";

    private static final String VALID_TIME = "12:00";

    private static final List<String> INVALID_STUDENT_INDEXES_NEGATIVE = Arrays.asList("-1");

    private static final List<String> INVALID_STUDENT_INDEXES_CHAR = Arrays.asList("a");

    private static final List<String> VALID_STUDENT_INDEXES = Arrays.asList("1", "2", "3");

    private static final List<String> STUDENT_INDEXES_WITH_SPACE = Arrays.asList("  1  ", " 2", "3  ");

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
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        StudentName expectedStudentName = new StudentName(VALID_NAME);
        assertEquals(expectedStudentName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        StudentName expectedStudentName = new StudentName(VALID_NAME);
        assertEquals(expectedStudentName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseLessonDesc_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLessonDesc((String) null));
    }

    @Test
    public void parseLessonDesc_invalidDesc_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLessonDesc(INVALID_LESSON_DESC));
    }

    @Test
    public void parseLessonDesc_validDescWithoutWhiteSpace_returnsLessonDesc() throws Exception {
        LessonDesc expectedLessonDesc = new LessonDesc(VALID_LESSON_DESC);
        assertEquals(expectedLessonDesc, ParserUtil.parseLessonDesc(VALID_LESSON_DESC));
    }

    @Test
    public void parseLessonDesc_validDescWithWhiteSpace_returnsLessonDesc() throws Exception {
        String lessonDescWithWhiteSpace = WHITESPACE + VALID_LESSON_DESC + WHITESPACE;
        LessonDesc expectedLessonDesc = new LessonDesc(lessonDescWithWhiteSpace);
        assertEquals(expectedLessonDesc, ParserUtil.parseLessonDesc(lessonDescWithWhiteSpace));
    }

    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate((String) null));
    }

    @Test
    public void parseDate_invalidDate_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE));
    }

    @Test
    public void parseDate_validDateWithoutWhiteSpace_returnsLocalDate() throws Exception {
        LocalDate expectedLocalDate = LocalDate.parse(VALID_DATE);
        assertEquals(expectedLocalDate, ParserUtil.parseDate(VALID_DATE));
    }

    @Test
    public void parseDate_validDateWithWhiteSpace_returnsLocalDate() throws Exception {
        String dateWithWhiteSpace = WHITESPACE + VALID_DATE + WHITESPACE;
        LocalDate expectedDate = LocalDate.parse(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(dateWithWhiteSpace));
    }

    @Test
    public void parseTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTime((String) null));
    }

    @Test
    public void parseTime_invalidTime_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTime(INVALID_TIME));
    }

    @Test
    public void parseTime_validTimeWithoutWhiteSpace_returnsLocalTime() throws Exception {
        LocalTime expectedLocalTime = LocalTime.parse(VALID_TIME);
        assertEquals(expectedLocalTime, ParserUtil.parseTime(VALID_TIME));
    }

    @Test
    public void parseTime_validTimeWithWhiteSpace_returnsLocalTime() throws Exception {
        String dateWithWhiteSpace = WHITESPACE + VALID_TIME + WHITESPACE;
        LocalTime expectedTime = LocalTime.parse(VALID_TIME);
        assertEquals(expectedTime, ParserUtil.parseTime(dateWithWhiteSpace));
    }

    @Test
    public void parseStudentIndexes_null_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStudentIndexes(null));
    }

    @Test
    public void parseStudentIndexes_invalidStudentIndexes_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStudentIndexes(INVALID_STUDENT_INDEXES_NEGATIVE));

        assertThrows(ParseException.class, () -> ParserUtil.parseStudentIndexes(INVALID_STUDENT_INDEXES_CHAR));
    }

    @Test
    public void parseStudentIndexes_validStudentIndexes_returnsLocalTime() throws Exception {
        Set<Index> expectedStudentIndex = new HashSet<>();
        expectedStudentIndex.add(Index.fromOneBased(1));
        expectedStudentIndex.add(Index.fromOneBased(2));
        expectedStudentIndex.add(Index.fromOneBased(3));
        assertEquals(expectedStudentIndex, ParserUtil.parseStudentIndexes(VALID_STUDENT_INDEXES));
    }

    @Test
    public void parseStudentIndex_validStudentIndexesWithWhiteSpace_returnsLocalTime() throws Exception {
        Set<Index> expectedStudentIndex = new HashSet<>();
        expectedStudentIndex.add(Index.fromOneBased(1));
        expectedStudentIndex.add(Index.fromOneBased(2));
        expectedStudentIndex.add(Index.fromOneBased(3));
        assertEquals(expectedStudentIndex, ParserUtil.parseStudentIndexes(STUDENT_INDEXES_WITH_SPACE));
    }
}
