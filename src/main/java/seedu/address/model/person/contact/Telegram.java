package seedu.address.model.person.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's telegram in the contact book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class Telegram extends Contact {

    public static final String MESSAGE_CONSTRAINTS = "Telegram username should be of the format @username "
            + "and adhere to the following constraints:\n"
            + "1. The username should be between 5 and 32 characters in length."
            + "2. The username should only contain alphanumeric characters and underscores. The username may not "
            + "start or end with any underscores."
            + "3. The username must not have consecutive underscores.";

    /*
     * The first character of the telegram must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^@(?=.{5,32}$)(?!.*__)[A-Za-z][A-Za-z0-9_]*[A-Za-z0-9]$";
    public static final String TELEGRAM_LINK_PREFIX = "https://t.me/";

    private static final ContactType CONTACT_TYPE = ContactType.TELEGRAM;

    /**
     * Constructs an {@code Telegram}.
     *
     * @param username A valid telegram's username.
     */
    public Telegram(String username) {
        super(CONTACT_TYPE, username, TELEGRAM_LINK_PREFIX + username);
        requireNonNull(username);
        checkArgument(isValidTelegram(username), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid telegram username.
     */
    public static boolean isValidTelegram(String test) {
        return test.matches(VALIDATION_REGEX);
    }

}
