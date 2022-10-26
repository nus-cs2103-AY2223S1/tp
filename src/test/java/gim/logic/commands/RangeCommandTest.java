package gim.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import gim.model.date.Date;
import gim.model.exercise.DateWithinRangePredicate;

class RangeCommandTest {

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

        RangeCommand rangeCommandOne = new RangeCommand(predicateOne);
        RangeCommand rangeCommandTwo = new RangeCommand(predicateTwo);

        // same object -> returns true
        assertEquals(rangeCommandOne, rangeCommandOne);

        // same values -> returns true
        RangeCommand rangeCommandOneCopy = new RangeCommand(predicateOne);
        assertEquals(rangeCommandOne, rangeCommandOneCopy);

        // different types -> returns false
        assertNotEquals("STRING", rangeCommandOne);

        // null returns false
        assertNotEquals(rangeCommandOne, null);

        // different exercise returns false
        assertNotEquals(rangeCommandOne, rangeCommandTwo);
    }
}
