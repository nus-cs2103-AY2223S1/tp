package gim.model.date;

import static gim.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateTest {

    /**
     * Date constructor does not accept null as its value. When no
     * argument is provided to the constructor, the value is set by
     * default as today's date.
     */
    @Test
    public void constructor_default_setAsDateToday() {
        assertEquals((new Date()).date, LocalDate.now());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    /**
     * Test for invalid date where the formatting does not conform to dd/MM/uuuu format.
     */
    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDateEmptyString = "";
        String invalidDateWhitespaces = " ";
        String invalidDateFormatOne = "5/5/2022";
        String invalidDateFormatTwo = "05/5/2022";
        String invalidDateFormatThree = "5/05/2022";
        String invalidDateFormatFour = "05/05/22";
        String invalidDateFormatFive = "31-04-2022";
        String invalidDateFormatSix = "2022/02/02";
        String invalidDateFormatSeven = "20 October 2022";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateEmptyString));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateWhitespaces));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateFormatOne));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateFormatTwo));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateFormatThree));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateFormatFour));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateFormatFive));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateFormatSix));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateFormatSeven));
    }

    /**
     * Test for invalid date where the date is non-existent, but is not covered by the regex.
     * Example: 31st February 2022 is a non-existent date.
     */
    @Test
    public void constructor_nonExistentDate_throwsDateTimeParseException() {
        String nonExistentDate = "31/02/2022";
        assertThrows(DateTimeParseException.class, () -> new Date(nonExistentDate));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDateByRegex(null));
    }

}
