package seedu.address.model.date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null DateOfBirth
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // blank DateOfBirth
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only

        // invalid DateOfBirth
        assertFalse(Date.isValidDate("1.1.2000"));
        assertFalse(Date.isValidDate("12/23/2000"));
        assertFalse(Date.isValidDate("12 Mar 1993"));

        // valid DateOfBirth
        assertTrue(Date.isValidDate("1/1/1000"));
        assertTrue(Date.isValidDate("23/12/2000"));
        assertTrue(Date.isValidDate("2/11/1900"));
        assertTrue(Date.isValidDate("11/11/1111"));
    }
}
