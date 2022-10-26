package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.parser.ArgumentMultimap.DOES_NOT_EXIST;

/**
 * Represents a Person's Telegram username in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTele(String)}
 */
public class Telegram {

    public static final String MESSAGE_CONSTRAINTS = "Telegram usernames can take any values, `"
            + "and it should only be one word";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s]+";

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
        if (test.equals(DOES_NOT_EXIST)) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return telehandle.equals(DOES_NOT_EXIST) ? telehandle : "@" + telehandle;
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

