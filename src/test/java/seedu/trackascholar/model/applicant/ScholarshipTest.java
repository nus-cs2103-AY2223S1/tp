package seedu.trackascholar.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackascholar.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ScholarshipTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Scholarship(null));
    }

    @Test
    public void constructor_invalidScholarship_throwsIllegalArgumentException() {
        String invalidScholarship = "";
        assertThrows(IllegalArgumentException.class, () -> new Scholarship(invalidScholarship));
    }

    @Test
    public void isValidScholarship() {
        // null scholarship
        assertThrows(NullPointerException.class, () -> Scholarship.isValidScholarship(null));

        // invalid scholarship
        assertFalse(Scholarship.isValidScholarship("")); // empty string
        assertFalse(Scholarship.isValidScholarship(" ")); // spaces only

        // valid scholarship
        assertTrue(Scholarship.isValidScholarship("NUS Global Merit"));
        assertTrue(Scholarship.isValidScholarship("A")); // one character
        assertTrue(Scholarship.isValidScholarship("MENDAKI - Dr Abdul Aziz Ali Scholarship")); // with a dash
        assertTrue(Scholarship.isValidScholarship(
                        "Hong Kong & Shanghai Banking Corporation Centenary Scholarship")); // long scholarship
    }
}
