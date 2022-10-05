package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.FoodBuilder.DEFAULT_EARLIER_TIME;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FoodBuilder;

public class IsTodayPredicateTest {
    @Test
    public void test_foodRecordedOnSameDay_returnsTrue() {
        IsTodayPredicate predicate = new IsTodayPredicate();
        assertTrue(predicate.test(new FoodBuilder().build()));
    }

    @Test
    public void test_foodRecordedOnDifferentDay_returnsFalse() {
        IsTodayPredicate predicate = new IsTodayPredicate();
        assertFalse(predicate.test(new FoodBuilder().withDateTime(DEFAULT_EARLIER_TIME).build()));
    }
}
