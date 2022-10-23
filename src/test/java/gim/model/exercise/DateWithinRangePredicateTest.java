package gim.model.exercise;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import gim.model.date.Date;

class DateWithinRangePredicateTest {
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