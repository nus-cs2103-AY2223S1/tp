package seedu.address.model.tutorial;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the group number of a tutorial in SETA
 */
public class Time {

    public static final String MESSAGE_CONSTRAINTS =
            "time should only take certain format, and it should not be blank";

    /*
     * The first character of the time must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    //public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
//    public static final String VALIDATION_REGEX = "[^\\s].*";
    // I still don't understand what it means right now, and I will find out and change it later.

    public final LocalDateTime dateTime;

    /**
     * Constructs a {@code time}.
     *
     * @param dateTime A valid time.
     */
    public Time(String dateTime) {
        requireNonNull(dateTime);

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.dateTime = LocalDateTime.parse(dateTime);
        } catch (DateTimeParseException e) {
            checkArgument(false, MESSAGE_CONSTRAINTS);
        }
    }

//    /**
//     * Returns true if a given string is a valid time.
//     */
//    public static boolean isValidTime(String test) {
//        return test.matches(VALIDATION_REGEX);
//    }


    @Override
    public String toString() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyy h:mma"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && dateTime.equals(((Time) other).dateTime)); // state check
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }
}
