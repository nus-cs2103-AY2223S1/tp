package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class QuantityTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Quantity(null));
    }

    @Test
    public void constructor_invalidNum_throwsIllegalArgumentException() {
        String invalidQuantity = "";
        assertThrows(IllegalArgumentException.class, () -> new Quantity(invalidQuantity));
    }

    @Test
    public void isValidQuantity() {
        // null quantity number
        assertThrows(NullPointerException.class, () -> Quantity.isValidQuantity(null));

        // invalid quantity numbers
        assertFalse(Quantity.isValidQuantity("")); // empty string
        assertFalse(Quantity.isValidQuantity(" ")); // spaces only
        assertFalse(Quantity.isValidQuantity("test")); // non-numeric
        assertFalse(Quantity.isValidQuantity("9011p041")); // alphabets within digits
        assertFalse(Quantity.isValidQuantity("93 14")); // spaces within digits

        // valid quantity numbers
        assertTrue(Quantity.isValidQuantity("1"));
        assertTrue(Quantity.isValidQuantity("91"));
        assertTrue(Quantity.isValidQuantity("93121534"));
        assertTrue(Quantity.isValidQuantity("124293842033123"));
    }
    @Test
    public void testToString() {
        String value = "93121534";
        Quantity quantity = new Quantity(value);
        assertEquals(quantity.toString(), value);
    }

    @Test
    public void value() {
        String value = "93121534";
        Quantity quantity = new Quantity(value);
        assertEquals(quantity.value(), 93121534);
    }

    @Test
    public void testEquals() {
        String value = "93121534";
        Quantity quantity = new Quantity(value);
        Quantity test = new Quantity(value);
        assertEquals(quantity, test);
    }
}
