package gim.model.date;

import static gim.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

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
     * Tests for invalid date where the formatting does not conform to dd/MM/uuuu format.
     */

    @Test
    public void constructor_invalidDateOne_throwsIllegalArgumentExceptionOne() {
        String invalidDateFormatOne = "005/5/2022";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateFormatOne));
    }

    @Test
    public void constructor_invalidDateTwo_throwsIllegalArgumentException() {
        String invalidDateFormatTwo = "05/005/2022";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateFormatTwo));
    }

    @Test
    public void constructor_invalidDateThree_throwsIllegalArgumentException() {
        String invalidDateFormatThree = "5/05/20220";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateFormatThree));
    }

    @Test
    public void constructor_invalidDateFour_throwsIllegalArgumentException() {
        String invalidDateFormatFour = "20 October 2022";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateFormatFour));
    }

    @Test
    public void constructor_invalidDateFive_throwsIllegalArgumentException() {
        String invalidDateFormatFive = "32/01/2022";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateFormatFive));
    }

    @Test
    public void constructor_invalidDateWhitespaces_throwsIllegalArgumentException() {
        String invalidDateWhitespaces = "     ";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateWhitespaces));
    }

    @Test
    public void constructor_invalidDateEmptyString_throwsIllegalArgumentException() {
        String invalidDateEmptyString = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateEmptyString));
    }


    /**
     * Test for invalid date where the date is non-existent, but is not covered by the regex.
     * Example: 31st February 2022 is a non-existent date.
     */

    @Test
    public void constructor_nonExistentDateOne_throwsIllegalArgumentException() {
        String nonExistentDateOne = "31/02/2022";
        assertThrows(IllegalArgumentException.class, () -> new Date(nonExistentDateOne));
    }

    @Test
    public void constructor_nonExistentDateTwo_throwsIllegalArgumentException() {
        String nonExistentDateTwo = "29/02/2022"; // 2022 is a non-leap year
        assertThrows(IllegalArgumentException.class, () -> new Date(nonExistentDateTwo));
    }

    @Test
    public void constructor_nonExistentDateThree_throwsIllegalArgumentException() {
        String nonExistentDateThree = "31/04/2022";
        assertThrows(IllegalArgumentException.class, () -> new Date(nonExistentDateThree));
    }

    @Test
    public void constructor_validLeapYearDate_success() {
        String validDate = "29/02/2020"; // 2020 is a leap year
        assertEquals(new Date(validDate), new Date(validDate)); // no DateTimeParseException should be thrown
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDateByRegex(null));
    }

}
