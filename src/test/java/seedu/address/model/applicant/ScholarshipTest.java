package seedu.address.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
        assertTrue(Scholarship.isValidScholarship("Blk 456, Den Road, #01-355"));
        assertTrue(Scholarship.isValidScholarship("-")); // one character
        assertTrue(Scholarship
                .isValidScholarship("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long scholarship
    }
}
