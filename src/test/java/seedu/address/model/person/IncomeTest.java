package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IncomeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Income(null));
    }

    @Test
    public void constructor_invalidIncome_throwsIllegalArgumentException() {
        String invalidIncome = "";
        assertThrows(IllegalArgumentException.class, () -> new Income(invalidIncome));
    }

    @Test
    public void isValidIncome() {
        // null income value
        assertThrows(NullPointerException.class, () -> Income.isValidIncome(null));

        // invalid income values
        assertFalse(Income.isValidIncome("")); // empty string
        assertFalse(Income.isValidIncome(" ")); // spaces only
        assertFalse(Income.isValidIncome("900")); // no $ sign
        assertFalse(Income.isValidIncome("$money")); // non-numeric
        assertFalse(Income.isValidIncome("$9p1")); // alphabets within digits
        assertFalse(Income.isValidIncome("$9 01")); // spaces within digits

        // valid income values
        assertTrue(Income.isValidIncome("$5")); // 1 number
        assertTrue(Income.isValidIncome("$901"));
        assertTrue(Income.isValidIncome("$999999"));
        assertTrue(Income.isValidIncome("$999082365912956280")); // long income value
    }
}
