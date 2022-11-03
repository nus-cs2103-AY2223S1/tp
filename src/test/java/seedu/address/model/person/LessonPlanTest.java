package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LessonPlanTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonPlan(null));
    }

    @Test
    public void constructor_invalidLessonPlan_throwsIllegalArgumentException() {
        String invalidLessonPlan = "";
        assertThrows(IllegalArgumentException.class, () -> new LessonPlan(invalidLessonPlan));
    }

    @Test
    public void isValidLessonPlan() {
        // null LessonPlan number
        assertThrows(NullPointerException.class, () -> LessonPlan.isValidLessonPlan(null));

        // invalid LessonPlan values
        assertFalse(LessonPlan.isValidLessonPlan("")); // empty string
        assertFalse(LessonPlan.isValidLessonPlan(" ")); // spaces only

        // valid LessonPlan values
        assertTrue(LessonPlan.isValidLessonPlan("B")); // one letter
        assertTrue(LessonPlan.isValidLessonPlan("Biology")); // one word
        assertTrue(LessonPlan.isValidLessonPlan("Secondary 4 Biology")); // multiple words
    }
}
