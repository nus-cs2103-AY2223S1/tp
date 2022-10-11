package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class Telegram {

    public static final String MESSAGE_CONSTRAINTS = "Telegram handle can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String handle;

    /**
     * Constructs an {@code Telegram}.
     *
     * @param handle A valid handle.
     */
    public Telegram(String handle) {
        requireNonNull(handle);
        checkArgument(isValidTelegram(handle), MESSAGE_CONSTRAINTS);
        this.handle = handle;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidTelegram(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return handle;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && handle.equals(((Telegram) other).handle)); // state check
    }

    @Override
    public int hashCode() {
        return handle.hashCode();
    }

}
