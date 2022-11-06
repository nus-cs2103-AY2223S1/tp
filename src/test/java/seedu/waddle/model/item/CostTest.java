package seedu.waddle.model.item;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.testutil.Assert.assertThrows;

public class CostTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Cost(null));
    }

    @Test
    public void constructor_invalidCost_throwsIllegalArgumentException() {
        String invalidCost = "";
        assertThrows(IllegalArgumentException.class, () -> new Cost(invalidCost));
    }

    @Test
    public void isValidCost() {
        // null cost
        assertThrows(NullPointerException.class, () -> Cost.isValidCost(null));

        // invalid cost
        assertFalse(Cost.isValidCost("")); // empty string
        assertFalse(Cost.isValidCost(" ")); // spaces only
        assertFalse(Cost.isValidCost("one"));
        assertFalse(Cost.isValidCost("1,000,000"));
        assertFalse(Cost.isValidCost("2000000")); // greater than 1,000,000

        // valid cost
        assertTrue(Cost.isValidCost("2"));
        assertTrue(Cost.isValidCost("999999"));
        assertTrue(Cost.isValidCost("0"));
    }
}
