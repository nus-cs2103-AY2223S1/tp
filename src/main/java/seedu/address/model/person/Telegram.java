package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Telegram username in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTele(String)}
 */
public class Telegram {

    public static final String MESSAGE_CONSTRAINTS = "Telegram usernames can take any values, "
            + "and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String telehandle;

    /**
     * Constructs a {@code Telegram}.
     *
     * @param s A valid Telegram username.
     */
    public Telegram(String s) {
        requireNonNull(s);
        checkArgument(isValidTele(s), MESSAGE_CONSTRAINTS);
        telehandle = s;
    }

    /**
     * Returns true if a given string is a valid GitHub profile type (username or website link).
     */
    public static boolean isValidTele(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return telehandle;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && telehandle.equals(((Telegram) other).telehandle)); // state check
    }

    @Override
    public int hashCode() {
        return telehandle.hashCode();
    }

}

