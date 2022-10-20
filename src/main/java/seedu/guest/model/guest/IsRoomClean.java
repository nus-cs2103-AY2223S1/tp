package seedu.guest.model.guest;

import static java.util.Objects.requireNonNull;
import static seedu.guest.commons.util.AppUtil.checkArgument;

/**
 * Represents the boolean status if the guest's room is cleaned.
 * Guarantees: immutable; is valid as declared in {@link #isValidIsRoomClean(String)}
 */
public class IsRoomClean {

    public static final String MESSAGE_CONSTRAINTS =
            "IsRoomClean field should only contain any case-insensitive versions of the strings"
                    + "\"yes\", \"no\", \"y\" or \"n\".";
    public static final String VALIDATION_REGEX = "^yes$|^no$|^y$|^n$";
    public static final String DEFAULT_IS_ROOM_CLEAN = "yes";
    public final String value;

    /**
     * Constructs a {@code IsRoomClean}.
     *
     * @param isRoomClean A valid isRoomClean.
     */
    public IsRoomClean(String isRoomClean) {
        requireNonNull(isRoomClean);
        String isRoomCleanToLower = isRoomClean.toLowerCase();
        checkArgument(isValidIsRoomClean(isRoomCleanToLower), MESSAGE_CONSTRAINTS);
        value = standardiseIsRoomClean(isRoomCleanToLower);
    }

    /**
     * Returns true if a given string is a valid isRoomClean.
     */
    public static boolean isValidIsRoomClean(String test) {
        String testToLower = test.toLowerCase();
        return testToLower.matches(VALIDATION_REGEX);
    }

    /**
     * Returns standardised notation of the isRoomClean field.
     *
     * @param status a valid isRoomClean.
     */
    private String standardiseIsRoomClean(String status) {
        requireNonNull(status);
        assert isValidIsRoomClean(status);

        String standardisedRoomUnclean = "no";
        String standardisedRoomClean = "yes";
        String abbreviatedRoomUnclean = "n";
        String unabbreviatedRoomUnclean = "no";

        if (status.equals(abbreviatedRoomUnclean) || status.equals(unabbreviatedRoomUnclean)) {
            return standardisedRoomUnclean;
        } else {
            return standardisedRoomClean;
        }
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
