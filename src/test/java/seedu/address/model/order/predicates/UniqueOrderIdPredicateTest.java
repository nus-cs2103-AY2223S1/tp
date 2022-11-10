package seedu.address.model.order.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalOrders;

public class UniqueOrderIdPredicateTest {
    @Test
    public void equals() {
        UniqueOrderIdPredicate firstPredicate = new UniqueOrderIdPredicate(TypicalOrders.ORDER_1.getId());
        UniqueOrderIdPredicate secondPredicate = new UniqueOrderIdPredicate(TypicalOrders.ORDER_2.getId());

        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        UniqueOrderIdPredicate firstPredicateCopy = new UniqueOrderIdPredicate(TypicalOrders.ORDER_1.getId());
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
