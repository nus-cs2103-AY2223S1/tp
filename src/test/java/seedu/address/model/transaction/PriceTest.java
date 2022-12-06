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
    public void isValidDouble() {
        // null price number
        assertThrows(NullPointerException.class, () -> Price.isValidPrice(null));

        // invalid price numbers
        assertFalse(Price.isValidPrice("")); // empty string
        assertFalse(Price.isValidPrice(" ")); // spaces only
        assertFalse(Price.isValidPrice("test")); // non-numeric
        assertFalse(Price.isValidPrice("9011p041")); // alphabets within digits
        assertFalse(Price.isValidPrice("93 14")); // spaces within digits
        assertFalse(Price.isValidPrice("93..14")); // two decimal points
        assertFalse(Price.isValidPrice("+45")); // has "+"


        // valid price numbers
        assertTrue(Price.isValidPrice("0.1"));
        assertTrue(Price.isValidPrice("1.1827872"));
        assertTrue(Price.isValidPrice("91"));
        assertTrue(Price.isValidPrice("0093534"));
        assertTrue(Price.isValidPrice("12419"));
    }


    @Test
    public void testToString() {
        String value = "21534";
        Price price = new Price(value);
        assertEquals(price.toString(), "$21,534");
    }

    @Test
    public void value() {
        String value = "931234";
        Price price = new Price(value);
        assertEquals(price.value(), 931234);
    }

    @Test
    public void testEquals() {
        String value = "931234";
        Price price = new Price(value);
        Price test = new Price(value);
        assertEquals(price, test);
    }



}
