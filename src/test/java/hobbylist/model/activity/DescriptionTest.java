package hobbylist.model.activity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static hobbylist.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import hobbylist.testutil.Assert;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Description(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        Assert.assertThrows(NullPointerException.class, () -> Description.isValidAddress(null));

        // invalid addresses
        assertFalse(Description.isValidAddress("")); // empty string
        assertFalse(Description.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Description.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Description.isValidAddress("-")); // one character
        assertTrue(Description.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
