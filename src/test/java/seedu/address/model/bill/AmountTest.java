package seedu.address.model.bill;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AmountTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Amount(null));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        String invalidAmount = "";
        assertThrows(IllegalArgumentException.class, () -> new Amount(invalidAmount));
    }

    @Test
    public void isValidAmount() {
        // null amounts
        assertThrows(NullPointerException.class, () -> Amount.isValidAmount(null));

        // invalid amounts
        assertFalse(Amount.isValidAmount("")); // empty string
        assertFalse(Amount.isValidAmount(" ")); // spaces only
        assertFalse(Amount.isValidAmount("-"));
        assertFalse(Amount.isValidAmount("fadfdasf "));
        assertFalse(Amount.isValidAmount("-1"));
        assertFalse(Amount.isValidAmount("0"));
        assertFalse(Amount.isValidAmount("0.123"));
        assertFalse(Amount.isValidAmount("$100.0"));

        // valid amounts
        assertTrue(Amount.isValidAmount("1.00"));
        assertTrue(Amount.isValidAmount("100.0"));
        assertTrue(Amount.isValidAmount("1"));
        assertTrue(Amount.isValidAmount("1.02"));
    }

    @Test
    public void equals() {
        Amount d1 = new Amount("1.00");
        Amount d2 = new Amount("2.00");
        Amount d3 = new Amount("1.00");
        assertTrue(d1.equals(d1));
        assertTrue(d1.equals(d3));
        assertTrue(d3.equals(d1));
        assertFalse(d2.equals(d1));
        assertFalse(d3.equals(null));
        assertFalse(d3.equals("1.00"));
    }

    @Test
    public void hashCodeTest() {
        Amount d1 = new Amount("1.00");
        Amount d2 = new Amount("2.00");
        Amount d3 = new Amount("1.00");
        assertTrue(d1.hashCode() == d1.hashCode());
        assertFalse(d1.hashCode() == d2.hashCode());
        assertTrue(d1.hashCode() == d3.hashCode());
    }
}
