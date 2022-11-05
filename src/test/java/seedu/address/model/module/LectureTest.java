package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LessonBuilder;

public class LectureTest {

    public static final Lecture VALID_LECTURE = (Lecture) new LessonBuilder().withType("lec").build();

    @Test
    public void typeToString_returnsCorrectType() {
        String validLabType = VALID_LECTURE.typeToString();
        String expected = "Lecture";
        assertEquals(expected, validLabType);
    }

    @Test
    public void toFullString_returnsCorrectFullString() {
        String validLabFullString = VALID_LECTURE.toFullString();
        String expected = "CS2103T Lecture on Thursday 14:00 to 15:00";
        assertEquals(expected, validLabFullString);
    }

}
