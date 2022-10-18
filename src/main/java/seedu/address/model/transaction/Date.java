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


    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in the format DD/MM/YYYY";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    public final String date;

    /**
     * Constructs a {@code Date}.
     *
     * @param dateTime A valid goods name.
     */
    public Date(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDate(dateTime), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(dateTime, dtf).toString();
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, dtf);
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
        return date;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && date.equals(((Date) other).date)); // state check
    }
}
