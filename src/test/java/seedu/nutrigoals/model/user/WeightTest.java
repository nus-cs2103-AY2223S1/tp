package seedu.nutrigoals.model.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class WeightTest {

    @Test
    public void constructor_nullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Weight(null));
    }

    @Test
    public void constructor_invalidThrowsIllegalArgumentException() {
        String invalidWeight = " ";
        assertThrows(IllegalArgumentException.class, () -> new Weight(invalidWeight));
    }

    @Test
    public void isValidWeight() {
        String invalidWeight = " ";
        String validWeight = "30";

        assertTrue(Weight.isValidWeight(validWeight));
        assertFalse(Weight.isValidWeight(invalidWeight));
        assertFalse(Weight.isValidWeight("2123"));
        assertFalse(Weight.isValidWeight("-11"));
        assertFalse(Weight.isValidWeight("abdc"));

    }

    @Test
    public void equals() {
        Weight weightA = new Weight("30");
        Weight weightB = new Weight("50");

        assertTrue(weightA.equals(weightA));
        assertFalse(weightA.equals("30"));
        assertFalse(weightA.equals(null));
        assertFalse(weightA.equals(weightB));
    }
}
