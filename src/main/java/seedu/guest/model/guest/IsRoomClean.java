package seedu.guest.model.guest;

import static java.util.Objects.requireNonNull;
import static seedu.guest.commons.util.AppUtil.checkArgument;

/**
 * Represents the boolean status if the guest's room is cleaned.
 * Guarantees: immutable; is valid as declared in {@link #isValidIsRoomClean(String)}
 */
public class IsRoomClean {

    public static final String MESSAGE_CONSTRAINTS =
            "Is Room Clean should only contain the strings \"yes\" or \"no\"";
    public static final String VALIDATION_REGEX = "^yes$|^no$";
    public final String value;

    /**
     * Constructs a {@code IsRoomClean}.
     *
     * @param isClean A valid is room clean.
     */
    public IsRoomClean(String isClean) {
        requireNonNull(isClean);
        checkArgument(isValidIsRoomClean(isClean), MESSAGE_CONSTRAINTS);
        value = isClean;
    }

    /**
     * Returns true if a given string is a valid is room clean.
     */
    public static boolean isValidIsRoomClean(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsRoomClean // instanceof handles nulls
                && value.equals(((IsRoomClean) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
