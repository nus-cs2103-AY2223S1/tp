package seedu.address.model.commission;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FeeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Fee(null));
    }

    @Test
    public void constructor_invalidFee_throwsInvalidArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Fee(-1.5));
    }

    @Test
    public void isValidFee() {
        assertTrue(Fee.isValidFee(1.5)); // positive values are valid
        assertTrue(Fee.isValidFee(0.0)); // zero is valid
        assertFalse(Fee.isValidFee(-1.5)); // negative values are not valid
        // valid fees are not more than the max fee
        assertTrue(Fee.isValidFee(Fee.MAX_FEE));
        assertFalse(Fee.isValidFee(Fee.MAX_FEE + 1));
    }
}
