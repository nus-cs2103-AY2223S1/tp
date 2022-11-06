package seedu.pennywise.model.entry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_AMT_DINNER;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_AMT_INVESTMENT;
import static seedu.pennywise.testutil.Assert.assertThrows;

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
        Amount amount1 = new seedu.pennywise.model.entry.Amount("1.00");
        Amount amount2 = new seedu.pennywise.model.entry.Amount("2.00");

        assertEquals(new seedu.pennywise.model.entry.Amount("3.00"), Amount.add(amount1, amount2));
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

    @Test
    public void equals() {
        final Amount amount = new Amount(VALID_AMT_DINNER);

        // same values -> returns true
        Amount amountWithSameValues = new Amount(VALID_AMT_DINNER);
        assertEquals(amount, amountWithSameValues);

        // same object -> returns true
        assertEquals(amount, amount);

        // null -> returns false
        assertNotEquals(null, amount);

        // different types -> returns false
        assertNotEquals(amount, new Object());

        // different descriptor -> returns false
        assertNotEquals(amount, new Amount(VALID_AMT_INVESTMENT));
    }
}
