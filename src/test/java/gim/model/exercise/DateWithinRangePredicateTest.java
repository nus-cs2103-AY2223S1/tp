package gim.model.exercise;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gim.model.date.Date;
import gim.testutil.ExerciseBuilder;

class DateWithinRangePredicateTest {
    @Test
    public void getRangeSizeInDays() {
        DateWithinRangePredicate predicate = new DateWithinRangePredicate(
            new Date("10/10/2022"), new Date("12/10/2022")
        );
        assertEquals(2, predicate.getRangeSizeInDays());
    }

    @Test
    public void test_success() {
        DateWithinRangePredicate predicate = new DateWithinRangePredicate(
                new Date("10/10/2022"), new Date("12/10/2022")
        );
        Exercise exercise = new ExerciseBuilder().withDate("11/10/2022").build();
        assertTrue(predicate.test(exercise));
    }

    @Test
    public void equals() {
        String startDateStringOne = "10/10/2022";
        String endDateStringOne = "12/10/2022";
        String startDateStringTwo = "21/10/2022";
        String endDateStringTwo = "28/10/2022";

        DateWithinRangePredicate predicateOne =
                new DateWithinRangePredicate(new Date(startDateStringOne), new Date(endDateStringOne));
        DateWithinRangePredicate predicateTwo =
                new DateWithinRangePredicate(new Date(startDateStringTwo), new Date(endDateStringTwo));

        // same object -> returns true
        assertEquals(predicateOne, predicateOne);

        // same values -> returns true
        DateWithinRangePredicate predicateOneCopy =
                new DateWithinRangePredicate(new Date(startDateStringOne), new Date(endDateStringOne));
        assertEquals(predicateOne, predicateOneCopy);

        // different types -> returns false
        assertNotEquals(predicateOne, "STRING");

        // null -> returns false
        assertNotEquals(predicateOne, null);

        // different exercise -> returns false
        assertNotEquals(predicateOne, predicateTwo);
    }
}
