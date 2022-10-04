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
        assertFalse(Weight.isValidWeight("91")); // less than 3 numbers
        assertFalse(Weight.isValidWeight("weight")); // non-numeric
        assertFalse(Weight.isValidWeight("9011p041")); // alphabets within digits
        assertFalse(Weight.isValidWeight("9312 1534")); // spaces within digits

        // valid weight numbers
        assertTrue(Weight.isValidWeight("911")); // exactly 3 numbers
        assertTrue(Weight.isValidWeight("93121534"));
        assertTrue(Weight.isValidWeight("124293842033123")); // long weight numbers
    }
}
