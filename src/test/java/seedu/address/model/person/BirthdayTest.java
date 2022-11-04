package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void constructor_invalidBirthday_throwsIllegalArgumentException() {
        String invalidBirthday = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidBirthday));
    }

    @Test
    public void isValidBirthday() {
        // null birthday
        assertThrows(NullPointerException.class, () -> Birthday.isValidBirthday(null));

        // invalid birthdays
        assertFalse(Birthday.isValidBirthday("")); // empty string
        assertFalse(Birthday.isValidBirthday(" ")); // spaces only
        assertFalse(Birthday.isValidBirthday("00/01/2000")); // Boundary case for start of month
        assertFalse(Birthday.isValidBirthday("32/05/2000")); // Boundary case at end of month
        assertFalse(Birthday.isValidBirthday("01/00/2000")); // Boundary case for start of year
        assertFalse(Birthday.isValidBirthday("01/13/2000")); // Boundary case for end of year
        assertFalse(Birthday.isValidBirthday("01/01/19999")); // invalid year only
        assertFalse(Birthday.isValidBirthday("1/01/2000")); // day field not long enough
        assertFalse(Birthday.isValidBirthday("01/6/2000")); // day field not long enough
        assertFalse(Birthday.isValidBirthday("29/02/2001")); // 29th February on a non-leap year
        assertFalse(Birthday.isValidBirthday("31/06/2000")); // day that does not exist


        // valid birthdays
        assertTrue(Birthday.isValidBirthday("15/06/2000")); // Normal date
        assertTrue(Birthday.isValidBirthday("01/01/2000")); // Boundary case for start of month
        assertTrue(Birthday.isValidBirthday("30/06/2000")); // Boundary case for end of month
        assertTrue(Birthday.isValidBirthday("29/02/2000")); // 29th February on a leap year


    }


    @Test
    public void isPastBirthday() {
        // past birthdays
        assertTrue(Birthday.isInPast("29/02/2000"));
        assertTrue(Birthday.isInPast("15/06/2022"));
        assertTrue(Birthday.isInPast("01/01/2021"));

        // future birthdays
        assertFalse(Birthday.isInPast("30/06/2050")); // Boundary case for end of month
    }

    @Test
    public void deepCopy_copyNotSameButEqual() {
        Birthday birthday = new Birthday("01/01/2000");
        Birthday deepCopy = birthday.deepCopy();
        assertNotSame(birthday, deepCopy);
        assertEquals(birthday, deepCopy);
    }
}
