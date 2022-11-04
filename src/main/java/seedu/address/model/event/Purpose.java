package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Event's purpose in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPurpose(String)}
 */
public class Purpose implements Comparable<Purpose> {

    public static final String MESSAGE_CONSTRAINTS =
            "Purpose should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String purpose;

    /**
     * Constructs a {@code Purpose}.
     *
     * @param purpose A valid purpose for the event.
     */
    public Purpose(String purpose) {
        requireNonNull(purpose);
        checkArgument(isValidPurpose(purpose), MESSAGE_CONSTRAINTS);
        this.purpose = purpose;
    }

    /**
     * Returns true if a given string is a valid purpose.
     */
    public static boolean isValidPurpose(String purposeToTest) {
        return purposeToTest.matches(VALIDATION_REGEX);
    }

    @Override
    public int compareTo(Purpose p) {
        return this.purpose.compareToIgnoreCase(p.purpose);
    }

    @Override
    public String toString() {
        return purpose;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Purpose // instanceof handles nulls
                && purpose.equals(((Purpose) other).purpose)); // state check
    }

    @Override
    public int hashCode() {
        return purpose.hashCode();
    }
}
