package tracko.model.items;

import org.junit.jupiter.api.Test;
import tracko.model.items.Quantity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.testutil.Assert.assertThrows;

public class QuantityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Quantity(null));
    }

    @Test
    public void constructor_invalidQuantity_throwsIllegalArgumentException() {
        int invalidQuantity = -500;
        assertThrows(IllegalArgumentException.class, () -> new Quantity(invalidQuantity));
    }

    @Test
    public void isValidQuantity() {
        // null
        assertThrows(NullPointerException.class, () -> Quantity.isValidQuantity(null));

        // invalid Quantity
        assertFalse(Quantity.isValidQuantity(-500)); // negative number
        assertFalse(Quantity.isValidQuantity(-2147483648)); // Integer.MIN_VALUE

        // valid Quantity
        assertTrue(Quantity.isValidQuantity(300));
        assertTrue(Quantity.isValidQuantity(200));
        assertTrue(Quantity.isValidQuantity(2147483647)); // Integer.MAX_VALUE
    }

    @Test
    public void equals() {
        int firstQuantityInt = 100;
        int secondQuantityInt = 249248;

        Quantity firstQuantity = new Quantity(firstQuantityInt);
        Quantity secondQuantity = new Quantity(secondQuantityInt);

        // same object -> returns true
        assertTrue(firstQuantity.equals(firstQuantity));

        Quantity firstQuantityCopy = new Quantity(firstQuantityInt);
        // same values -> returns true
        assertTrue(firstQuantity.equals(firstQuantityCopy));

        // different types -> returns false
        assertFalse(firstQuantity.equals("Hello"));
        assertFalse(firstQuantity.equals(40000.3245));

        // null -> returns false
        assertFalse(firstQuantity.equals(null));

        // different item name -> returns false
        assertFalse(firstQuantity.equals(secondQuantity));
    }
}

