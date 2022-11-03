package jarvis.model;

import static jarvis.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class TimePeriodTest {
    private LocalDateTime dt1 = LocalDateTime.of(2022, 12, 12, 10, 0);
    private LocalDateTime dt2 = LocalDateTime.of(2022, 12, 12, 11, 0);
    private LocalDateTime dt3 = LocalDateTime.of(2022, 12, 15, 10, 0);
    private LocalDateTime dt4 = LocalDateTime.of(2022, 12, 15, 20, 0);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimePeriod(null, null));
        assertThrows(NullPointerException.class, () -> new TimePeriod(null, dt1));
        assertThrows(NullPointerException.class, () -> new TimePeriod(dt1, null));
    }

    @Test
    public void constructor_invalidTimePeriod_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new TimePeriod(dt1, dt1)); // Same start and end time

        // End time after start time
        assertThrows(IllegalArgumentException.class, () -> new TimePeriod(dt2, dt1));
        assertThrows(IllegalArgumentException.class, () -> new TimePeriod(dt3, dt1));
    }

    @Test
    public void constructor_validTimePeriod() {
        TimePeriod timePeriod1 = new TimePeriod(dt1, dt2); // Same day
        TimePeriod timePeriod2 = new TimePeriod(dt1, dt3); // Different day

        assertEquals(dt1, timePeriod1.getStart());
        assertEquals(dt2, timePeriod1.getEnd());
    }

    @Test
    public void isValidTimePeriod() {
        assertTrue(TimePeriod.isValidTimePeriod(dt1, dt2)); // same day, chronological order
        assertTrue(TimePeriod.isValidTimePeriod(dt2, dt3)); // different day, chronological order

        LocalDateTime dt1Copy = LocalDateTime.of(2022, 12, 12, 10, 0);
        assertFalse(TimePeriod.isValidTimePeriod(dt1, dt1Copy)); // same time
        assertFalse(TimePeriod.isValidTimePeriod(dt2, dt1)); // same day, not in chronological order
        assertFalse(TimePeriod.isValidTimePeriod(dt1, null)); // null datetime
        assertFalse(TimePeriod.isValidTimePeriod(null, null)); // same day, chronological order
    }

    @Test
    public void hasOverlap() {
        // 1 time period contained entirely within the other
        TimePeriod inner = new TimePeriod(dt2, dt3);
        TimePeriod outer = new TimePeriod(dt1, dt4);

        assertTrue(inner.hasOverlap(outer));
        assertTrue(outer.hasOverlap(inner));

        // Overlap but not contained within each other
        TimePeriod timePeriod1 = new TimePeriod(dt1, dt3);
        TimePeriod timePeriod2 = new TimePeriod(dt2, dt4);

        assertTrue(timePeriod1.hasOverlap(timePeriod2));
        assertTrue(timePeriod2.hasOverlap(timePeriod1));

        // No overlap
        TimePeriod first = new TimePeriod(dt1, dt2);
        TimePeriod second = new TimePeriod(dt3, dt4);

        assertFalse(first.hasOverlap(second));
        assertFalse(second.hasOverlap(first));

        // End of first time period is start of second time period
        TimePeriod firstHalf = new TimePeriod(dt1, dt2);
        TimePeriod secondHalf = new TimePeriod(dt2, dt3);

        assertFalse(firstHalf.hasOverlap(secondHalf));
        assertFalse(secondHalf.hasOverlap(firstHalf));
    }

    @Test
    public void isOnSameDay() {
        TimePeriod sameDay = new TimePeriod(dt1, dt2);
        TimePeriod differentDay = new TimePeriod(dt1, dt3);

        assertTrue(sameDay.isOnSameDay());
        assertFalse(differentDay.isOnSameDay());
    }

    @Test
    public void testEquals() {
        TimePeriod timePeriod1 = new TimePeriod(dt1, dt2);
        TimePeriod timePeriod2 = new TimePeriod(dt3, dt4);
        TimePeriod timePeriod3 = new TimePeriod(dt1, dt3);

        // same time -> returns true
        TimePeriod timePeriod1Copy = new TimePeriod(dt1, dt2);
        assertTrue(timePeriod1.equals(timePeriod1Copy));

        // same object -> returns true
        assertTrue(timePeriod1.equals(timePeriod1));

        // null -> returns false
        assertFalse(timePeriod1.equals(null));

        // different type -> returns false
        assertFalse(timePeriod1.equals(5));

        //different values -> returns false
        assertFalse(timePeriod1.equals(timePeriod2));
        assertFalse(timePeriod1.equals(timePeriod3));
    }
}