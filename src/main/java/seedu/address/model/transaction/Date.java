package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to store the date of transaction.
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in the format DD/MM/YYYY";

    public final String date;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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

        String[] testArr = test.split("/");

        try {
            Integer.parseInt(testArr[0]);
            Integer.parseInt(testArr[1]);
            Integer.parseInt(testArr[2]);
        } catch (NumberFormatException e) {
            return false;
        }
        if (testArr.length < 3) {
            return false;
        }

        if (testArr[0].length() != 2 || testArr[1].length() != 2 || testArr[2].length() != 4) {
            return false;
        }

        if (Integer.parseInt(testArr[0]) <= 0 || Integer.parseInt(testArr[1]) <= 0
                || Integer.parseInt(testArr[2]) <= 0) {
            return false;
        }

        if (Integer.parseInt(testArr[0]) > 31 || Integer.parseInt(testArr[1]) > 12) {
            return false;
        }

        return true;
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
