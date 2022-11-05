package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IncomeLevelTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IncomeLevel(null));
    }

    @Test
    public void constructor_invalidIncomeLevel_throwsIllegalArgumentException() {
        String invalidIncomeLevel = "aslkdh";
        assertThrows(IllegalArgumentException.class, () -> new IncomeLevel(invalidIncomeLevel));
    }

    @Test
    public void isValidIncomeLevel() {
        // null name
        assertThrows(NullPointerException.class, () -> IncomeLevel.isValidIncome(null));

        // invalid name
        assertFalse(IncomeLevel.isValidIncome("")); // empty string
        assertFalse(IncomeLevel.isValidIncome(" ")); // spaces only
        assertFalse(IncomeLevel.isValidIncome("^")); // only non-alphanumeric characters
        assertFalse(IncomeLevel.isValidIncome("peter*")); // contains non-alphanumeric characters
        assertFalse(IncomeLevel.isValidIncome("fdasfd")); // numbers only
        assertFalse(IncomeLevel.isValidIncome("$-1")); // negative number
        assertFalse(IncomeLevel.isValidIncome("$00001")); // trailing zeroes

        // valid name
        assertTrue(IncomeLevel.isValidIncome("231231")); // $ + numbers
        assertTrue(IncomeLevel.isValidIncome("0")); // $ + numbers
        assertTrue(IncomeLevel.isValidIncome("999")); // $ + numbers
    }
}
