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

        // EP: [min_int...9]
        assertThrows(IllegalArgumentException.class, () -> new Weight("9"));
        assertThrows(IllegalArgumentException.class, () -> new Weight("-300"));

        // EP: [200...max_int]
        assertThrows(IllegalArgumentException.class, () -> new Weight("200"));
        assertThrows(IllegalArgumentException.class, () -> new Weight("500"));
    }

    @Test
    public void isValidWeight() {
        String invalidWeight = " ";
        String validWeight = "80";

        // EP: [10...199]
        assertTrue(Weight.isValidWeight(validWeight));
        assertTrue(Weight.isValidWeight("10"));
        assertTrue(Weight.isValidWeight("199"));

        // EP: empty string
        assertFalse(Weight.isValidWeight(invalidWeight));

        // EP: [200...max_int]
        assertFalse(Weight.isValidWeight("2123"));

        // EP: [min_int...9]
        assertFalse(Weight.isValidWeight("-11"));

        // EP: non-integer
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
