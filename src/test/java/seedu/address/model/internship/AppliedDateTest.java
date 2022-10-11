package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AppliedDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppliedDate(null));
    }

    /*
    // Removed for now as there are no constraints on the inputs
    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new AppliedDate(invalidAddress));
    }
    */

    /*
    // Removed for now as there are no constraints on the inputs
    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> AppliedDate.isValidAppliedDate(null));

        // invalid addresses
        assertFalse(AppliedDate.isValidAppliedDate("")); // empty string
        assertFalse(AppliedDate.isValidAppliedDate(" ")); // spaces only

        // valid addresses
        assertTrue(AppliedDate.isValidAppliedDate("Blk 456, Den Road, #01-355"));
        assertTrue(AppliedDate.isValidAppliedDate("-")); // one character
        assertTrue(AppliedDate.isValidAppliedDate("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
    */
}
