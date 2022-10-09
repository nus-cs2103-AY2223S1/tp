package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.model.item.itemvalidator.ItemBoughtDateValidator;

public class ItemBoughtDateValidatorTest {

    /**
     * Checks for valid date string formatting.
     */
    @Test
    public void isValidFormat() {
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("01-01-2000"));

        // Delimiters
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("01/01/2000")); // Wrong delimiter 1
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("01.01.2000")); // Wrong delimiter 2
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("01012000")); // Missing delimiter 1
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("01-012000")); // Missing delimiter 2
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("0101-2000")); // Missing delimiter 3

        // Whitespace
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("01 01 2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime(" 01-01-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("01-01-2000 "));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime(" 01-01-2000 "));

        // Wrong Datetime Format
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("1-01-2000")); // Wrong Day
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("01-1-2000")); // Wrong Month
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("01-01-200")); // Wrong Year
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("01-01-00"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("01-01-0"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("-01-2000")); // Missing Day
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("01--2000")); // Missing Month
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("01-01-")); // Missing Year

        // Wrong characters
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime(""));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("a"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("01-01-a"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("01-01-á"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("01-01-你"));
    }


    /**
     * Checks if dates are within valid bounds.
     */
    @Test
    public void isDateWithinValidBounds() {
        // Year Format: dd-MM-YYYY
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Test Year
        assertFalse(ItemBoughtDateValidator.isYearLessThanMinYear(LocalDate.parse("01-01-1900", formatter)));
        assertFalse(ItemBoughtDateValidator.isYearMoreThanMaxYear(LocalDate.parse("01-01-2300", formatter)));
        assertTrue(ItemBoughtDateValidator.isYearLessThanMinYear(LocalDate.parse("01-01-1899", formatter)));
        assertTrue(ItemBoughtDateValidator.isYearMoreThanMaxYear(LocalDate.parse("01-01-2301", formatter)));

        // Test Month
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("01-01-2000")); // Lower Bound
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("01-12-2000")); // Upper Bound
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("01-06-2000")); // Middle
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("01-00-2000")); // Below Lower Bound
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("01-13-2000")); // Above Upper Bound

        // Test Days - Lower Bound
        // Positive Cases
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("01-01-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("01-02-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("01-03-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("01-04-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("01-05-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("01-06-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("01-07-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("01-08-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("01-09-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("01-10-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("01-11-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("01-12-2000"));

        // Negative Cases
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("00-01-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("00-02-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("00-03-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("00-04-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("00-05-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("00-06-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("00-07-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("00-08-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("00-09-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("00-10-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("00-11-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("00-12-2000"));

        // Test Days - Upper Bound
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("31-01-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("28-02-2001")); // February Non-Leap Year
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("29-02-2004")); // February Leap Year
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("31-03-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("30-04-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("31-05-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("30-06-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("31-07-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("31-08-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("30-09-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("31-10-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("30-11-2000"));
        assertTrue(ItemBoughtDateValidator.isParsableItemDatetime("31-12-2000"));

        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("32-01-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("29-02-2001")); // February Non-Leap Year
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("32-03-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("31-04-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("32-05-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("31-06-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("32-07-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("32-08-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("31-09-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("32-10-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("31-11-2000"));
        assertFalse(ItemBoughtDateValidator.isParsableItemDatetime("32-12-2000"));
    }
}
