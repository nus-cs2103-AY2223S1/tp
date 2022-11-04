package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MonthlyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Monthly(null));
    }

    @Test
    public void constructor_invalidMonthly_throwsIllegalArgumentException() {
        String invalidMonthly = "aslkdh";
        assertThrows(IllegalArgumentException.class, () -> new Monthly(invalidMonthly));
    }

    @Test
    public void isValidMonthly() {
        // null name
        assertThrows(NullPointerException.class, () -> Monthly.isValidMonthly(null));

        // invalid name
        assertFalse(Monthly.isValidMonthly("")); // empty string
        assertFalse(Monthly.isValidMonthly(" ")); // spaces only
        assertFalse(Monthly.isValidMonthly("^")); // only non-alphanumeric characters
        assertFalse(Monthly.isValidMonthly("peter*")); // contains non-alphanumeric characters
        assertFalse(Monthly.isValidMonthly("fdasfd")); // numbers only
        assertFalse(Monthly.isValidMonthly("$-1")); // negative number
        assertFalse(Monthly.isValidMonthly("$00001")); // trailing zeroes

        // valid name
        assertTrue(Monthly.isValidMonthly("231231")); // $ + numbers
        assertTrue(Monthly.isValidMonthly("0")); // $ + numbers
        assertTrue(Monthly.isValidMonthly("999")); // $ + numbers
    }
}
