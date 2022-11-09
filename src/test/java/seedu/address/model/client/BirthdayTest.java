package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.DateKeyword;

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

    @Test
    public void upcomingBirthday_validBirthday_success() {
        //Any date
        LocalDate testDate = LocalDate.of(2022, 1, 11);
        LocalDate validDate = LocalDate.of(2000, 1, 11);
        Birthday testBirthday = new Birthday(testDate);
        Birthday validBirthday = new Birthday(validDate);

        assertEquals(testBirthday, Birthday.upcomingBirthday(validBirthday, testDate));

        //Check for Leap year
        LocalDate testLeapDate = LocalDate.of(2020, 2, 29);
        LocalDate testNotLeapDate = LocalDate.of(2022, 2, 28);
        LocalDate validLeapDate = LocalDate.of(1996, 2, 29);

        Birthday testLeapBirthday = new Birthday(testLeapDate);
        Birthday testNotLeapBirthday = new Birthday(testNotLeapDate);
        Birthday validLeapBirthday = new Birthday(validLeapDate);

        assertEquals(testLeapBirthday, Birthday.upcomingBirthday(validLeapBirthday, testLeapDate));
        assertEquals(testNotLeapBirthday, Birthday.upcomingBirthday(validLeapBirthday, testNotLeapDate));
    }

    @Test
    public void isInPeriod_validDate_success() {
        //Within the same month
        LocalDate testDate = LocalDate.of(2022, 1, 11);
        LocalDate tomorrowDate = LocalDate.of(2022, 1, 12);
        LocalDate endWeekDate = LocalDate.of(2022, 1, 18);
        LocalDate startMonthDate = LocalDate.of(2022, 1, 1);
        LocalDate endMonthDate = LocalDate.of(2022, 1, 31);

        Birthday tomorrowBirthday = new Birthday(tomorrowDate);
        Birthday startWeekBirthday = new Birthday(testDate);
        Birthday endWeekBirthday = new Birthday(endWeekDate);
        Birthday startMonthBirthday = new Birthday(startMonthDate);
        Birthday endMonthBirthday = new Birthday(endMonthDate);

        //Check for All Time
        assertTrue(tomorrowBirthday.isInPeriod(DateKeyword.ALL_TIME, testDate));

        //Check for tomorrow
        assertTrue(tomorrowBirthday.isInPeriod(DateKeyword.TOMORROW, testDate));

        //Check for week
        assertTrue(tomorrowBirthday.isInPeriod(DateKeyword.THIS_WEEK, testDate));
        assertTrue(startWeekBirthday.isInPeriod(DateKeyword.THIS_WEEK, testDate));
        assertTrue(endWeekBirthday.isInPeriod(DateKeyword.THIS_WEEK, testDate));

        //Check for month
        assertTrue(tomorrowBirthday.isInPeriod(DateKeyword.THIS_MONTH, testDate));
        assertTrue(endWeekBirthday.isInPeriod(DateKeyword.THIS_MONTH, testDate));
        assertTrue(startMonthBirthday.isInPeriod(DateKeyword.THIS_MONTH, testDate));
        assertTrue(endMonthBirthday.isInPeriod(DateKeyword.THIS_MONTH, testDate));

        //Span between 2 months
        LocalDate testSpanDate = LocalDate.of(2022, 1, 31);
        LocalDate tomorrowSpanDate = LocalDate.of(2022, 2, 1);
        LocalDate endWeekSpanDate = LocalDate.of(2022, 2, 6);

        Birthday tomorrowSpanBirthday = new Birthday(tomorrowSpanDate);
        Birthday endWeekSpanBirthday = new Birthday(endWeekSpanDate);

        //Check for tomorrow
        assertTrue(tomorrowSpanBirthday.isInPeriod(DateKeyword.TOMORROW, testSpanDate));

        //Check for week
        assertTrue(tomorrowSpanBirthday.isInPeriod(DateKeyword.THIS_WEEK, testSpanDate));
        assertTrue(endWeekSpanBirthday.isInPeriod(DateKeyword.THIS_WEEK, testSpanDate));

        //Check for leap date
        LocalDate testLeapDate = LocalDate.of(2020, 2, 28);
        LocalDate tomorrowLeapDate = LocalDate.of(2020, 2, 29);
        Birthday tomorrowLeapBirthday = new Birthday(tomorrowLeapDate);
        assertTrue(tomorrowLeapBirthday.isInPeriod(DateKeyword.TOMORROW, testLeapDate));
    }

    @Test
    public void isInPeriod_invalidBirthday_failure() {
        LocalDate testDate = LocalDate.of(2022, 1, 11);
        LocalDate beforeTestDate = LocalDate.of(2022, 1, 10);
        LocalDate afterTomorrowDate = LocalDate.of(2022, 1, 13);
        LocalDate afterEndWeekDate = LocalDate.of(2022, 1, 19);
        LocalDate beforeStartMonthDate = LocalDate.of(2021, 12, 31);
        LocalDate afterBeforeMonthDate = LocalDate.of(2022, 2, 1);

        Birthday beforeTodayBirthday = new Birthday(beforeTestDate);
        Birthday beforeTomorrowBirthday = new Birthday(testDate);
        Birthday afterTomorrowBirthday = new Birthday(afterTomorrowDate);
        Birthday afterEndWeekBirthday = new Birthday(afterEndWeekDate);
        Birthday beforeStartMonthBirthday = new Birthday(beforeStartMonthDate);
        Birthday afterEndMonthBirthday = new Birthday(afterBeforeMonthDate);

        //Check for tomorrow
        assertFalse(beforeTomorrowBirthday.isInPeriod(DateKeyword.TOMORROW, testDate));
        assertFalse(afterTomorrowBirthday.isInPeriod(DateKeyword.TOMORROW, testDate));

        //Check for week
        assertFalse(beforeTodayBirthday.isInPeriod(DateKeyword.THIS_WEEK, testDate));
        assertFalse(afterEndWeekBirthday.isInPeriod(DateKeyword.THIS_WEEK, testDate));

        //Check for month
        assertFalse(beforeStartMonthBirthday.isInPeriod(DateKeyword.THIS_MONTH, testDate));
        assertFalse(afterEndMonthBirthday.isInPeriod(DateKeyword.THIS_MONTH, testDate));
    }
}
