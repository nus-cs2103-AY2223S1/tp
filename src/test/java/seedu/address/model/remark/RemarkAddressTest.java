package seedu.address.model.remark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RemarkAddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemarkAddress(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new RemarkAddress(invalidAddress));
    }

    @Test
    public void isValidRemarkAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> RemarkAddress.isValidRemarkAddress(null));

        // invalid address
        assertFalse(RemarkAddress.isValidRemarkAddress("")); // empty string
        assertFalse(RemarkAddress.isValidRemarkAddress(" ")); // empty string

        // valid address
        assertTrue(RemarkAddress.isValidRemarkAddress("bedok mall")); // alphabets only
        assertTrue(RemarkAddress.isValidRemarkAddress("12345")); // numbers only
        assertTrue(RemarkAddress.isValidRemarkAddress("21 toh drive")); // alphanumeric characters
        assertTrue(RemarkAddress.isValidRemarkAddress("Clem Mall")); // with capital letters
        assertTrue(RemarkAddress.isValidRemarkAddress("Block 111 Bedok Reservoir Road #01-1234,"
                + " S470111")); // long names
    }

    @Test
    public void toString_email_returnsValueInName() {
        String value = "Bedok Mall";
        RemarkAddress name = new RemarkAddress(value);
        assertEquals(name.toString(), value);
    }

}
