package longtimenosee.model.person;

import static longtimenosee.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class BirthdayTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void isValidFormat() {
        // EP: null
        assertThrows(NullPointerException.class, () -> Birthday.isValidFormat(null));

        //EP: All alphabets
        assertFalse(Birthday.isValidFormat("YYYY-MM-DD"));
        //EP: Invalid Year, Valid Month and Day
        assertFalse(Birthday.isValidFormat("199-01-01"));
        //EP: Valid Year, Invalid Month, Valid Day
        assertFalse(Birthday.isValidFormat("1990-1-01"));
        //EP: Valid Year, Valid Month, Invalid Day
        assertFalse(Birthday.isValidFormat("1990-01-1"));
    }
    /**
     * Tests for non format issues, but invalid dates
     */
    @Test
    public void isValidValues() {
        //EP: Invalid dates with correct format (invalid values)
        assertFalse(Birthday.isValidDate("2019-02-29")); //Leap year day on non leap-year
        assertFalse(Birthday.isValidDate("2000-13-01")); //Invalid month
        assertFalse(Birthday.isValidDate("2000-13-31")); //Invalid day
        //Edge Case: Valid leap years
        assertTrue(Birthday.isValidDate("2020-02-29")); //Leap year day on non leap-year

    }
}
