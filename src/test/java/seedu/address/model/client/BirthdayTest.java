package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.junit.jupiter.api.Test;


public class BirthdayTest {

    @Test
    public void toString_variousBirthdayObjects_returnsCorrectString() {
        Birthday emptyBirthday = new Birthday(null);
        assertEquals("", emptyBirthday.toString());

        Birthday validBirthday = new Birthday(LocalDate.of(2000, 4, 4));
        assertEquals("04042000", validBirthday.toString());
    }

    @Test
    public void equals_variousBirthdayObjects_evalutatesCorrectly() {
        Birthday emptyBirthday = new Birthday(null);
        Birthday nullBirthday = null;
        Birthday validBirthday = new Birthday(LocalDate.of(2000, 12, 12));

        // same birthday
        assertTrue(emptyBirthday.equals(emptyBirthday));
        assertTrue(validBirthday.equals(validBirthday));

        // identical birthday
        Birthday validBirthdayCopy = new Birthday(LocalDate.of(2000, 12, 12));
        assertTrue(validBirthday.equals(validBirthdayCopy));

        // emptyBirthday does not equal nullBirthday
        assertFalse(emptyBirthday.equals(nullBirthday));
    }

    @Test
    public void formattedDate_variousBirthdays_returnsFormattedBirthday() {
        Birthday emptyBirthday = new Birthday(null);
        LocalDate date = LocalDate.of(1952, 1, 12);
        Birthday validBirthday = new Birthday(LocalDate.of(1952, 1, 12));

        assertEquals(date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)), validBirthday.formattedDate());
        assertEquals("", emptyBirthday.formattedDate());
    }
}
