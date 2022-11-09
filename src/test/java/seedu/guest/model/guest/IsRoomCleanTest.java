package seedu.guest.model.guest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IsRoomCleanTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IsRoomClean(null));
    }

    @Test
    public void constructor_invalidIsRoomClean_throwsIllegalArgumentException() {
        String invalidIsRoomClean = "";
        assertThrows(IllegalArgumentException.class, () -> new IsRoomClean(invalidIsRoomClean));
    }

    @Test
    public void isValidIsRoomClean() {
        // null is room clean
        assertThrows(NullPointerException.class, () -> IsRoomClean.isValidIsRoomClean(null));

        // invalid is room clean
        assertFalse(IsRoomClean.isValidIsRoomClean("")); // empty string
        assertFalse(IsRoomClean.isValidIsRoomClean(" ")); // spaces only
        assertFalse(IsRoomClean.isValidIsRoomClean("maybe")); // not yes or no
        assertFalse(IsRoomClean.isValidIsRoomClean("yess")); // misspelt yes
        assertFalse(IsRoomClean.isValidIsRoomClean("yesno")); // not yes or no
        assertFalse(IsRoomClean.isValidIsRoomClean("true")); // synonym of yes

        // valid is room clean
        assertTrue(IsRoomClean.isValidIsRoomClean("yes")); // yes is valid
        assertTrue(IsRoomClean.isValidIsRoomClean("y")); // y is valid
        assertTrue(IsRoomClean.isValidIsRoomClean("no")); // no is valid
        assertTrue(IsRoomClean.isValidIsRoomClean("n")); // n is valid
        assertTrue(IsRoomClean.isValidIsRoomClean("No")); // first letter capitalised
        assertTrue(IsRoomClean.isValidIsRoomClean("yEs")); // case-insensitive

    }
}

