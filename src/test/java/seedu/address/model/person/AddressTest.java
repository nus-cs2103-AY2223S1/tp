package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Scholarship_Name(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Scholarship_Name(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Scholarship_Name.isValidScholarship_Name(null));

        // invalid addresses
        assertFalse(Scholarship_Name.isValidScholarship_Name("")); // empty string
        assertFalse(Scholarship_Name.isValidScholarship_Name(" ")); // spaces only

        // valid addresses
        assertTrue(Scholarship_Name.isValidScholarship_Name("Blk 456, Den Road, #01-355"));
        assertTrue(Scholarship_Name.isValidScholarship_Name("-")); // one character
        assertTrue(Scholarship_Name.isValidScholarship_Name("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
