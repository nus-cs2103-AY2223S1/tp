package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;


public class FilterBuyerByPriorityPredicateTest {

    @Test
    public void equals() {
        Priority priority1 = new Priority("high");
        Priority priority2 = new Priority("normal");

        FilterBuyerByPriorityPredicate firstPredicate =
                new FilterBuyerByPriorityPredicate(priority1);
        FilterBuyerByPriorityPredicate secondPredicate =
                new FilterBuyerByPriorityPredicate((priority2));

        //same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        //same value -> returns true
        FilterBuyerByPriorityPredicate firstPredicateCopy =
                new FilterBuyerByPriorityPredicate(priority1);
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

        FilterBuyerByPriorityPredicate predicate =
                new FilterBuyerByPriorityPredicate(new Priority("high"));
        assertTrue(predicate.test(new PersonBuilder().withPriority("HIGH").build()));
    }

    @Test
    public void test_priorityDoesNotMatch_returnFalse() {
        //No matching characteristics
        FilterBuyerByPriorityPredicate predicate =
                new FilterBuyerByPriorityPredicate(new Priority("normal"));
        assertFalse(predicate.test(new PersonBuilder().withPriority("high").build()));
    }
}
