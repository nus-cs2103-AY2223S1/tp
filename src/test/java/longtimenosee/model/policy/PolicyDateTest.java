package longtimenosee.model.policy;

import static longtimenosee.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import longtimenosee.model.person.Phone;

public class PolicyDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void isValidFormat() {
        // EP: null
        assertThrows(NullPointerException.class, () -> PolicyDate.isValidFormat(null));

        //EP: All alphabets
        assertFalse(PolicyDate.isValidFormat("YYYY-MM-DD"));
        //EP: Invalid Year, Valid Month and Day
        assertFalse(PolicyDate.isValidFormat("199-01-01"));
        //EP: Valid Year, Invalid Month, Valid Day
        assertFalse(PolicyDate.isValidFormat("1990-1-01"));
        //EP: Valid Year, Valid Month, Invalid Day
        assertFalse(PolicyDate.isValidFormat("1990-01-1"));
    }
    /**
     * Tests for non format issues, but invalid dates
     */
    @Test
    public void isValidValues() {
        //EP: Invalid dates with correct format (invalid values)
        assertFalse(PolicyDate.isValidDate("2019-02-29")); //Leap year day on non leap-year
        assertFalse(PolicyDate.isValidDate("2000-13-01")); //Invalid month
        assertFalse(PolicyDate.isValidDate("2000-12-32")); //Invalid day
        assertFalse(PolicyDate.isValidDate("1899-12-31")); //Invalid year (before 1900)
        assertFalse(PolicyDate.isValidDate("2101-12-31")); //Invalid year (after 2100)

        //Edge Case: Valid leap years
        assertTrue(PolicyDate.isValidDate("2020-02-29")); //Leap year day on non leap-year

    }
}
