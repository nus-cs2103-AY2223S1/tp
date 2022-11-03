package jarvis.model;

import static jarvis.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LessonDescTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonDesc(null));
    }

    @Test
    public void constructor_invalidDesc_throwsIllegalArgumentException() {
        String invalidDesc = "";
        assertThrows(IllegalArgumentException.class, () -> new LessonDesc(invalidDesc));

        String invalidDesc2 = "    ";
        assertThrows(IllegalArgumentException.class, () -> new LessonDesc(invalidDesc2));
    }

    @Test
    public void constructor_leadingAndTrailingWhitespace_trimmed() {
        String descString = "   Studio 1   ";
        LessonDesc lessonDesc = new LessonDesc(descString);

        assertTrue(lessonDesc.toString().equals(descString.strip()));
    }

    @Test
    public void isValidLessonDesc() {
        // invalid desc
        assertFalse(LessonDesc.isValidLessonDesc(null)); // null
        assertFalse(LessonDesc.isValidLessonDesc("")); // empty string
        assertFalse(LessonDesc.isValidLessonDesc(" ")); // spaces only

        // valid desc
        assertTrue(LessonDesc.isValidLessonDesc("Recursion studio")); // alphabets only
        assertTrue(LessonDesc.isValidLessonDesc("studio 2")); // alphanumeric characters
        assertTrue(LessonDesc.isValidLessonDesc("Studio 2")); // with capital letters
        assertTrue(LessonDesc.isValidLessonDesc("Studio 2: '*' and '^' operators")); // non-alphanumeric characters
    }

    @Test
    public void testEquals() {
        LessonDesc studio1 = new LessonDesc("Studio 1");
        LessonDesc consult2 = new LessonDesc("Consult 2");

        // same description -> returns true
        LessonDesc studio1Copy = new LessonDesc("Studio 1");
        assertTrue(studio1.equals(studio1Copy));

        // same object -> returns true
        assertTrue(studio1.equals(studio1));

        // null -> returns false
        assertFalse(studio1.equals(null));

        // different type -> returns false
        assertFalse(studio1.equals(5));

        //different values -> returns false
        assertFalse(studio1.equals(consult2));
    }
}