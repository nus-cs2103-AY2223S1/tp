package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ScholarshipName(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new ScholarshipName(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> ScholarshipName.isValidScholarshipName(null));

        // invalid addresses
        assertFalse(ScholarshipName.isValidScholarshipName("")); // empty string
        assertFalse(ScholarshipName.isValidScholarshipName(" ")); // spaces only

        // valid addresses
        assertTrue(ScholarshipName.isValidScholarshipName("Blk 456, Den Road, #01-355"));
        assertTrue(ScholarshipName.isValidScholarshipName("-")); // one character
        assertTrue(ScholarshipName
                .isValidScholarshipName("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
