package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import seedu.address.model.appointment.Time;

public class TimeTest {
    private static final Time TIME_TWELVE_THIRTY_PM = new Time(LocalTime.of(12, 30));
    private static final Time TIME_ONE_THIRTY_AM = new Time(LocalTime.of(1, 30));
    private static final Time TIME_ONE_THIRTY_PM = new Time(LocalTime.of(13, 30));

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void versionToString_validVersion_correctStringRepresentation() {
        Time time = new Time(LocalTime.of(12, 30));
        assertEquals("12:30 PM", time.toString());
    }

    @Test
    public void equals_success() {

        // same object -> returns true
        assertEquals(TIME_TWELVE_THIRTY_PM, TIME_TWELVE_THIRTY_PM);

        // null -> returns false
        assertNotEquals(TIME_TWELVE_THIRTY_PM, null);

        // different types -> returns false
        assertNotEquals(TIME_TWELVE_THIRTY_PM, 1);

        // different hour -> returns false
        assertNotEquals(TIME_TWELVE_THIRTY_PM, TIME_ONE_THIRTY_PM);

        // different period -> returns false
        assertNotEquals(TIME_ONE_THIRTY_AM, TIME_ONE_THIRTY_PM);
    }
}
