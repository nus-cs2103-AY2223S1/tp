package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BirthdayMonthTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BirthdayMonth(null));
    }

    @Test
    public void constructor_invalidBirthdayMonth_throwsIllegalArgumentException() {
        String invalidBirthdayMonth = "";
        assertThrows(IllegalArgumentException.class, () -> new BirthdayMonth(invalidBirthdayMonth));
    }

    @Test
    public void isValidBirthdayMonth() {
        // null birthdayMonth
        assertThrows(NullPointerException.class, () ->BirthdayMonth.isValidBirthdayMonth(null));

        // invalid birthdayMonth
        assertFalse(BirthdayMonth.isValidBirthdayMonth("")); // empty string
        assertFalse(BirthdayMonth.isValidBirthdayMonth(" ")); // spaces only
        assertFalse(BirthdayMonth.isValidBirthdayMonth("0")); // no month 0
        assertFalse(BirthdayMonth.isValidBirthdayMonth("13")); // no month 13

        // valid birthdayMonth
        assertTrue(BirthdayMonth.isValidBirthdayMonth("1")); // first month
        assertTrue(BirthdayMonth.isValidBirthdayMonth("12")); // last month
        assertTrue(BirthdayMonth.isValidBirthdayMonth("6")); // middle month
    }
}
