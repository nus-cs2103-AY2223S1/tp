package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidBirthday = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidBirthday));
    }

    @Test
    public void isValidAddress() {
        // null birthday
        assertThrows(NullPointerException.class, () -> Birthday.isValidDate(null));

        // invalid birthday
        assertFalse(Birthday.isValidDate("")); // empty string
        assertFalse(Birthday.isValidDate(" ")); // spaces only
        assertFalse(Birthday.isValidDate("18-13-2000")); // invalid month
        assertFalse(Birthday.isValidDate("33-01-2000")); // invalid day

        // valid birthday
        assertTrue(Birthday.isValidDate("01-02-2000"));
        assertTrue(Birthday.isValidDate("31-01-2000"));
    }
}
