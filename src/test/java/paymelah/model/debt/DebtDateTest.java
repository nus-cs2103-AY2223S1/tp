package paymelah.model.debt;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static paymelah.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DebtDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DebtDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new DebtDate(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> DebtDate.isValidDate(null));

        // invalid date
        assertFalse(DebtDate.isValidDate("")); // empty string
        assertFalse(DebtDate.isValidDate(" ")); // spaces only
        assertFalse(DebtDate.isValidDate("       ")); // many white spaces
        assertFalse(DebtDate.isValidDate("2021-03-32")); // non-existent day
        assertFalse(DebtDate.isValidDate("2021-4-31")); // invalid day in valid month
        assertFalse(DebtDate.isValidDate("2021-111-01")); // invalid month
        assertFalse(DebtDate.isValidDate("2021/10/12")); // wrong format
        assertFalse(DebtDate.isValidDate("1-10-10")); // <4-digit year
        assertFalse(DebtDate.isValidDate("2022-2-29")); // Feb 29 on non-leap year

        // valid date
        assertTrue(DebtDate.isValidDate("2021-009-12")); // >2-digit month
        assertTrue(DebtDate.isValidDate("2021-09-12")); // 2-digit month
        assertTrue(DebtDate.isValidDate("2021-9-12")); // 1-digit month
        assertTrue(DebtDate.isValidDate("2021-11-0012")); // >2-digit day
        assertTrue(DebtDate.isValidDate("2021-11-08")); // 2-digit day
        assertTrue(DebtDate.isValidDate("2021-9-8")); // 1-digit day
        assertTrue(DebtDate.isValidDate("2020-2-29")); // Feb 29 on leap year
    }
}
