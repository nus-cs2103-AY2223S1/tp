package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    public void isDateAfterHundredYear_invalidBirthday_success() {
        LocalDate today = LocalDate.now();

        //Test with a birthday that is today
        Birthday validBirthdayToday = new Birthday(today);
        assertTrue(Birthday.isDateBeforeHundredYear(validBirthdayToday));

        //Test with a birthday that is 100 years ago and 1 day before
        LocalDate validDateHundredYearsAndOneDay = today.minusYears(100).plusDays(1);
        Birthday validBirthdayHundredYearsAndOneDay = new Birthday(validDateHundredYearsAndOneDay);
        assertTrue(Birthday.isDateBeforeHundredYear(validBirthdayHundredYearsAndOneDay));
    }
    @Test
    public void isDateAfterHundredYear_invalidBirthday_failure() {
        LocalDate today = LocalDate.now();

        //Test with a birthday that is 101 years ago
        LocalDate invalidDateHundredYears = today.minusYears(101);
        Birthday invalidBirthdayHundredYears = new Birthday(invalidDateHundredYears);
        assertFalse(Birthday.isDateBeforeHundredYear(invalidBirthdayHundredYears));

        //Test with a birthday that is 100 years and 1 day ago
        LocalDate invalidDateHundredYearsAndOneDay = today.minusYears(100).minusDays(1);
        Birthday invalidBirthdayHundredYearsAndOneDay = new Birthday(invalidDateHundredYearsAndOneDay);
        assertFalse(Birthday.isDateBeforeHundredYear(invalidBirthdayHundredYearsAndOneDay));
    }
}
