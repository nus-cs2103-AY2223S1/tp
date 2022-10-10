package seedu.address.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Scholarship(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Scholarship(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Scholarship.isValidScholarship(null));

        // invalid addresses
        assertFalse(Scholarship.isValidScholarship("")); // empty string
        assertFalse(Scholarship.isValidScholarship(" ")); // spaces only

        // valid addresses
        assertTrue(Scholarship.isValidScholarship("Blk 456, Den Road, #01-355"));
        assertTrue(Scholarship.isValidScholarship("-")); // one character
        assertTrue(Scholarship
                .isValidScholarship("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
