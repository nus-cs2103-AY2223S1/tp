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
        assertFalse(IsRoomClean.isValidIsRoomClean("No")); // first letter capitalised
        assertFalse(IsRoomClean.isValidIsRoomClean("yesno")); // not yes or no

        // valid is room clean
        assertTrue(IsRoomClean.isValidIsRoomClean("yes")); // only yes and no are valid
        assertTrue(IsRoomClean.isValidIsRoomClean("no"));
    }
}

