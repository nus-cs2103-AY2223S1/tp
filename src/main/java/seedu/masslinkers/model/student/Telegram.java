package seedu.masslinkers.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.masslinkers.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's address in the mass linkers.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class Telegram {

    public static final String MESSAGE_CONSTRAINTS = "Telegram handle is case insensitive and "
            + "can only contain a-z, 0-9, underscores and have a minimum length of 5 characters.";

    // Regex adapted from:
    // https://stackoverflow.com/questions/63308185/regex-match-telegram-username-and-delete-whole-line-in-php
    public static final String VALIDATION_REGEX = "[a-zA-Z0-9]+(?:_[a-zA-Z0-9]+)*";

    public final String handle;

    /**
     * Constructs an {@code Telegram}.
     *
     * @param handle A valid handle.
     */
    public Telegram(String handle) {
        requireNonNull(handle);
        checkArgument(isValidTelegram(handle), MESSAGE_CONSTRAINTS);
        this.handle = handle.toLowerCase();
    }

    /**
     * Checks if telegram provided is valid.
     * Checks follow that of telegram's guidelines:
     * "You can use a-z, 0-9, underscores. Minimum length is 5 characters."
     */
    public static boolean isValidTelegram(String test) {
        return test.length() >= 5 && test.toLowerCase().matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return handle;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && handle.equalsIgnoreCase(((Telegram) other).handle)); // state check
    }

    @Override
    public int hashCode() {
        return handle.hashCode();
    }

}
