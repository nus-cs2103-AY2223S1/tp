package friday.model.student;

import static friday.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegramHandle(String)}
 */
public class TelegramHandle {


    public static final String MESSAGE_CONSTRAINTS =
            "TelegramHandle should only contain letters a-z, numbers 0-9, and underscores, and it should be at least " +
                    "5 characters long";
    public static final String VALIDATION_REGEX = "[a-z0-9_]{5,}";
    public final String value;

    /**
     * Constructs a {@code TelegramHandle}.
     *
     * @param handle A valid phone number.
     */
    public TelegramHandle(String handle) {
        requireNonNull(handle);
        checkArgument(isValidTelegramHandle(handle), MESSAGE_CONSTRAINTS);
        value = handle;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidTelegramHandle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "@" + value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TelegramHandle // instanceof handles nulls
                && value.equals(((TelegramHandle) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
