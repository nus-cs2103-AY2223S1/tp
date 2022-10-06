package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.FoodBuilder.DEFAULT_EARLIER_TIME;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FoodBuilder;

public class IsFoodAddedTodayPredicateTest {
    @Test
    public void test_foodRecordedOnSameDay_returnsTrue() {
        IsFoodAddedTodayPredicate predicate = new IsFoodAddedTodayPredicate();
        assertTrue(predicate.test(new FoodBuilder().build()));
    }

    @Test
    public void test_foodRecordedOnDifferentDay_returnsFalse() {
        IsFoodAddedTodayPredicate predicate = new IsFoodAddedTodayPredicate();
        assertFalse(predicate.test(new FoodBuilder().withDateTime(DEFAULT_EARLIER_TIME).build()));
    }
}
