package seedu.address.model.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Customer's arrival in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidArrival(String)}
 */

// Adapted from https://github.com/nus-cs2103-AY2021S2/tp/pull/10/files

public class Arrival {

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in the format dd/mm/yyyy. For example, 1 July 2021 should be "
                    + "expressed as 01/07/2021, not 1/7/2021."
                    + "\n- Day, month or year should not be zeros."
                    + "\n- Date cannot be before year 1900 or after 2099.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^((0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/(19|20)\\d\\d)$";

    public final LocalDate value;

    /**
     * Constructs a {@code arrival}.
     *
     * @param arrival A valid arrival.
     */
    public Arrival(String arrival) {
        requireNonNull(arrival);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formatter = formatter.withLocale(Locale.ENGLISH);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDate date = LocalDate.parse(arrival, formatter);
        checkArgument(isValidArrival(arrival), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidArrival(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedString = value.format(formatter);
        return formattedString;
    }

    public String toDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedString = value.format(formatter);
        return formattedString;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Arrival // instanceof handles nulls
                && value.equals(((Arrival) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
