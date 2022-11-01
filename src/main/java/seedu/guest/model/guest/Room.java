package seedu.guest.model.guest;

import static java.util.Objects.requireNonNull;
import static seedu.guest.commons.util.AppUtil.checkArgument;

/**
 * Represents the Guest's room
 * Guarantees: immutable; is valid as declared in {@link #isValidRoom(String)}
 */
public class Room {

    public static final String MESSAGE_CONSTRAINTS =
            "Rooms should only contain alphanumeric characters and hyphens (-). "
            + "They must be at least 1 character and at most 100 characters long."
            + "They cannot start or end with a hyphen. "
            + "Hyphens can only be used between 2 alphanumeric characters.";

    // Maximum number of characters able to be entered into Room
    private static final int MAX_LENGTH_ROOM = 100;

    // Room contains alphanumeric characters with an optional hyphen only in between, and don't start with a hyphen
    // It can also accept a hyphen as long as it is in between 2 alphanumeric characters
    // Solution below adapted from https://stackoverflow.com/questions/51063724/
    private static final String VALIDATION_REGEX = "^[A-Za-z0-9](-?[A-Za-z0-9])*$";

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
        return test.matches(VALIDATION_REGEX) && test.length() <= MAX_LENGTH_ROOM;
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
