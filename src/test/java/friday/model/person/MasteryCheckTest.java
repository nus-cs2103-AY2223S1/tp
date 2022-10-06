package friday.model.person;

import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MasteryCheckTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MasteryCheck(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new MasteryCheck(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> MasteryCheck.isValidAddress(null));

        // invalid addresses
        assertFalse(MasteryCheck.isValidAddress("")); // empty string
        assertFalse(MasteryCheck.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(MasteryCheck.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(MasteryCheck.isValidAddress("-")); // one character
        assertTrue(MasteryCheck.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
