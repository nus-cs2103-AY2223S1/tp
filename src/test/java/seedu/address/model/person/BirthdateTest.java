package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.person.Birthdate.DATE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class BirthdateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthdate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidBirthdate = "2001.09.11";
        assertThrows(IllegalArgumentException.class, () -> new Birthdate(invalidBirthdate));
    }

    @Test
    void isValidDateFormat() {
        // null birthdate
        assertThrows(NullPointerException.class, () -> Birthdate.isValidDateFormat(null));

        // invalid birthdate
        assertFalse(Birthdate.isValidDateFormat("")); // empty string
        assertFalse(Birthdate.isValidDateFormat(" ")); // spaces only
        assertFalse(Birthdate.isValidDateFormat("02022002")); // incorrect date format
        assertFalse(Birthdate.isValidDateFormat("11.11.2001")); // incorrect date format
        assertFalse(Birthdate.isValidDateFormat("2009.09.21")); // incorrect date format
        assertFalse(Birthdate.isValidDateFormat("10 Jul 2022")); // incorrect date format
        assertFalse(Birthdate.isValidDateFormat("12 12 2007")); // incorrect date format
        assertFalse(Birthdate.isValidDateFormat("2004-11-11")); // incorrect date format

        // valid birthdate
        assertTrue(Birthdate.isValidDateFormat("01-01-2001"));
        assertTrue(Birthdate.isValidDateFormat("12-02-1927"));
        assertTrue(Birthdate.isValidDateFormat("31-12-2003"));
    }

    @Test
    void isFutureDate() {
        assertTrue(Birthdate.isFutureDate(LocalDate.now().plusYears(10).format(DATE_FORMAT)));
        assertTrue(Birthdate.isFutureDate(LocalDate.now().plusDays(1).format(DATE_FORMAT)));
        assertTrue(Birthdate.isFutureDate(LocalDate.now().plusMonths(1).format(DATE_FORMAT)));

        assertFalse(Birthdate.isFutureDate(LocalDate.now().minusYears(6).format(DATE_FORMAT)));
        assertFalse(Birthdate.isFutureDate(LocalDate.now().minusDays(1).format(DATE_FORMAT)));
        assertFalse(Birthdate.isFutureDate(LocalDate.now().minusMonths(1).format(DATE_FORMAT)));
    }

    @Test
    void calculateAge() {
        // set up test Birthdate
        Birthdate bd1 = new Birthdate("01-04-1974");
        Birthdate bd2 = new Birthdate("05-05-1995");
        Birthdate bd3 = new Birthdate("03-08-1989");
        Birthdate bd4 = new Birthdate("30-03-2003");
        Birthdate bd5 = new Birthdate("10-10-2010");

        assertEquals(48, bd1.calculateAge());
        assertEquals(27, bd2.calculateAge());
        assertEquals(33, bd3.calculateAge());
        assertEquals(19, bd4.calculateAge());
        assertEquals(12, bd5.calculateAge());
    }

    @Test
    void toDisplayFormat() {
        // set up test Birthdate
        Birthdate bd1 = new Birthdate("01-04-1974");
        Birthdate bd2 = new Birthdate("05-05-1995");
        Birthdate bd3 = new Birthdate("03-08-1989");
        Birthdate bd4 = new Birthdate("30-03-2003");
        Birthdate bd5 = new Birthdate("10-10-2010");

        assertEquals("1 Apr 1974", bd1.toDisplayFormat());
        assertEquals("5 May 1995", bd2.toDisplayFormat());
        assertEquals("3 Aug 1989", bd3.toDisplayFormat());
        assertEquals("30 Mar 2003", bd4.toDisplayFormat());
        assertEquals("10 Oct 2010", bd5.toDisplayFormat());
    }
}
