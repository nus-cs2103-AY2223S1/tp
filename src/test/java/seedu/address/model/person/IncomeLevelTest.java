package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IncomeLevelTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Income(null));
    }

    @Test
    public void constructor_invalidIncomeLevel_throwsIllegalArgumentException() {
        String invalidIncomeLevel = "aslkdh";
        assertThrows(IllegalArgumentException.class, () -> new Income(invalidIncomeLevel));
    }

    @Test
    public void isValidIncomeLevel() {
        // null name
        assertThrows(NullPointerException.class, () -> Income.isValidIncome(null));

        // invalid name
        assertFalse(Income.isValidIncome("")); // empty string
        assertFalse(Income.isValidIncome(" ")); // spaces only
        assertFalse(Income.isValidIncome("^")); // only non-alphanumeric characters
        assertFalse(Income.isValidIncome("peter*")); // contains non-alphanumeric characters
        assertFalse(Income.isValidIncome("fdasfd")); // numbers only
        assertFalse(Income.isValidIncome("$-1")); // negative number
        assertFalse(Income.isValidIncome("$00001")); // trailing zeroes

        // valid name
        assertTrue(Income.isValidIncome("231231")); // $ + numbers
        assertTrue(Income.isValidIncome("0")); // $ + numbers
        assertTrue(Income.isValidIncome("999")); // $ + numbers
    }
}
