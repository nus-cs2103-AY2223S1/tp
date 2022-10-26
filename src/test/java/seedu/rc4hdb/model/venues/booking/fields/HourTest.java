package seedu.rc4hdb.model.venues.booking.fields;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Hour}.
 */
public class HourTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Hour(null));
    }

    @Test
    public void constructor_invalidHour_throwsIllegalArgumentException() {
        String invalidHour = "123";
        assertThrows(IllegalArgumentException.class, () -> new Hour(invalidHour));
    }

    @Test
    public void constructor_validHour_constructHour() {
        assertTrue(new Hour("12") instanceof Hour);
    }

    @Test
    public void asInt_returnsInt() {
        assertTrue(8 == new Hour("8").asInt());
    }

    @Test
    public void isAfterOrDuring() {
        Hour firstHour = new Hour("16");
        Hour secondHour = new Hour("9");

        // EP: this hour is after or during other hour - return true
        assertTrue(firstHour.isAfterOrDuring(secondHour));
        assertTrue(firstHour.isAfterOrDuring(firstHour));

        // EP: this hour is before the other hour - return false
        assertFalse(secondHour.isAfterOrDuring(firstHour));
    }

    @Test
    public void isValidHour() {
        // EP: before 8 and after 23
        assertFalse(Hour.isValidHour("7"));
        assertFalse(Hour.isValidHour("24"));

        // EP: between 8 and 23
        assertTrue(Hour.isValidHour("8"));
        assertTrue(Hour.isValidHour("23"));
        assertTrue(Hour.isValidHour("15"));

        // EP: any other string
        assertFalse(Hour.isValidHour("69012343"));
        assertFalse(Hour.isValidHour("asdfpadf"));
        assertFalse(Hour.isValidHour("123af..de"));

        // EP: null string
        assertFalse(Hour.isValidHour(null));
    }

}
