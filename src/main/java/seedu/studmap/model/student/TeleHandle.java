package seedu.studmap.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.commons.util.AppUtil.checkArgument;

public class TeleHandle {

    public static final String MESSAGE_CONSTRAINTS = "Telegram handle should be in the format @handle";

    public static final String VALIDATION_REGEX = "^[@][\\p{all}]*";

    public final String value;

    public TeleHandle(String handle) {
        requireNonNull(handle);
        checkArgument(isValidTeleHandle(handle), MESSAGE_CONSTRAINTS);
        this.value = handle;
    }

    public static boolean isValidTeleHandle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
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
