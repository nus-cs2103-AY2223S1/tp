package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Price(null));
    }

    @Test
    public void constructor_invalidPrice_throwsIllegalArgumentException() {
        String invalidPrice = "";
        assertThrows(IllegalArgumentException.class, () -> new Price(invalidPrice));
    }

    @Test
    public void isValidPrice() {
        // null price for item
        assertThrows(NullPointerException.class, () -> Price.isValidPrice(null));

        // invalid prices for items
        assertFalse(Price.isValidPrice("")); // empty string
        assertFalse(Price.isValidPrice(" ")); // empty string// spaces only
        assertFalse(Price.isValidPrice("1.50")); // no $ sign
        assertFalse(Price.isValidPrice("$91")); // no decimal places
        assertFalse(Price.isValidPrice("$21.0")); // only one decimal
        assertFalse(Price.isValidPrice("$27.000")); // more than 2 decimal places
        assertFalse(Price.isValidPrice("$21 .00")); // spaces within price
        assertFalse(Price.isValidPrice("price")); // non-numeric


        // valid prices for items
        assertTrue(Price.isValidPrice("$1.50"));
        assertTrue(Price.isValidPrice("$21.55"));
        assertTrue(Price.isValidPrice("$999.99")); // long price
    }
}
