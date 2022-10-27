package seedu.address.model.tuitionclass;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.tuitionclass.exceptions.InvalidDayException;

public class DayTest {

    @Test
    public void createDay_invalidDay_throwsInvalidDayException() {
        String invalidDay = "yesterday";
        assertThrows(InvalidDayException.class, () -> Day.createDay(invalidDay));
    }

    @Test
    public void createLevel_validLevel_success() {
        assertTrue(Day.createDay("MONDAY") == Day.MONDAY);
        assertTrue(Day.createDay("TUESDAY") == Day.TUESDAY);
        assertTrue(Day.createDay("WEDNESDAY") == Day.WEDNESDAY);
        assertTrue(Day.createDay("THURSDAY") == Day.THURSDAY);
        assertTrue(Day.createDay("FRIDAY") == Day.FRIDAY);
        assertTrue(Day.createDay("SATURDAY") == Day.SATURDAY);
        assertTrue(Day.createDay("SUNDAY") == Day.SUNDAY);
    }
}
