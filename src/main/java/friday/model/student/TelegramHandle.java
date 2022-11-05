package friday.model.student;

import static friday.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Student's Telegram handle in Friday.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegramHandle(String)}
 */
public class TelegramHandle implements Comparable<TelegramHandle> {


    public static final String MESSAGE_CONSTRAINTS =
            "Telegram handle should only contain letters a-z, numbers 0-9, and underscores; it should also be between "
                    + "5 and 32 characters long";
    public static final String VALIDATION_REGEX = "[a-zA-Z0-9_]{5,32}";

    public static final TelegramHandle EMPTY_TELEGRAMHANDLE = new TelegramHandle();

    public final String value;

    /**
     * Constructs a {@code TelegramHandle}.
     *
     * @param handle A valid Telegram handle.
     */
    public TelegramHandle(String handle) {
        requireNonNull(handle);
        checkArgument(isValidTelegramHandle(handle), MESSAGE_CONSTRAINTS);
        value = handle;
    }

    /**
     * Constructs a {@code TelegramHandle} for the empty instance.
     */
    private TelegramHandle() {
        value = "";
    }

    /**
     * Returns true if the given string is a valid Telegram handle.
     */
    public static boolean isValidTelegramHandle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the given string is a valid Telegram handle or empty.
     *
     * Only to be used when converting JSON to Student in JsonAdaptedStudent.
     */
    public static boolean isValidOrEmptyJson(String test) {
        return test.matches(VALIDATION_REGEX) || test.equals("");
    }

    /**
     * Returns true if the given Telegram handle is the empty value.
     */
    public boolean isEmpty() {
        return this == EMPTY_TELEGRAMHANDLE;
    }

    @Override
    public String toString() {
        String str = this.isEmpty() ? "" : value;
        return "@" + str;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TelegramHandle // instanceof handles nulls
                && value.toLowerCase().equals(((TelegramHandle) other).value.toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(TelegramHandle telegramHandle) {
        if (this.equals(telegramHandle)) {
            return 0;
        } else if (this.isEmpty()) {
            return 1;
        } else if (telegramHandle.isEmpty()) {
            return -1;
        }
        return this.value.toLowerCase().compareTo(telegramHandle.value.toLowerCase());
    }

}
