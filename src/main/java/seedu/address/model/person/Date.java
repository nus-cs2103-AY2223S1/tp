package seedu.address.model.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Date {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in dd-mm-yyyy format";

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public final LocalDate value;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date in string format of dd-mm-yyyy.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.value = LocalDate.parse(date, FORMATTER);
    }

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(LocalDate date) {
        this.value = date;
    }

    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
