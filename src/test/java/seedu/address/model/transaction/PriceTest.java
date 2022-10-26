package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class PriceTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Price(null));
    }

    @Test
    public void constructor_invalidNum_throwsIllegalArgumentException() {
        String invalidPrice = "";
        assertThrows(IllegalArgumentException.class, () -> new Price(invalidPrice));
    }

    @Test
    public void isValidPrice() {
        // null price number
        assertThrows(NullPointerException.class, () -> Price.isValidPrice(null));

        // invalid price numbers
        assertFalse(Price.isValidPrice("")); // empty string
        assertFalse(Price.isValidPrice(" ")); // spaces only
        assertFalse(Price.isValidPrice("test")); // non-numeric
        assertFalse(Price.isValidPrice("9011p041")); // alphabets within digits
        assertFalse(Price.isValidPrice("93 14")); // spaces within digits
        assertFalse(Price.isValidPrice("93..14")); // two decimal points

        // valid price numbers
        assertTrue(Price.isValidPrice("0.1"));
        assertTrue(Price.isValidPrice("1.1827872"));
        assertTrue(Price.isValidPrice("91"));
        assertTrue(Price.isValidPrice("93121534"));
        assertTrue(Price.isValidPrice("124293842033123"));
    }

    @Test
    public void isValidPositivePrice() {
        // null price number
        assertThrows(NullPointerException.class, () -> Price.isPositivePrice(null));

        // invalid price numbers
        assertFalse(Price.isPositivePrice("-1.0"));
        assertFalse(Price.isPositivePrice("-0.8"));
        assertFalse(Price.isPositivePrice("-09.0"));
        assertFalse(Price.isPositivePrice("-1.09"));
        assertFalse(Price.isPositivePrice("-0.0"));
        assertFalse(Price.isPositivePrice("-0"));

        // valid price numbers
        assertTrue(Price.isPositivePrice("0.1"));
        assertTrue(Price.isPositivePrice("1.1827872"));
        assertTrue(Price.isPositivePrice("91"));
        assertTrue(Price.isPositivePrice("93121534"));
        assertTrue(Price.isPositivePrice("124293842033123"));
        assertTrue(Price.isPositivePrice("0"));
        assertTrue(Price.isPositivePrice("0.000"));
    }
    @Test
    public void testToString() {
        String value = "93121534";
        Price price = new Price(value);
        assertEquals(price.toString(), "$93,121,534");
    }

    @Test
    public void value() {
        String value = "93121534";
        Price price = new Price(value);
        assertEquals(price.value(), 93121534);
    }

    @Test
    public void testEquals() {
        String value = "93121534";
        Price price = new Price(value);
        Price test = new Price(value);
        assertEquals(price, test);
    }

}
