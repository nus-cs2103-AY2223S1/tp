package seedu.address.testutil;

/**
 * A utility class containing a list of date and times in {@code String} format to be used in tests.
 */
public class TypicalDateTimes {
    // Dates which follow the format accepted
    public static final String FIRST_VALID_DATE = "23 Oct 2022"; // d MMM uuuu
    public static final String SECOND_VALID_DATE = "23 Oct"; // d MMM
    public static final String THIRD_VALID_DATE = "23/10/2022"; // d/M/uuuu
    public static final String FOURTH_VALID_DATE = "23/10"; // d/M
    public static final String FIFTH_VALID_DATE = "29/02/2024"; // 29 Feb on leap year

    // Dates which may exist but considered invalid by parser
    public static final String FIRST_INVALID_DATE = "23 October 2022"; // only MMM or M allowed for month
    public static final String SECOND_INVALID_DATE = "23 Oct 22"; // only uuuu for year
    public static final String THIRD_INVALID_DATE = "29 Feb 2023"; // 29 Feb on non-leap years

    // Dates that will never exist
    public static final String FIRST_NONSTANDARD_DATE = "30 Feb";
    public static final String SECOND_NONSTANDARD_DATE = "0 Jan";

    // Times which follow the format accepted
    public static final String FIRST_VALID_TIME = "20:00"; // HH:mm
    public static final String SECOND_VALID_TIME = "12:00 pm"; // h:mm a

    // Times which may exist but considered invalid by parser
    public static final String FIRST_INVALID_TIME = "2000"; // colon missing from HH:mm format
    public static final String SECOND_INVALID_TIME = "3 pm"; // :mm missing from H:mm a format
    public static final String THIRD_INVALID_TIME = "3:00pm"; // space missing from H:mm a format
}
