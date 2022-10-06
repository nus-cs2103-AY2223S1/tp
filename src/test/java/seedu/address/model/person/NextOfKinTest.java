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
        assertFalse(NextOfKin.isValidNextOfKin("-")); // one character

        // valid NextOfKines
        assertTrue(NextOfKin.isValidNextOfKin("a, a, 81283745")); // short NextOfKin
        assertTrue(NextOfKin.isValidNextOfKin("Charles, Husband, 81283745"));
        assertTrue(NextOfKin
                .isValidNextOfKin("King Kong the Fifth, King of Swazililand, 23234923492304")); // long NextOfKin
    }
}
