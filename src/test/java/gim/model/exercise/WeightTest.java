package gim.model.exercise;

import static gim.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WeightTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Weight(null));
    }

    @Test
    public void constructor_invalidWeight_throwsIllegalArgumentException() {
        String invalidWeight = "";
        assertThrows(IllegalArgumentException.class, () -> new Weight(invalidWeight));
    }

    @Test
    public void isValidWeight() {
        // null weight number
        assertThrows(NullPointerException.class, () -> Weight.isValidWeight(null));

        // invalid weight numbers
        assertFalse(Weight.isValidWeight("")); // empty string
        assertFalse(Weight.isValidWeight(" ")); // spaces only
        assertFalse(Weight.isValidWeight("25.")); // dot written but not followed by digit(s)
        assertFalse(Weight.isValidWeight(".25")); // dot written but not preceded by digit(s)
        assertFalse(Weight.isValidWeight("1.250")); // more than 3 decimal place
        assertFalse(Weight.isValidWeight("1.2500")); // more than 3 decimal place
        assertFalse(Weight.isValidWeight("weight")); // non-numeric
        assertFalse(Weight.isValidWeight(":)")); // non-numeric
        assertFalse(Weight.isValidWeight("125x0")); // alphabets within digits
        assertFalse(Weight.isValidWeight("12 500")); // spaces within digits

        // valid weight numbers
        assertTrue(Weight.isValidWeight("6.25")); // 2 decimal place
        assertTrue(Weight.isValidWeight("12.5")); // 1 decimal place
        assertTrue(Weight.isValidWeight("0")); // weight of 0 allowed
        assertTrue(Weight.isValidWeight("0.0")); // weight of 0 allowed
        assertTrue(Weight.isValidWeight("0.00")); // weight of 0 allowed
        assertTrue(Weight.isValidWeight("00.0")); // weight of 0 allowed
        assertTrue(Weight.isValidWeight("1")); // single digit
        assertTrue(Weight.isValidWeight("10")); // double digit
        assertTrue(Weight.isValidWeight("100")); // 3 digits
        assertTrue(Weight.isValidWeight("1000")); // 4 digits
        assertTrue(Weight.isValidWeight("2372384734778931340")); // long weight numbers
    }
}
