package seedu.address.model.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import seedu.address.model.util.Date;

/**
 * Represents a client's birthday in MyInsuRec.
 */
public class Birthday extends Date {

    public static final String VALIDATION_REGEX = "^([0-2][0-9]||3[0-1])(0[0-9]||1[0-2])([0-9][0-9])?[0-9][0-9]$";
    public static final String MESSAGE_FORMAT_CONSTRAINTS =
            "Birthday should be in the format DDMMYYYY and should be a valid day of the year";
    public static final String MESSAGE_DATE_CONSTRAINTS =
            "Birthday should be at most 100 years before and no more than the date of creation";
    private static final int YEARS_BEFORE = 100;

    /**
     * Constructs a {@code birthdayDate}.
     *
     * @param birthday A valid birthday date.
     */
    public Birthday(LocalDate birthday) {
        super(birthday);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Birthday
                && date.equals(((Birthday) other).date));
    }

    /**
     * Returns a reader-friendly version of date.
     */
    public String formattedDate() {
        String birthdayFormatted;
        // returns date as '12 Jan 1952' for example
        birthdayFormatted = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        return birthdayFormatted;
    }

    public static boolean isValidBirthday(String test) {
        return test.matches(VALIDATION_REGEX) || test.equals("");
    }

    /**
     * Returns true if and only if the given date {@code birthday} is not after today
     * and not before {@code YEARS_BEFORE}.
     */
    public static boolean isDateInValidPeriod(Birthday birthday) {
        return isBeforePeriod(birthday, YEARS_BEFORE);
    }

    /**
     * Returns a new Birthday with the current year.
     */
    public Birthday upcomingBirthday() {
        int yearDifference = LocalDate.now().getYear() - date.getYear();
        return new Birthday(date.plusYears(yearDifference));
    }
}
