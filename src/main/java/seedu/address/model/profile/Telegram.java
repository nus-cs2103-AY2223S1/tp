package seedu.address.model.profile;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Profile's telegram username in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class Telegram {

    public static final String MESSAGE_CONSTRAINTS = "Telegram usernames should be of the format @username "
            + "and adhere to the following constraints:\n"
            + "1. The username should be at least 5 characters and at most 40 characters long.\n"
            + "2. The username should start with an alphabet but can contain alphanumeric characters and underscores.\n"
            + "3. It cannot contain consecutive underscores or end with an underscore.\n"
            + "Usernames are case-insensitive, but your capitalization preferences will be stored.";
    // regex adapted from
    // https://github.com/AY2122S2-CS2103T-W09-1/tp/blob/master/src/main/java/seedu/address/model/person/Telegram.java
    public static final String VALIDATION_REGEX = "^@+[a-zA-Z](?:[a-zA-Z0-9]|_(?=[a-zA-Z0-9])){4,39}$";
    public static final Telegram EMPTY_TELEGRAM = new Telegram("");

    public final String username;

    /**
     * Constructs a {@code Telegram}.
     *
     * @param username A valid Telegram username.
     */
    public Telegram(String username) {
        requireNonNull(username);
        checkArgument(isValidTelegram(username), MESSAGE_CONSTRAINTS);
        this.username = username;
    }

    /**
     * Returns if a given string is a valid Telegram username.
     */
    public static Boolean isValidTelegram(String test) {
        return test.equals("") || test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the Telegram object is empty.
     */
    public boolean isEmpty() {
        return this.equals(EMPTY_TELEGRAM);
    }

    @Override
    public String toString() {
        return this.username;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && username.toLowerCase().equals(((Telegram) other).username.toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

}
