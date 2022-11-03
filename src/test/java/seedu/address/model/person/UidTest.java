package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UidTest {
    @Test
    public void constructor_stringNull_throwsNullPointerException() {
        String nullInput = null;
        assertThrows(NullPointerException.class, () -> new Uid(nullInput));
    }

    @Test
    public void constructor_longNull_throwsNullPointerException() {
        Long nullInput = null;
        assertThrows(NullPointerException.class, () -> new Uid(nullInput));
    }

    @Test
    public void constructor_invalidUid_throwsIllegalArgumentException() {
        String invalidUid = "";
        assertThrows(IllegalArgumentException.class, () -> new Uid(invalidUid));
    }

    @Test
    public void isValidUid() {
        // null uid number
        assertThrows(NullPointerException.class, () -> Uid.isValidUid(null));

        // invalid uid numbers
        assertFalse(Uid.isValidUid("")); // empty string
        assertFalse(Uid.isValidUid(" ")); // spaces only
        assertFalse(Uid.isValidUid("100000000")); // more than 99998
        assertFalse(Uid.isValidUid("uid")); // non-numeric
        assertFalse(Uid.isValidUid("9011p041")); // alphabets within digits
        assertFalse(Uid.isValidUid("9312 1534")); // spaces within digits
        assertFalse(Uid.isValidUid("-1")); // negative numbers

        // valid uid numbers
        assertTrue(Uid.isValidUid("911"));
    }
}
