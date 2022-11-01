package seedu.address.model.client;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import seedu.address.logic.parser.DateKeyword;

/**
 * Represents a client's birthday in MyInsuRec.
 */
public class Birthday {

    public static final String VALIDATION_REGEX = "^([0-2][0-9]||3[0-1])(0[0-9]||1[0-2])([0-9][0-9])?[0-9][0-9]$";
    public static final String MESSAGE_FORMAT_CONSTRAINTS =
            "Birthday should be in the format DDMMYYYY and should be a valid day of the year";
    public static final String MESSAGE_DATE_CONSTRAINTS =
            "Birthday should be at most 100 years before and no more than the date of creation";
    private static final int yearsBefore = 100;
    private final LocalDate birthday;

    /**
     * Constructs a {@code birthdayDate}.
     *
     * @param birthday A valid birthday date.
     */
    public Birthday(LocalDate birthday) {
        requireNonNull(birthday);
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return birthday.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Birthday
                && birthday.equals(((Birthday) other).birthday));
    }

    /**
     * Returns a reader-friendly version of date.
     */
    public String formattedDate() {
        String birthdayFormatted;
        // returns date as '12 Jan 1952' for example
        birthdayFormatted = birthday.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        return birthdayFormatted;
    }

    /**
     * Returns true if birthday fall between the given period {@code dateKeyword} else returns false.
     */
    public boolean isBirthdayInPeriod(DateKeyword dateKeyword) {
        Birthday startDate;
        Birthday endDate;
        LocalDate today = LocalDate.now();

        Birthday upcomingBirthday = new Birthday(
                LocalDate.of(today.getYear(), birthday.getMonthValue(), birthday.getDayOfMonth()));

        switch(dateKeyword) {
        case TOMORROW:
            startDate = new Birthday(today.plusDays(1));
            endDate = new Birthday(today.plusDays(1));
            break;
        case THIS_MONTH:
            startDate = new Birthday(today.withDayOfMonth(1));
            endDate = new Birthday(today.withDayOfMonth(today.getMonth().length(today.isLeapYear())));
            break;
        case THIS_WEEK:
            startDate = new Birthday(today);
            endDate = new Birthday(today.plusDays(7));
            break;
        default:
            startDate = null;
            endDate = null;
        }
        requireNonNull(startDate);
        requireNonNull(endDate);

        return upcomingBirthday.isBeforeDate(endDate) && upcomingBirthday.isAfterDate(startDate);

    }

    private boolean isBeforeDate(Birthday other) {
        return birthday.compareTo(other.birthday) <= 0;
    }

    private boolean isAfterDate(Birthday other) {
        return birthday.compareTo(other.birthday) >= 0;
    }

    public static boolean isValidBirthday(String test) {
        return test.matches(VALIDATION_REGEX) || test.equals("");
    }

    public static boolean isDateInValidPeriod(LocalDate date) {
        return !date.isAfter(LocalDate.now()) && !date.isBefore(LocalDate.now().minusYears(yearsBefore));
    }
}
