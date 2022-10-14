package seedu.nutrigoals.model.meal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.testutil.FoodBuilder.DEFAULT_EARLIER_TIME;
import static seedu.nutrigoals.testutil.FoodBuilder.DEFAULT_LATER_TIME;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.testutil.FoodBuilder;

public class IsFoodAddedOnThisDatePredicateTest {
    @Test
    public void getDate() {
        DateTime dateTime = new DateTime(DEFAULT_EARLIER_TIME);
        IsFoodAddedOnThisDatePredicate predicate = new IsFoodAddedOnThisDatePredicate(dateTime);
        assertEquals(dateTime.getDate(), predicate.getDate());
    }

    @Test
    public void test_foodRecordedOnSameDay_returnsTrue() {
        IsFoodAddedOnThisDatePredicate predicate = new IsFoodAddedOnThisDatePredicate(new DateTime());
        assertTrue(predicate.test(new FoodBuilder().build()));
    }

    @Test
    public void test_foodRecordedOnDifferentDay_returnsFalse() {
        IsFoodAddedOnThisDatePredicate predicate = new IsFoodAddedOnThisDatePredicate(new DateTime());
        assertFalse(predicate.test(new FoodBuilder().withDateTime(DEFAULT_EARLIER_TIME).build()));
    }

    @Test
    public void equals() {
        DateTime firstDateTime = new DateTime(DEFAULT_EARLIER_TIME);
        DateTime secondDateTime = new DateTime(DEFAULT_LATER_TIME);
        DateTime thirdDateTime = new DateTime();
        IsFoodAddedOnThisDatePredicate firstPredicate = new IsFoodAddedOnThisDatePredicate(firstDateTime);
        IsFoodAddedOnThisDatePredicate secondPredicate = new IsFoodAddedOnThisDatePredicate(secondDateTime);
        IsFoodAddedOnThisDatePredicate thirdPredicate = new IsFoodAddedOnThisDatePredicate(thirdDateTime);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IsFoodAddedOnThisDatePredicate firstPredicateCopy = new IsFoodAddedOnThisDatePredicate(firstDateTime);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different time -> returns true
        assertTrue(firstPredicate.equals(secondPredicate));

        // different date -> returns false
        assertFalse(firstPredicate.equals(thirdPredicate));
    }
}
