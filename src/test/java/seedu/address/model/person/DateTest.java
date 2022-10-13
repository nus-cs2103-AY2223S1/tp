package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class DateTest {
    private static final Date DATE_21_JAN_2023 = new Date(LocalDate.of(2023, 1, 21));
    private static final Date DATE_22_JAN_2023 = new Date(LocalDate.of(2023, 1, 22));
    private static final Date DATE_21_JAN_2022 = new Date(LocalDate.of(2022, 1, 21));
    private static final Date DATE_21_FEB_2023 = new Date(LocalDate.of(2023, 2, 21));

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
        assertEquals(DATE_21_JAN_2023, DATE_21_JAN_2023);

        // null -> returns false
        assertNotEquals(DATE_21_JAN_2023, null);

        // different types -> returns false
        assertNotEquals(DATE_21_JAN_2023, 1);

        // different day -> returns false
        assertNotEquals(DATE_21_JAN_2023, DATE_22_JAN_2023);

        // different month -> returns false
        assertNotEquals(DATE_21_JAN_2023, DATE_21_FEB_2023);

        // different year -> returns false
        assertNotEquals(DATE_21_JAN_2023, DATE_21_JAN_2022);
    }
}
