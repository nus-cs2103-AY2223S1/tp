package seedu.address.model.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a client's birthday in MyInsuRec.
 */
public class Birthday {

    public static final String MESSAGE_CONSTRAINTS =
            "Birthday should be in the format DDMMYYYY";
    private final LocalDate birthday;

    /**
     * Constructs a {@code birthdayDate}.
     *
     * @param birthday A valid birthday date.
     */
    public Birthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        if (birthday == null) {
            return "";
        }
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
     * @return
     */
    public String formattedDate() {
        String birthdayFormatted;
        if (birthday == null) {
            birthdayFormatted = "";
        } else {
            // returns date as '12 Jan 1952' for example
            birthdayFormatted = birthday.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        }
        return birthdayFormatted;
    }
}
