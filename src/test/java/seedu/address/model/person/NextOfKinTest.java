package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NextOfKinTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NextOfKin(null));
    }

    @Test
    public void constructor_invalidNextOfKin_throwsIllegalArgumentException() {
        String invalidNextOfKin = "";
        assertThrows(IllegalArgumentException.class, () -> new NextOfKin(invalidNextOfKin));
    }

    @Test
    public void isValidNextOfKin() {
        // null NextOfKin
        assertThrows(NullPointerException.class, () -> NextOfKin.isValidNextOfKin(null));

        // invalid NextOfKines
        assertFalse(NextOfKin.isValidNextOfKin("")); // empty string
        assertFalse(NextOfKin.isValidNextOfKin(" ")); // spaces only

        // valid NextOfKines
        assertTrue(NextOfKin.isValidNextOfKin("Charles, Husband, 81283745"));
        assertTrue(NextOfKin.isValidNextOfKin("-")); // one character
        assertTrue(NextOfKin.isValidNextOfKin("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long NextOfKin
    }
}
