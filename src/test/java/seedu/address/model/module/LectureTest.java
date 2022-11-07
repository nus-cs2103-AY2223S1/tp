package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LessonBuilder;

public class LectureTest {

    public static final Lecture VALID_LECTURE1 = (Lecture) new LessonBuilder().withType("lec").build();
    public static final Lecture VALID_LECTURE2 = (Lecture) new LessonBuilder().withType("lec")
            .withModule("CS2100").build();
    public static final Tutorial VALID_TUTORIAL = (Tutorial) new LessonBuilder().withType("tut").build();

    @Test
    public void getLectureType_returnsCorrectType() {
        String expected = "lec";
        assertEquals(expected, VALID_LECTURE1.getType());
    }

    @Test
    public void getNonLectureType_returnsFalse() {
        String expected = "lec";
        assertFalse(expected.equals(VALID_TUTORIAL.getType()));
    }

    @Test
    public void toString_returnsCorrectString() {
        String expected = "CS2103T Lecture 14:00 to 15:00";
        assertEquals(expected, VALID_LECTURE1.toString());
    }

    @Test
    public void typeToString_returnsCorrectType() {
        String validLabType = VALID_LECTURE1.typeToString();
        String expected = "Lecture";
        assertEquals(expected, validLabType);
    }

    @Test
    public void toFullString_returnsCorrectFullString() {
        String validLabFullString = VALID_LECTURE1.toFullString();
        String expected = "CS2103T Lecture on Thursday 14:00 to 15:00";
        assertEquals(expected, validLabFullString);
    }

    @Test
    public void equals() {

        // same object -> returns true
        assertTrue(VALID_LECTURE1.equals(VALID_LECTURE1));

        // same values -> returns true
        Lecture duplicateLecture = (Lecture) new LessonBuilder().withType("lec").withModule("CS2103T")
                .withDay("4").withStartTime("14:00").withEndTime("15:00").build();
        assertTrue(VALID_LECTURE1.equals(duplicateLecture));

        // different types -> returns false
        assertFalse(VALID_LECTURE1.equals(VALID_TUTORIAL));

        // null -> returns false
        assertFalse(VALID_LECTURE1.equals(null));

        // different lecture lessons -> returns false
        assertFalse(VALID_LECTURE1.equals(VALID_LECTURE2));
    }

}
