package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LectureDetailsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LectureDetails(null));
    }

    @Test
    public void constructor_invalidLectureDetail_throwsIllegalArgumentException() {
        String invalidLectureDetail = "";
        assertThrows(IllegalArgumentException.class, () -> new LectureDetails(invalidLectureDetail));
    }

    @Test
    public void isValidLectureDetailsDescription() {
        // null lecture details
        assertThrows(NullPointerException.class, () -> LectureDetails.areValidLectureDetails(null));

        // invalid lecture details
        assertFalse(LectureDetails.areValidLectureDetails(" ")); // Empty string

        // valid lecture details
        assertTrue(LectureDetails.areValidLectureDetails("Monday 1400")); // Spaces allowed
        assertTrue(LectureDetails.areValidLectureDetails("Tuesday"));
        assertTrue(LectureDetails.areValidLectureDetails("Wednesday Friday 1800")); // Multiple words
        assertTrue(LectureDetails.areValidLectureDetails("Tuesday, Thursday, Friday")); // Non-alphanumerics allowed
    }
}
