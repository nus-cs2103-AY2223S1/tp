package seedu.waddle.model.itinerary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BudgetTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Budget(null));
    }

    @Test
    public void constructor_invalidBudget_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Budget("-0.50"));
        assertThrows(IllegalArgumentException.class, () -> new Budget("1000000.50"));

    }

    @Test
    public void isValidBudgetTest() {
        // null budget
        assertThrows(NullPointerException.class, () -> Budget.isValidBudget(null));

        // invalid budget
        assertFalse(Budget.isValidBudget("")); // empty string
        assertFalse(Budget.isValidBudget(" ")); // spaces only
        assertFalse(Budget.isValidBudget("$50")); // special characters
        assertFalse(Budget.isValidBudget("-1")); // negative budget
        assertFalse(Budget.isValidBudget("1000001")); // budget too high

        // valid budget
        assertTrue(Budget.isValidBudget("0"));
        assertTrue(Budget.isValidBudget("1000000"));
        assertTrue(Budget.isValidBudget("500.50"));
    }
}
