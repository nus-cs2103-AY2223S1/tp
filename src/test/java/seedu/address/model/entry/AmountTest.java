package seedu.address.model.entry;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void add_validAmounts_success() {
        Amount amount1 = new seedu.address.model.entry.Amount("1.00");
        Amount amount2 = new seedu.address.model.entry.Amount("2.00");

        assertEquals(new seedu.address.model.entry.Amount("3.00"), Amount.add(amount1, amount2));
    }

    @Test
    public void isValidAmount() {
        // null amount
        assertThrows(NullPointerException.class, () -> Amount.isValidAmount(null));

        // invalid amount
        assertFalse(Amount.isValidAmount(""));
        assertFalse(Amount.isValidAmount("  "));

        // valid amount
        String validAmount = "3.22";
        assertTrue(Amount.isValidAmount(validAmount));

    }

    @Test
    public void toFormattedString() {
        String amount = "10";
        assertEquals(new Amount(amount).toFormattedString(), "$10.00");
    }
}
