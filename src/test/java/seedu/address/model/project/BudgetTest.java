package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BudgetTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Budget(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidBudget = "";
        assertThrows(IllegalArgumentException.class, () -> new Budget(invalidBudget));
    }

    @Test
    public void isValidBudget() {
        // null budget number
        assertThrows(NullPointerException.class, () -> Budget.isValidBudget(null));

        // invalid budget numbers
        assertFalse(Budget.isValidBudget("")); // empty string
        assertFalse(Budget.isValidBudget(" ")); // spaces only
        assertFalse(Budget.isValidBudget("phone")); // non-numeric
        assertFalse(Budget.isValidBudget("9011p041")); // alphabets within digits
        assertFalse(Budget.isValidBudget("9312 1534")); // spaces within digits

        // valid budget numbers
        assertTrue(Budget.isValidBudget("911"));
        assertTrue(Budget.isValidBudget("93121534"));
        assertTrue(Budget.isValidBudget("124293842033123")); // long budget numbers
    }
}
