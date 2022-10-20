package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Professor's office hour in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidOfficeHour(String)}
 */
public class OfficeHour {

    public static final String MESSAGE_CONSTRAINTS = "Office hour consist of:\n"
            + "- integer representation of weekday (from monday[1]- friday[5])\n"
            + "- time in HH:mm military time format (16:00 represents 4 pm)\n"
            + "- duration specified in integer (1 - 9)";

    /*
     * The length of the OfficeHour must be specifically 9 char.
     * Day specified must be from (1 - 5) matching (monday - friday)
     * Start time must be specified in HH:mm
     * Duration must be specified as an integer (1 - 9)
     */
    public static final String VALIDATION_REGEX =
            "^(?=\\S{9}$)([1-5]{1})+-+(([0-1]{1}\\d{1})|([2]{1}[0-3]{1}))+:+([0-5]{1}\\d{1}-\\d{1})$";


    public final String value;

    /**
     * Constructs an {@code OfficeHour}.
     *
     * @param officeHour A valid officeHour.
     */
    public OfficeHour(String officeHour) {
        requireNonNull(officeHour);
        this.value = officeHour;
    }

    /**
     * Returns true if a given string is a valid office hour.
     * @param test target string
     * @return true if test is a valid officeHour
     */
    public static boolean isValidOfficeHour(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof OfficeHour
                && value.equals(((OfficeHour) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
