package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.LessonBuilder;

public class LessonTest {

    public static final String VALID_MODULE = LessonBuilder.DEFAULT_MODULE;
    public static final String VALID_DAY = LessonBuilder.DEFAULT_DAY;
    public static final String VALID_START_TIME = LessonBuilder.DEFAULT_START_TIME;
    public static final String VALID_END_TIME = LessonBuilder.DEFAULT_END_TIME;

    @Test
    public void constructor_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonBuilder().withModule(null).build());
    }

    @Test
    public void constructor_nullDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonBuilder().withDay(null).build());
    }

    @Test
    public void constructor_nullStartTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonBuilder().withStartTime(null).build());
    }

    @Test
    public void constructor_nullEndTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonBuilder().withEndTime(null).build());
    }

    @Test
    public void isValidLesson() {
        // null module
        assertThrows(NullPointerException.class, () -> Lesson.isValidLesson(null));

        // blank module
        assertFalse(Lesson.isValidLesson(" ")); // spaces only

        // invalid parts
        assertFalse(Lesson.isValidLesson("CS_2040")); // underscore in module
        assertFalse(Lesson.isValidLesson("CS.2040")); // period in module
        assertFalse(Lesson.isValidLesson("CS+2040")); // pluses in module
        assertFalse(Lesson.isValidLesson("CS 2040")); // spaces in module
        assertFalse(Lesson.isValidLesson("CS-2040")); // hyphen in module
        assertFalse(Lesson.isValidLesson("CS@2040")); // '@' symbol in module
        assertFalse(Lesson.isValidLesson("peter--jack")); // consecutive hyphens
        assertFalse(Lesson.isValidLesson("C2040")); // module prefix only 1 letter
        assertFalse(Lesson.isValidLesson("ABCDE2040")); // module prefix more than 4 letters
        assertFalse(Lesson.isValidLesson("2040")); // missing module prefix
        assertFalse(Lesson.isValidLesson("CS204")); // module code less than 4 digits
        assertFalse(Lesson.isValidLesson("CS20405")); // module code more than 4 digits
        assertFalse(Lesson.isValidLesson("CS20A0")); // module code contains letters
        assertFalse(Lesson.isValidLesson("CS2040SG")); // module suffix more than 1 letter

        // valid module
        assertTrue(Lesson.isValidLesson("CS2040")); // 2 letter prefix module name
        assertTrue(Lesson.isValidLesson("UTR1000")); // 3 letter prefix module name
        assertTrue(Lesson.isValidLesson("GESS1025")); // 4 letter prefix module name
        assertTrue(Lesson.isValidLesson("CS2040S")); // 2 letter prefix module name with suffix
        assertTrue(Lesson.isValidLesson("UTR1000A")); // 3 letter prefix module name with suffix
        assertTrue(Lesson.isValidLesson("GESS1025R")); // 4 letter prefix module name with suffix
        assertTrue(Lesson.isValidLesson("cs2040s")); // completely un-capitalised module name
        assertTrue(Lesson.isValidLesson("Cs2040s")); // first letter capitalised module name
        assertTrue(Lesson.isValidLesson("CS2040s")); // prefix capitalised module name
        assertTrue(Lesson.isValidLesson("cs2040S")); // suffix capitalised module name
        assertTrue(Lesson.isValidLesson("dAo1047X")); // random letters capitalised module name
    }

    @Test
    public void equals() {
        final Lesson standardLesson = new LessonBuilder().build();

        // same values -> returns true
        Lesson lessonWithSameModule = new LessonBuilder().withModule(VALID_MODULE)
                .withDay(VALID_DAY).withStartTime(VALID_START_TIME).withEndTime(VALID_END_TIME).build();
        assertTrue(standardLesson.equals(lessonWithSameModule));

        // same object -> returns true
        assertTrue(standardLesson.equals(standardLesson));

        // null -> returns false
        assertFalse(standardLesson.equals(null));

        // different types -> returns false
        assertFalse(standardLesson.equals(new Tag("friends")));
    }

    @Test
    public void toString_returnsCorrectString() {
        final Lesson lesson = new LessonBuilder().build();
        String expected = "CS2103T Tutorial 14:00 to 15:00";
        assertEquals(expected, lesson.toString());
    }

    @Test
    public void toDayString_returnsCorrectDay() {
        final Lesson lessonWithDay1 = new LessonBuilder().withDay("1").build();
        final Lesson lessonWithDay7 = new LessonBuilder().withDay("7").build();
        String day1ToString = "Monday";
        String day7ToString = "Sunday";
        assertEquals(day1ToString, lessonWithDay1.toDayString());
        assertEquals(day7ToString, lessonWithDay7.toDayString());
    }

}
