package paymelah.model.person;

import static java.util.Objects.requireNonNull;
import static paymelah.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's telegram handle in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidHandle(String)}
 */
public class Telegram {
    public static final Telegram EMPTY_TELEGRAM = new Telegram();
    public static final String EMPTY_TELEGRAM_STRING = "Telegram unknown";
    public static final String MESSAGE_CONSTRAINTS = "Telegram handles should be of the format @username "
            + "and adhere to the following constraints:\n"
            + "1. The username-part can optionally be preceded by a '@'.\n"
            + "2. The username-part should be at least 5 characters long and only contain alphanumeric characters "
            + "and the special character '_'. The username-part may only start with an alphabetic character and it "
            + "cannot end with the '_' character.\n";

    public static final String OPTIONAL_AT = "^@?"; // May include At character as prefix
    public static final String USERNAME_START = "[a-zA-Z]"; // Start with a letter
    public static final String USERNAME_MIDDLE = "\\w{3,}"; // At least three characters
    public static final String USERNAME_END = "[\\w&&[^_]]"; // Cannot end with underscore
    public static final String USERNAME_COMPLETE = "(" + USERNAME_START + USERNAME_MIDDLE + USERNAME_END + ")$";
    public static final String VALIDATION_REGEX = OPTIONAL_AT + USERNAME_COMPLETE;

    public final String value;

    /**
     * Constructs an {@code TelegramHandle}.
     *
     * @param handle A valid Telegram handle.
     */
    public Telegram(String handle) {
        requireNonNull(handle);
        checkArgument(isValidHandle(handle), MESSAGE_CONSTRAINTS);
        if (handle.startsWith("@")) {
            value = handle;
        } else {
            value = "@" + handle;
        }
    }

    private Telegram() {
        value = EMPTY_TELEGRAM_STRING;
    }

    /**
     * Returns if a given string is a valid Telegram Handle.
     */
    public static boolean isValidHandle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && value.equals(((Telegram) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
