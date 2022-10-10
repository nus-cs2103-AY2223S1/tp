package seedu.address.model.item.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemExpiryDateValidatorTest {

    /**
     * Checks for valid date string formatting.
     */
    @Test
    public void isValidFormat() {
        Assertions.assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("01-01-2000"));

        // Delimiters
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("01/01/2000")); // Wrong delimiter 1
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("01.01.2000")); // Wrong delimiter 2
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("01012000")); // Missing delimiter 1
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("01-012000")); // Missing delimiter 2
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("0101-2000")); // Missing delimiter 3

        // Whitespace
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("01 01 2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime(" 01-01-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("01-01-2000 "));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime(" 01-01-2000 "));

        // Wrong Datetime Format
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("1-01-2000")); // Wrong Day
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("01-1-2000")); // Wrong Month
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("01-01-200")); // Wrong Year
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("01-01-00"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("01-01-0"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("-01-2000")); // Missing Day
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("01--2000")); // Missing Month
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("01-01-")); // Missing Year

        // Wrong characters
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime(""));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("a"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("01-01-a"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("01-01-á"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("01-01-你"));
    }


    /**
     * Checks if dates are within valid bounds.
     */
    @Test
    public void isDateWithinValidBounds() {
        // Year Format: dd-MM-YYYY
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Test Year
        assertFalse(ItemExpiryDateValidator.isYearLessThanMinYear(LocalDate.parse("01-01-1900", formatter)));
        assertFalse(ItemExpiryDateValidator.isYearMoreThanMaxYear(LocalDate.parse("01-01-2300", formatter)));
        assertTrue(ItemExpiryDateValidator.isYearLessThanMinYear(LocalDate.parse("01-01-1899", formatter)));
        assertTrue(ItemExpiryDateValidator.isYearMoreThanMaxYear(LocalDate.parse("01-01-2301", formatter)));

        // Test Month
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("01-01-2000")); // Lower Bound
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("01-12-2000")); // Upper Bound
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("01-06-2000")); // Middle
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("01-00-2000")); // Below Lower Bound
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("01-13-2000")); // Above Upper Bound

        // Test Days - Lower Bound
        // Positive Cases
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("01-01-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("01-02-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("01-03-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("01-04-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("01-05-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("01-06-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("01-07-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("01-08-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("01-09-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("01-10-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("01-11-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("01-12-2000"));

        // Negative Cases
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("00-01-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("00-02-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("00-03-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("00-04-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("00-05-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("00-06-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("00-07-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("00-08-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("00-09-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("00-10-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("00-11-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("00-12-2000"));

        // Test Days - Upper Bound
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("31-01-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("28-02-2001")); // February Non-Leap Year
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("29-02-2004")); // February Leap Year
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("31-03-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("30-04-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("31-05-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("30-06-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("31-07-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("31-08-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("30-09-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("31-10-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("30-11-2000"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("31-12-2000"));

        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("32-01-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("29-02-2001")); // February Non-Leap Year
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("32-03-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("31-04-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("32-05-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("31-06-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("32-07-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("32-08-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("31-09-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("32-10-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("31-11-2000"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("32-12-2000"));
    }
}
