package seedu.studmap.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's telegram handle in the student map.
 * Guarantees: immutable; is valid as declared in {@link #isValidTeleHandle(String)}
 */
public class TeleHandle {

    public static final String NO_TELEHANDLE_STRING = "No Telegram handle";

    public static final String TELEHANDLE_DISPLAY_PREFIX = "Telegram: ";

    public static final String MESSAGE_CONSTRAINTS = "Telegram handle should be in the format @handle";

    public static final String VALIDATION_REGEX = "^[@][\\p{all}]*";

    public final String value;

    /**
     * Constructs an empty {@code TeleHandle}.
     */
    public TeleHandle() {
        this.value = "";
    }

    /**
     * Constructs a {@code TeleHandle}.
     *
     * @param handle A valid name.
     */
    public TeleHandle(String handle) {
        requireNonNull(handle);
        checkArgument(isValidTeleHandle(handle), MESSAGE_CONSTRAINTS);
        this.value = handle;
    }

    /**
     * Returns string used for display in UI.
     *
     * @return String to be displayed
     */
    public String getDisplayString() {
        return this.value.isEmpty()
                ? NO_TELEHANDLE_STRING
                : TELEHANDLE_DISPLAY_PREFIX + value;
    }

    /**
     * Returns true if a given string is a valid telegram handle.
     */
    public static boolean isValidTeleHandle(String test) {
        return test.isBlank() || test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value.isEmpty()
                ? NO_TELEHANDLE_STRING
                : value;
    }

    /**
     * Returns string used for sorting telegram handle.
     */
    public String toCmpString() {
        return this.value.isEmpty()
               ? null
               : value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeleHandle // instanceof handles nulls
                && value.equals(((TeleHandle) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
