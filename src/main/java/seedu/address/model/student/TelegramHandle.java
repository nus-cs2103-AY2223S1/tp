package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's telegram handle in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class TelegramHandle {

    public static final String MESSAGE_CONSTRAINTS =
            "Telegram handles should only contain alphanumeric characters and underscores, and it should not be blank";

    public static final String VALIDATION_REGEX = "[a-zA-Z0-9_]+";

    public final String telegram;

    /**
     * Constructs a {@code Telegram}.
     *
     * @param telegram A valid name.
     */
    public TelegramHandle(String telegram) {
        requireNonNull(telegram);
        checkArgument(isValidTelegram(telegram), MESSAGE_CONSTRAINTS);
        this.telegram = telegram;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidTelegram(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return telegram;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TelegramHandle // instanceof handles nulls
                && telegram.equals(((TelegramHandle) other).telegram)); // state check
    }

    @Override
    public int hashCode() {
        return telegram.hashCode();
    }

}
