package tracko.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tracko.testutil.OrderBuilder;

public class OrderDateTimeComparatorTest {
    @Test
    public void equals() {
        OrderDateTimeComparator orderDateTimeComparator1 = new OrderDateTimeComparator("new");
        OrderDateTimeComparator orderDateTimeComparator2 = new OrderDateTimeComparator("old");

        // same object -> returns true
        assertTrue(orderDateTimeComparator1.equals(orderDateTimeComparator1));

        //same values -> return true
        OrderDateTimeComparator orderDateTimeComparator1Copy = new OrderDateTimeComparator("new");
        assertTrue(orderDateTimeComparator1.equals(orderDateTimeComparator1Copy));

        // different types -> returns false
        assertFalse(orderDateTimeComparator1.equals(1));

        // null -> returns false
        assertFalse(orderDateTimeComparator1.equals(null));

        // different predicate -> returns false
        assertFalse(orderDateTimeComparator1.equals(orderDateTimeComparator2));
    }

    @Test
    public void test_compareByOldest_returnsTrue() {
        OrderDateTimeComparator comparator = new OrderDateTimeComparator("old");
        Order olderOrder = new OrderBuilder().build();
        Order newerOrder = new OrderBuilder().build();
        assertSame(0, comparator.compare(olderOrder, newerOrder));
    }

    @Test
    public void test_compareByNewest_returnsTrue() {
        OrderDateTimeComparator comparator = new OrderDateTimeComparator("new");
        Order olderOrder = new OrderBuilder().build();
        Order newerOrder = new OrderBuilder().build();
        assertSame(0, comparator.compare(olderOrder, newerOrder));
    }
}
