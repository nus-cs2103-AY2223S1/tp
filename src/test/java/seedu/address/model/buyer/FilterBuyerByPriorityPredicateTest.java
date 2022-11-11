package seedu.address.model.buyer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BuyerBuilder;


public class FilterBuyerByPriorityPredicateTest {

    @Test
    public void equals() {
        Priority priority1 = new Priority("high");
        Priority priority2 = new Priority("normal");

        FilterBuyersByPriorityPredicate firstPredicate =
                new FilterBuyersByPriorityPredicate(priority1);
        FilterBuyersByPriorityPredicate secondPredicate =
                new FilterBuyersByPriorityPredicate((priority2));

        //same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        //same value -> returns true
        FilterBuyersByPriorityPredicate firstPredicateCopy =
                new FilterBuyersByPriorityPredicate(priority1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        //different types -> returns false
        assertFalse(firstPredicate.equals("high"));

        //null -> returns false
        assertFalse(firstPredicate.equals(null));

        //different characteristics -> false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_priorityMatch_returnTrue() {

        FilterBuyersByPriorityPredicate predicate =
                new FilterBuyersByPriorityPredicate(new Priority("high"));
        assertTrue(predicate.test(new BuyerBuilder().withPriority("HIGH").build()));
    }

    @Test
    public void test_priorityDoesNotMatch_returnFalse() {
        //Different Priority
        FilterBuyersByPriorityPredicate predicate =
                new FilterBuyersByPriorityPredicate(new Priority("normal"));
        assertFalse(predicate.test(new BuyerBuilder().withPriority("high").build()));
    }
}
