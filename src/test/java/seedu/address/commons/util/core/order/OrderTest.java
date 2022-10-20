package seedu.address.commons.util.core.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.order.Order;

public class OrderTest {

    @Test
    public void createIncreasingOrder() {
        //invalid order
        assertThrows(IllegalArgumentException.class, () -> Order.lexicographicalOrder("INVALID"));

        // check equality using the same order
        assertEquals(true, Order.lexicographicalOrder("A-Z").isIncreasingOrder());
    }

    @Test
    public void createDecreasingOrder() {
        // check equality using the same order
        assertEquals(false, Order.lexicographicalOrder("Z-A").isIncreasingOrder());
    }

    @Test
    public void equals() {
        final Order increasingOrder = Order.lexicographicalOrder("A-Z");
        final Order decreasingOrder = Order.lexicographicalOrder("Z-A");

        // same values -> return true
        assertTrue(increasingOrder.equals(Order.lexicographicalOrder("A-Z")));
        assertTrue(decreasingOrder.equals(Order.lexicographicalOrder("Z-A")));

        // same object -> returns true
        assertTrue(increasingOrder.equals(increasingOrder));
        assertTrue(decreasingOrder.equals(decreasingOrder));

        // null -> returns false
        assertFalse(increasingOrder.equals(null));
        assertFalse(decreasingOrder.equals(null));

        // different order -> return false
        assertFalse(increasingOrder.equals(decreasingOrder));
    }
}
