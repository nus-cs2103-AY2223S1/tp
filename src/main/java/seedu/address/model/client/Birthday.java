package seedu.address.model.client;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a client's birthday in MyInsuRec.
 */
public class Birthday {

    public static final String VALIDATION_REGEX = "^([0-2][0-9]||3[0-1])(0[0-9]||1[0-2])([0-9][0-9])?[0-9][0-9]$";
    public static final String MESSAGE_CONSTRAINTS =
            "Birthday should be in the format DDMMYYYY";
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

    public static boolean isValidBirthday(String test) {
        return test.matches(VALIDATION_REGEX) | test.equals("");
    }
}
