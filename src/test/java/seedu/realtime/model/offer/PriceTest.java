package seedu.realtime.model.offer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PriceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Price(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPrice = "";
        assertThrows(IllegalArgumentException.class, () -> new Price(invalidPrice));
    }

    @Test
    public void isValidPrice() {
        // null price
        assertThrows(NullPointerException.class, () -> Price.isValidPrice(null));

        // invalid prices
        assertFalse(Price.isValidPrice("")); // empty string
        assertFalse(Price.isValidPrice(" ")); // spaces only
        assertFalse(Price.isValidPrice("-1")); // negative price
        assertFalse(Price.isValidPrice("hello")); // non-numeric
        assertFalse(Price.isValidPrice("123p123")); // alphabets within digits
        assertFalse(Price.isValidPrice("123 123")); // spaces within digits
        assertFalse(Price.isValidPrice("123.3123")); // symbols within digits

        // valid prices
        assertTrue(Price.isValidPrice("0")); // price starts with 0
        assertTrue(Price.isValidPrice("123456789")); // numeric only
        assertTrue(Price.isValidPrice("010")); // starts with 0
    }
}
