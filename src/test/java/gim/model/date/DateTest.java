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
     * Test for invalid date where the formatting does not conform to dd/MM/uuuu format.
     */
    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDateEmptyString = "";
        String invalidDateWhitespaces = "     ";
        String invalidDateFormatOne = "005/5/2022";
        String invalidDateFormatTwo = "05/005/2022";
        String invalidDateFormatThree = "5/05/20220";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateEmptyString));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateWhitespaces));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateFormatOne));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateFormatTwo));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateFormatThree));
    }

    /**
     * Test for invalid date where the date is non-existent, but is not covered by the regex.
     * Example: 31st February 2022 is a non-existent date.
     */
    @Test
    public void constructor_nonExistentDate_throwsIllegalArgumentException() {
        String nonExistentDate = "31/02/2022";
        assertThrows(IllegalArgumentException.class, () -> new Date(nonExistentDate));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDateByRegex(null));
    }

}
