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
    public void constructor_invalidLectureDetails_throwsIllegalArgumentException() {
        String invalidLectureDetails = "";
        assertThrows(IllegalArgumentException.class, () -> new LectureDetails(invalidLectureDetails));
    }

    @Test
    public void isValidLectureDetails() {
        // null lecture detail
        assertThrows(NullPointerException.class, () -> LectureDetails.areValidLectureDetails(null));

        // invalid lecture details
        assertFalse(LectureDetails.areValidLectureDetails("")); // empty string
        assertFalse(LectureDetails.areValidLectureDetails(" ")); // spaces only

        // valid lecture details
        assertTrue(LectureDetails.areValidLectureDetails("Monday and Wednesday 1800"));
        assertTrue(LectureDetails.areValidLectureDetails("-")); // one character
        assertTrue(LectureDetails.areValidLectureDetails("Thursday 1200-1400"));
        assertTrue(LectureDetails.areValidLectureDetails("No lectures"));
    }
}
