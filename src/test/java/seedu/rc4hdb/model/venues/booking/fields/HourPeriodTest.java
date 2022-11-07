package seedu.rc4hdb.model.venues.booking.fields;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link HourPeriod}.
 */
public class HourPeriodTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new HourPeriod(null));
    }

    @Test
    public void constructor_invalidHourPeriod_throwsIllegalArgumentException() {
        String invalidHourPeriod = "12-11";
        assertThrows(IllegalArgumentException.class, () -> new HourPeriod(invalidHourPeriod));
    }

    @Test
    public void constructor_validHourPeriod_constructHourPeriod() {
        assertTrue(new HourPeriod("12-13") instanceof HourPeriod);
    }

    @Test
    public void isValidHourPeriod() {
        // EP: start >= 8 and end <= 23 - return true
        // start < end and "-" in between start and end
        assertTrue(HourPeriod.isValidHourPeriod("8-23"));
        assertTrue(HourPeriod.isValidHourPeriod("11-17"));

        // EP: start >= end - return false
        assertFalse(HourPeriod.isValidHourPeriod("11-11"));
        assertFalse(HourPeriod.isValidHourPeriod("15-14"));

        // EP: start < 8 or end > 23 - return false
        assertFalse(HourPeriod.isValidHourPeriod("7-11"));
        assertFalse(HourPeriod.isValidHourPeriod("15-24"));

        // EP: not exactly one "-" in between start and end - return false
        assertFalse(HourPeriod.isValidHourPeriod("10 11"));
        assertFalse(HourPeriod.isValidHourPeriod("10--11"));

        // EP: any other strings - return false
        assertFalse(HourPeriod.isValidHourPeriod("1231asd"));
        assertFalse(HourPeriod.isValidHourPeriod("123-1x1"));

        // EP: null string
        assertThrows(NullPointerException.class, () -> HourPeriod.isValidHourPeriod(null));
    }

    @Test
    public void clashesWith() {
        HourPeriod hp12to13 = new HourPeriod("12-13");
        HourPeriod hp13to14 = new HourPeriod("13-14");
        HourPeriod hp12to15 = new HourPeriod("12-15");
        HourPeriod hp14to15 = new HourPeriod("14-15");

        // Intersect at one point - return false
        assertFalse(hp12to13.clashesWith(hp13to14));

        // No intersection at all - return false
        assertFalse(hp12to13.clashesWith(hp14to15));

        // Intersects partially - return true
        assertTrue(hp12to15.clashesWith(hp14to15));

        // Intersects completely - return true
        assertTrue(hp12to13.clashesWith(hp12to13));
    }

    @Test
    public void equals() {
        HourPeriod hp12to13 = new HourPeriod("12-13");
        HourPeriod otherHp12to13 = new HourPeriod("12-13");
        HourPeriod hp12to15 = new HourPeriod("12-15");
        HourPeriod hp14to15 = new HourPeriod("14-15");

        // Same item
        assertTrue(hp12to13.equals(hp12to13));

        // Same start and end
        assertTrue(hp12to13.equals(otherHp12to13));

        // Different start
        assertFalse(hp12to13.equals(hp14to15));

        // Different end
        assertFalse(hp12to13.equals(hp12to15));

        // Different start and end
        assertFalse(hp14to15.equals(otherHp12to13));
    }

}
