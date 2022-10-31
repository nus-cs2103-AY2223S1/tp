package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GradeProgressTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GradeProgress(null));
    }

    @Test
    public void constructor_invalidGradeProgress_throwsIllegalArgumentException() {
        String invalidGradeProgress = "";
        assertThrows(IllegalArgumentException.class, () -> new GradeProgress(invalidGradeProgress));
    }

    @Test
    public void isValidGradeProgress() {
        // null GradeProgress number
        assertThrows(NullPointerException.class, () -> GradeProgress.isValidGradeProgress(null));

        // invalid GradeProgress values
        assertFalse(GradeProgress.isValidGradeProgress("")); // empty string
        assertFalse(GradeProgress.isValidGradeProgress(" ")); // spaces only

        // valid GradeProgress values
        assertTrue(GradeProgress.isValidGradeProgress("A")); // one letter
        assertTrue(GradeProgress.isValidGradeProgress("Math")); // one word
        assertTrue(GradeProgress.isValidGradeProgress("Math:A")); // multiple characters
    }
}
