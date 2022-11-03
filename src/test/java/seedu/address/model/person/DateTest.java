package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.address.model.appointment.Date;
import seedu.address.model.appointment.Time;

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
        Date date = new Date(LocalDate.of(2023, 1, 21));
        assertEquals("21-Jan-2023", date.toString());
    }

    @Test
    public void getYear_sameYear_success() {
        Date date = new Date(LocalDate.of(2023, 1, 21));
        Assertions.assertEquals(2023, date.getYear());
        Assertions.assertNotEquals(2022, date.getYear());
    }
    @Test
    public void getMonth_sameMonth_success() {
        Date date = new Date(LocalDate.of(2023, 1, 21));
        Assertions.assertEquals(1, date.getMonth());
        Assertions.assertNotEquals(2, date.getMonth());
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
