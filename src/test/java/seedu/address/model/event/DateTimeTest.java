package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(invalidDate));
    }

    @Test
    public void parseDate_validDates_success() {
        // different separators dd mm yy
        assertEquals(LocalDate.parse("2022-11-23"), DateTime.parseDate("23/11/2022"));
        assertEquals(LocalDate.parse("2022-11-23"), DateTime.parseDate("23-11-2022"));

        // different separators yyyy mm dd
        assertEquals(LocalDate.parse("2022-11-23"), DateTime.parseDate("2022/11/23"));
        assertEquals(LocalDate.parse("2022-11-23"), DateTime.parseDate("2022-11-23"));

        // single day and month
        assertEquals(LocalDate.parse("2000-01-01"), DateTime.parseDate("1/1/2000"));
        assertEquals(LocalDate.parse("2000-01-01"), DateTime.parseDate("1-1-2000"));

        // single day
        assertEquals(LocalDate.parse("2000-12-01"), DateTime.parseDate("1/12/2000"));
        assertEquals(LocalDate.parse("2000-12-01"), DateTime.parseDate("1-12-2000"));

        // single month
        assertEquals(LocalDate.parse("2000-01-12"), DateTime.parseDate("12/1/2000"));
        assertEquals(LocalDate.parse("2000-01-12"), DateTime.parseDate("12-1-2000"));

        // parses february correctly
        assertEquals(LocalDate.parse("2022-02-28"), DateTime.parseDate("28/2/2022"));
        assertEquals(LocalDate.parse("2020-02-29"), DateTime.parseDate("29/2/2020"));

        // parses letters correctly
        assertEquals(LocalDate.parse("2022-10-10"), DateTime.parseDate("10/oct/2022"));
        assertEquals(LocalDate.parse("2022-07-04"), DateTime.parseDate("4-jUly-2022"));

        // parses dd-mm
        String year = String.valueOf(LocalDate.now().getYear());
        assertEquals(LocalDate.parse(year + "-10-10"), DateTime.parseDate("10-oCt"));
        assertEquals(LocalDate.parse(year + "-07-04"), DateTime.parseDate("4/7"));

        // valid years
        assertEquals(LocalDate.parse("0001-10-01"), DateTime.parseDate("01/october/0001"));
        assertEquals(LocalDate.parse("9999-10-01"), DateTime.parseDate("01-october-9999"));

    }

    @Test
    public void parseDate_invalidDates_exceptionThrown() {
        // out of bounds
        assertThrows(DateTimeParseException.class, () -> DateTime.parseDate("99/99/9999"));

        // random month
        assertThrows(DateTimeParseException.class, () -> DateTime.parseDate("20-whatmonth-2022"));

        // zeroes
        assertThrows(DateTimeParseException.class, () -> DateTime.parseDate("00-october-2022"));
        assertThrows(DateTimeParseException.class, () -> DateTime.parseDate("01-00-2022"));

        // invalid date do not parse
        assertThrows(DateTimeParseException.class, () -> DateTime.parseDate("31/february/2022"));
        assertThrows(DateTimeParseException.class, () -> DateTime.parseDate("30-february-2020"));

        assertThrows(DateTimeParseException.class, () -> DateTime.parseDate("01/october/0000"));
    }

    @Test
    public void parseTime_validTimes_success() {
        final String validDate = "23/11/2022 ";
        // no time
        assertEquals(Optional.empty(), DateTime.parseTime("23/11/2022"));
        assertEquals(Optional.empty(), DateTime.parseTime("1-jan-1998"));

        // HHmm
        assertEquals(Optional.ofNullable(LocalTime.parse("23:59")),
                DateTime.parseTime(validDate + "2359"));
        assertEquals(Optional.ofNullable(LocalTime.parse("00:00")),
                DateTime.parseTime(validDate + "0000"));

        // HH:mm
        assertEquals(Optional.ofNullable(LocalTime.parse("23:59")),
                DateTime.parseTime(validDate + "23:59"));
        assertEquals(Optional.ofNullable(LocalTime.parse("00:00")),
                DateTime.parseTime(validDate + "00:00"));
    }

    @Test
    public void parseTime_invalidTimes_exceptionThrown() {
        final String validDate = "23/11/2022 ";
        // out of bounds time
        assertThrows(DateTimeParseException.class, () -> DateTime.parseTime(validDate + "9999"));
        assertThrows(DateTimeParseException.class, () -> DateTime.parseTime(validDate + "5959"));
        assertThrows(DateTimeParseException.class, () -> DateTime.parseTime(validDate + "99:99"));
        assertThrows(DateTimeParseException.class, () -> DateTime.parseTime(validDate + "59:59"));

        // 60 as minutes
        assertThrows(DateTimeParseException.class, () -> DateTime.parseTime(validDate + "2360"));
        assertThrows(DateTimeParseException.class, () -> DateTime.parseTime(validDate + "23:60"));
    }

    @Test
    public void isValidDateTime_validDates_returnTrue() {
        // day, month only
        assertTrue(DateTime.isValidDateTime("2/2"));
        assertTrue(DateTime.isValidDateTime("2/22"));
        assertTrue(DateTime.isValidDateTime("22/2"));
        assertTrue(DateTime.isValidDateTime("22/22"));
        assertTrue(DateTime.isValidDateTime("22-22"));
        assertTrue(DateTime.isValidDateTime("22-Oct"));
        assertTrue(DateTime.isValidDateTime("22/acdef"));
        assertTrue(DateTime.isValidDateTime("2/octOber"));

        // day, month, time only
        assertTrue(DateTime.isValidDateTime("22/22 0000"));
        assertTrue(DateTime.isValidDateTime("22/22 00:00"));

        // day, month, year only
        assertTrue(DateTime.isValidDateTime("24/02/2022"));
        assertTrue(DateTime.isValidDateTime("31/Jan/2015"));
        assertTrue(DateTime.isValidDateTime("25-05-2018"));
        assertTrue(DateTime.isValidDateTime("40/02/2022"));
        assertTrue(DateTime.isValidDateTime("21-Rep-2015"));

        // day, month, year, time
        assertTrue(DateTime.isValidDateTime("24/02/2022 26:24"));
        assertTrue(DateTime.isValidDateTime("24/02/2022 99:99"));
        assertTrue(DateTime.isValidDateTime("31/Jan/2015 13:66"));
        assertTrue(DateTime.isValidDateTime("24/02/2022 13:43"));
        assertTrue(DateTime.isValidDateTime("31-Jan-2015 16:21"));
        assertTrue(DateTime.isValidDateTime("25-05-2018 04:55"));

        // longer whitespace
        assertTrue(DateTime.isValidDateTime("25-05-2018               04:55"));
        assertTrue(DateTime.isValidDateTime("25/jun/2018                            0455"));
    }

    @Test
    public void getDifferenceString_validDates_success() {
        DateTime dateOne = new DateTime("22/02/2022 06:00");
        DateTime dateTwo = new DateTime("22/02/2022 06:30");
        DateTime dateThree = new DateTime("22/02/2022 07:00");
        DateTime dateFour = new DateTime("22/02/2022 07:30");
        DateTime dateFive = new DateTime("23/02/2022 06:00");
        DateTime dateSix = new DateTime("23/02/2022 06:30");
        DateTime dateSeven = new DateTime("23/02/2022 07:00");
        DateTime dateEight = new DateTime("23/02/2022 07:30");
        DateTime dateNine = new DateTime("23-02-2022 07:30");
        // same time
        assertEquals(DateTime.getDifferenceString(dateOne, dateOne), "No Duration");
        // same day, same hour, different minute
        assertEquals(DateTime.getDifferenceString(dateOne, dateTwo), "30 minutes");
        // same day, different hour, same minute
        assertEquals(DateTime.getDifferenceString(dateOne, dateThree), "1 hour");
        // same day, different hour, different minute
        assertEquals(DateTime.getDifferenceString(dateOne, dateFour), "1 hour, 30 minutes");
        // different day, same hour, same minute
        assertEquals(DateTime.getDifferenceString(dateOne, dateFive), "1 day");
        // different day, same hour, different minute
        assertEquals(DateTime.getDifferenceString(dateOne, dateSix), "1 day, 30 minutes");
        // different day, different hour, same minute
        assertEquals(DateTime.getDifferenceString(dateOne, dateSeven), "1 day, 1 hour");
        // different day, different hour, different minute
        assertEquals(DateTime.getDifferenceString(dateOne, dateEight), "1 day, 1 hour, 30 minutes");
        // different format
        assertEquals(DateTime.getDifferenceString(dateOne, dateNine), "1 day, 1 hour, 30 minutes");
    }

    @Test
    public void compareTo_bothHaveTime() {
        // Same Date and Time
        assertEquals(new DateTime("22/02/2022 06:00").compareTo(new DateTime("22/02/2022 06:00")), 0);

        // Compared to a later Date and Time
        assertEquals(new DateTime("22/02/2022 06:00").compareTo(new DateTime("22/02/2022 07:00")), -1);
        assertEquals(new DateTime("22/02/2022 06:00").compareTo(new DateTime("23/02/2022 06:00")), -1);

        // Compared to an earlier Date and Time
        assertEquals(new DateTime("22/02/2022 06:00").compareTo(new DateTime("22/02/2022 05:00")), 1);
        assertEquals(new DateTime("22/02/2022 06:00").compareTo(new DateTime("21/02/2022 06:00")), 1);
    }

    @Test
    public void compareTo_bothNoTime() {
        // Same Date
        assertEquals(new DateTime("22/02/2022").compareTo(new DateTime("22/02/2022")), 0);

        // Compared to a later Date
        assertEquals(new DateTime("22/02/2022").compareTo(new DateTime("23/02/2022")), -1);

        // Compared to an earlier Date
        assertEquals(new DateTime("22/02/2022").compareTo(new DateTime("21/02/2022")), 1);
    }

    @Test
    public void compareTo_oneWithAnotherWithoutTime() {
        // With time compared to another with time (Same Date)
        assertEquals(new DateTime("22/02/2022").compareTo(new DateTime("22/02/2022 06:00")), -1);

        // With time compared to another with time (Different Date)
        assertEquals(new DateTime("22/02/2022").compareTo(new DateTime("21/02/2022 06:00")), 1);

        // With time compared to another without time (Same Date)
        assertEquals(new DateTime("22/02/2022 06:00").compareTo(new DateTime("22/02/2022")), 1);

        // With time compared to another without time (Different Date)
        assertEquals(new DateTime("21/02/2022 06:00").compareTo(new DateTime("22/02/2022")), -1);
    }
}
