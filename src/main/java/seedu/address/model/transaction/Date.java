package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class to store the date of transaction.
 */
public class Date {

    public static final DateTimeFormatter DEFAULT_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter NEW_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter DISPLAY_PATTERN = DateTimeFormatter.ofPattern("d MMM yyyy");

    public static final String MESSAGE_CONSTRAINTS_WRONG_FORMAT =
            "Date should contain numbers in the format DD/MM/YYYY";

    public static final String MESSAGE_CONSTRAINTS_WRONG_DATE = "Date inputted is not a valid date.";

    private static final String VALIDATION_REGEX = "[\\d\\/]*";


    public final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDateFormat(date), MESSAGE_CONSTRAINTS_WRONG_FORMAT);
        checkArgument(isValidDateInput(date), MESSAGE_CONSTRAINTS_WRONG_DATE);
        this.date = LocalDate.parse(date, NEW_PATTERN);
    }

    /**
     * Returns true if a given string is a valid input.
     */
    public static boolean isValidDateFormat(String test) {
        requireNonNull(test);
        return test.matches(VALIDATION_REGEX);
    }


    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDateInput(String test) {
        requireNonNull(test);
        try {
            LocalDate.parse(test, NEW_PATTERN);
            String[] testArr = test.split("/");
            int day = Integer.parseInt(testArr[0]);
            int month = Integer.parseInt(testArr[1]);
            int year = Integer.parseInt(testArr[2]);
            // Febuary
            if (month == 2) {
                if (day == 29) {
                    return (year % 4) == 0;
                }
                return day < 29;
            }
            // April, June, September, November
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                return day < 31;
            }

            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return date.format(DISPLAY_PATTERN);
    }

    public String getDateInDdMmYyyy() {
        return date.format(NEW_PATTERN);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && date.equals(((Date) other).date)); // state check
    }

    public LocalDate getLocalDate() {
        return this.date;
    }


    /**
     * Compares current transaction's date with another date.
     * @param date Another chosen date.
     * @return True if current date is older than other date.
     */
    public boolean isOlderThan(Date date) {
        return this.date.isBefore(date.date);
    }
}
