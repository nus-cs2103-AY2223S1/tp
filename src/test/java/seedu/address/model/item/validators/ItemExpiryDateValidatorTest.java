package seedu.address.model.item.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class ItemExpiryDateValidatorTest {

    /**
     * Checks for valid date string formatting.
     */
    @Test
    public void isValidFormat() {
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-01-01"));

        // Delimiters
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000/01/01")); // Wrong delimiter 1
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000.01.01")); // Wrong delimiter 2
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("20000101")); // Missing delimiter 1
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("200001-01")); // Missing delimiter 2
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-0101")); // Missing delimiter 3

        // Whitespace
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000 01 01"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime(" 2000-01-01"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-01-01 "));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime(" 2000-01-01 "));

        // Wrong Datetime Format
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-01-1")); // Wrong Day
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-1-01")); // Wrong Month
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("200-01-01")); // Wrong Year
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("00-01-01"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("0-01-01"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-01-")); // Missing Day
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000--01")); // Missing Month
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("-01-01")); // Missing Year

        // Wrong characters
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime(""));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("a"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("a-01-01"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-a-01"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-01-a"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("á-01-01"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-á-01"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-01-á"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("你-01-01"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-你-01"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-01-你"));
    }


    /**
     * Checks if dates are within valid bounds.
     */
    @Test
    public void isDateWithinValidBounds() {
        // Year Format: dd-MM-YYYY
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");

        // Test Year
        assertFalse(ItemExpiryDateValidator.isYearLessThanMinYear(LocalDate.parse("1900-01-01", formatter)));
        assertFalse(ItemExpiryDateValidator.isYearMoreThanMaxYear(LocalDate.parse("2300-01-01", formatter)));
        assertTrue(ItemExpiryDateValidator.isYearLessThanMinYear(LocalDate.parse("1899-01-01", formatter)));
        assertTrue(ItemExpiryDateValidator.isYearMoreThanMaxYear(LocalDate.parse("2301-01-01", formatter)));

        // Test Month
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-01-01")); // Lower Bound
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-12-01")); // Upper Bound
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-06-01")); // Middle
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-00-01")); // Below Lower Bound
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-13-01")); // Above Upper Bound

        // Test Days - Lower Bound
        // Positive Cases
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-01-01"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-02-01"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-03-01"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-04-01"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-05-01"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-06-01"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-07-01"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-08-01"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-09-01"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-10-01"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-11-01"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-12-01"));

        // Negative Cases
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-01-00"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-02-00"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-03-00"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-04-00"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-05-00"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-06-00"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-07-00"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-08-00"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-09-00"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-10-00"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-11-00"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-12-00"));

        // Test Days - Upper Bound
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-01-31"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2001-02-28")); // February Non-Leap Year
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2004-02-29")); // February Leap Year
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-03-31"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-04-30"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-05-31"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-06-30"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-07-31"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-08-31"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-09-30"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-10-31"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-11-30"));
        assertTrue(ItemExpiryDateValidator.isParsableItemDatetime("2000-12-31"));

        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-01-32"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2001-02-29")); // February Non-Leap Year
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2004-02-30")); // February Leap Year
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-03-32"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-04-31"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-05-32"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-06-31"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-07-32"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-08-32"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-09-31"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-10-32"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-11-31"));
        assertFalse(ItemExpiryDateValidator.isParsableItemDatetime("2000-12-32"));

    }
}
