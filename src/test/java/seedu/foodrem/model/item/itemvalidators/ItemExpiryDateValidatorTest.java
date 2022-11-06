package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.model.item.itemvalidators.ItemValidatorUtilTest.assertValidateFailure;

import org.junit.jupiter.api.Test;

import seedu.foodrem.testutil.MessageToUser;

/**
 * A class to test the ItemExpiryDateValidator.
 */
public class ItemExpiryDateValidatorTest {
    /**
     * Checks for valid date string formatting.
     */
    @Test
    public void isValidFormat() {
        ItemExpiryDateValidator.validate("01-01-2000");

        // Wrong Delimiters
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01/01/2000"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01.01.2000"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01012000"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01-012000"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("0101-2000"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);

        // Whitespace
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01 01 2000"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate(" 01-01-2000"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("2000-01-01 "),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate(" 01-01-2000 "),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);

        // Wrong Datetime Formats
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("1-01-2000"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE); // Wrong Day
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01-1-2000"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE); // Wrong Month
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01-01-200"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE); // Wrong Year
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01-01-00"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01-01-0"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01-01-0"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("-01-2000"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE); // Missing Day
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01--2000"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE); // Missing Month
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01-01-"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE); // Missing Year
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("2000-01-01"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE); // Reversed Datetime Format

        // Wrong characters
        assertValidateFailure(() -> ItemExpiryDateValidator.validate(""),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("a"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01-01-a"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01-a-2000"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01-01-a"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01-01-á"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01-á-2000"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("á-01-2000"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01-01-你"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01-你-2000"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("你-01-2000"),
                MessageToUser.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);
    }

    /**
     * Checks if dates are within valid bounds.
     */
    @Test
    public void isDateWithinValidBounds() {
        // Test Year
        ItemExpiryDateValidator.validate("01-01-1900"); // Lower Bound
        ItemExpiryDateValidator.validate("01-01-2300"); // Upper Bound
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01-01-1899"),
                MessageToUser.MESSAGE_FOR_EXPIRY_DATE_YEAR_TOO_SMALL); // Below Lower Bound
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01-01-2301"),
                MessageToUser.MESSAGE_FOR_EXPIRY_DATE_YEAR_TOO_LARGE); // Above Upper Bound

        // Test Month
        ItemExpiryDateValidator.validate("01-01-2000"); // Lower Bound
        ItemExpiryDateValidator.validate("01-12-2000"); // Upper Bound
        ItemExpiryDateValidator.validate("01-06-2000"); // Middle
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01-00-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE); // Below Lower Bound
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("01-13-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE); // Above Upper Bound

        // Test Days - Lower Bound
        // Positive Cases
        ItemExpiryDateValidator.validate("01-01-2000");
        ItemExpiryDateValidator.validate("01-02-2000");
        ItemExpiryDateValidator.validate("01-03-2000");
        ItemExpiryDateValidator.validate("01-04-2000");
        ItemExpiryDateValidator.validate("01-05-2000");
        ItemExpiryDateValidator.validate("01-06-2000");
        ItemExpiryDateValidator.validate("01-07-2000");
        ItemExpiryDateValidator.validate("01-08-2000");
        ItemExpiryDateValidator.validate("01-09-2000");
        ItemExpiryDateValidator.validate("01-10-2000");
        ItemExpiryDateValidator.validate("01-11-2000");
        ItemExpiryDateValidator.validate("01-12-2000");

        // Negative Cases
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("00-01-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("00-02-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("00-03-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("00-04-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("00-05-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("00-06-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("00-07-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("00-08-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("00-09-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("00-10-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("00-11-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("00-12-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);

        // Test Days - Upper Bound
        ItemExpiryDateValidator.validate("31-01-2000");
        ItemExpiryDateValidator.validate("28-02-2001"); // February Non-Leap Year
        ItemExpiryDateValidator.validate("29-02-2004"); // February Leap Year
        ItemExpiryDateValidator.validate("31-03-2000");
        ItemExpiryDateValidator.validate("30-04-2000");
        ItemExpiryDateValidator.validate("31-05-2000");
        ItemExpiryDateValidator.validate("30-06-2000");
        ItemExpiryDateValidator.validate("31-07-2000");
        ItemExpiryDateValidator.validate("31-08-2000");
        ItemExpiryDateValidator.validate("30-09-2000");
        ItemExpiryDateValidator.validate("31-10-2000");
        ItemExpiryDateValidator.validate("30-11-2000");
        ItemExpiryDateValidator.validate("31-12-2000");

        assertValidateFailure(() -> ItemExpiryDateValidator.validate("32-01-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("29-02-2001"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE); // February Non-Leap Year
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("30-02-2004"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE); // February Leap Year
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("32-03-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("31-04-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("32-05-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("31-06-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("32-07-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("32-08-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("31-09-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("32-10-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("31-11-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);
        assertValidateFailure(() -> ItemExpiryDateValidator.validate("32-12-2000"),
                MessageToUser.MESSAGE_FOR_NON_EXISTENT_PARSE_EXPIRY_DATE);

    }
}
