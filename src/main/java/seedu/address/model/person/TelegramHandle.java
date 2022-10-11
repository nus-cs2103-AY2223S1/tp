package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's telegram handle in the address book.
 */
public class TelegramHandle {

    public static final String MESSAGE_CONSTRAINTS =
            "Telegram Handle should start with a @ and only contain alphanumeric characters and underscores,"
                    + " and it should be at least 6 characters long inclusive of @.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[@]\\w{6,}";

    public final String telegramHandle;

    /**
     * Constructs a {@code TelegramHandle}.
     *
     * @param name A valid name.
     */
    public TelegramHandle(String name) {
        requireNonNull(name);
        checkArgument(isValidTelegramHandle(name), MESSAGE_CONSTRAINTS);
        telegramHandle = name;
    }

    /**
     * Returns true if a given string is a valid telegram handle.
     */
    public static boolean isValidTelegramHandle(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return telegramHandle;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TelegramHandle // instanceof handles nulls
                && telegramHandle.equals(((TelegramHandle) other).telegramHandle)); // state check
    }

    @Override
    public int hashCode() {
        return telegramHandle.hashCode();
    }

}
