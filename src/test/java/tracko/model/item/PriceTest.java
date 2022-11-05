package tracko.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class PriceTest {
    @Test
    public void equals() {
        Price price1 = new Price(3.99);
        Price price2 = new Price(1.98);

        // same object -> returns true
        assertTrue(price1.equals(price1));

        //same values -> return true
        Price price1Copy = new Price(3.99);
        assertTrue(price1.equals(price1Copy));

        // different types -> returns false
        assertFalse(price1.equals(1));

        // null -> returns false
        assertFalse(price1.equals(null));

        // different predicate -> returns false
        assertFalse(price1.equals(price2));
    }
}
