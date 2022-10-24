package seedu.rc4hdb.model.resident.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

import seedu.rc4hdb.commons.util.StringUtil;

/**
 * Represents a Resident's Room in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidRoom(String)}
 */
public class Room extends ResidentField {

    public static final String IDENTIFIER = "Room";

    public static final String MESSAGE_CONSTRAINTS =
            "Room Number should be in the format FLOOR-UNIT, where FLOOR and UNIT are both non-negative integers";

    /**
     * The characters before and after the '-' must be a positive integer.
     */
    public static final String VALIDATION_REGEX = "^[0-9]*[1-9][0-9]*[-][0-9]*[1-9][0-9]*$";

    /**
     * Constructs a {@code Room}.
     *
     * @param room A valid room string.
     */
    public Room(String room) {
        super(room);
        checkArgument(isValidRoom(room), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid room.
     */
    public static boolean isValidRoom(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Returns true if given {@code Room} is contained in this room
     * @param room a substring of a room number
     * @return true if the given room is a substring of room
     */
    public boolean contains(String room) {
        return StringUtil.containsWordIgnoreCase(value, room);
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
