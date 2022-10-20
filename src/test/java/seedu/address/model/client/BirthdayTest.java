package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.junit.jupiter.api.Test;


public class BirthdayTest {
    private LocalDate validDate = LocalDate.of(2000, 12, 12);

    @Test
    public void constructor() {
        // throws error if date is null
        assertThrows(NullPointerException.class, () -> new Birthday(null));

        // constructs Birthday with given valid date
        assertTrue(new Birthday(validDate) instanceof Birthday);
    }
    @Test
    public void toString_validBirthdayObject_returnsCorrectString() {
        Birthday validBirthday = new Birthday(LocalDate.of(2000, 4, 4));
        assertEquals("04042000", validBirthday.toString());
    }

    @Test
    public void equals_variousBirthdayObjects_evaluatesCorrectly() {
        Birthday validBirthday = new Birthday(LocalDate.of(2000, 12, 12));

        // same birthday
        assertTrue(validBirthday.equals(validBirthday));

        // identical birthday
        Birthday validBirthdayCopy = new Birthday(LocalDate.of(2000, 12, 12));
        assertTrue(validBirthday.equals(validBirthdayCopy));
    }

    @Test
    public void formattedDate_variousBirthdays_returnsFormattedBirthday() {
        LocalDate date = LocalDate.of(1952, 1, 12);
        Birthday validBirthday = new Birthday(LocalDate.of(1952, 1, 12));

        assertEquals(date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)), validBirthday.formattedDate());
    }
}
