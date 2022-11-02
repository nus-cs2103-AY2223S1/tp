package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * Represents a date (can be nurse's unavailable date or fully-scheduled date).
 * Guarantees: immutable;
 * Valid as declared in {@link #isValidDateFormat(String)} and {@link #isValidDate(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS = "Date should be in YYYY-MM-DD."
            + " For example, 2022-11-11";
    public static final String MESSAGE_CONSTRAINTS_VALID_DATE = "Please input a valid date for the month"
            + " For example, 2022-02-28 \n"
            + "January, March, May, July, August, October, December have 31 days \n"
            + "April, June, September, November have 30 days \n"
            + "February has 28 or 29 days depending if it is a leap year.";

    public static final String VALIDATION_REGEX = "(20[0-9][0-9])-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])";

    public final LocalDate date;
    private final String dateInString;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDateFormat(date), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS_VALID_DATE);
        this.date = LocalDate.parse(date);
        this.dateInString = date;
    }

    /**
     * Constructs a {@code Date}.
     */
    public Date(LocalDate date) {
        requireNonNull(date);
        this.date = date;
        this.dateInString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns true if a given string is a valid date format.
     */
    public static boolean isValidDateFormat(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String fullDate) {
        String[] dateArr = fullDate.split("-"); // splits to [year,month,date] eg.["2022","01","31"]
        int year = Integer.parseInt(dateArr[0]);
        int monthInInt = Integer.parseInt(dateArr[1]);
        Month month = Month.of(monthInInt);
        int date = Integer.parseInt(dateArr[2]); //date
        boolean isLeapYear = year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
        return date <= month.length(isLeapYear);
    }

    /**
     * Returns true if a given year is a leap year.
     */
    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    public String getString() {
        return this.dateInString;
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                        && date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    public LocalDate getDate() {
        return this.date;
    }

}
