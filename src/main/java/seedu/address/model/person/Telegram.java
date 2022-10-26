package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's telegram handle in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class Telegram {

    public static final String MESSAGE_CONSTRAINTS =
            "Telegram handles should start with a @ symbol and "
                    + "should only have alphanumeric characters after the @. "
                    + "The minimum length of the telegram handle should be 5 characters, not including the @ symbol. "
                    + "Additionally, it does not accept spaces between any characters";

    public static final String LOWER_CASE_ALPHANUMERIC_WITH_AT_LEAST_FIVE_CHARACTERS = "[a-z0-9_]{5,}";
    public static final String VALIDATION_REGEX = "@" + LOWER_CASE_ALPHANUMERIC_WITH_AT_LEAST_FIVE_CHARACTERS;

    public final String value;

    /**
     * Constructs a {@code Telegram} handle.
     *
     * @param telegram A valid telegram handle.
     */
    public Telegram(String telegram) {
        requireNonNull(telegram);
        checkArgument(isValidTelegram(telegram), MESSAGE_CONSTRAINTS);
        value = telegram;
    }

    /**
     * Returns if a given string is a valid telegram handle.
     */
    public static boolean isValidTelegram(String test) {
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
