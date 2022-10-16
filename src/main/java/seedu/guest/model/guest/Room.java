package seedu.guest.model.guest;

import static java.util.Objects.requireNonNull;
import static seedu.guest.commons.util.AppUtil.checkArgument;

/**
 * Represents the Guest's room
 * Guarantees: immutable; is valid as declared in {@link #isValidRoom(String)}
 */
public class Room {
    public static final String MESSAGE_CONSTRAINTS =
            "Room should only contain alphanumeric characters and hyphens";

    // Room contains //alphanumeric characters with hyphen only, and don't start with a hyphen
    private static final String VALIDATION_REGEX = "^[a-zA-Z0-9][a-zA-Z0-9\\-]*$";

    public final String value;

    /**
     * Constructs a {@code Room}.
     *
     * @param roomNumberValue A valid room number.
     */
    public Room(String roomNumberValue) {
        requireNonNull(roomNumberValue);
        checkArgument(isValidRoom(roomNumberValue), MESSAGE_CONSTRAINTS);
        value = roomNumberValue;
    }

    /**
     * Returns true if a given string is a valid room number.
     */
    public static boolean isValidRoom(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Room // instanceof handles nulls
                && value.equals(((Room) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }



}
