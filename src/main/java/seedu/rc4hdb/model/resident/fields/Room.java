package seedu.rc4hdb.model.resident.fields;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

/**
 * Represents a Resident's Room in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidRoom(String)}
 */
public class Room extends Field {

    public static final String MESSAGE_CONSTRAINTS =
            "Room Number should be in the format FLOOR-UNIT, where FLOOR and UNIT are both non-negative integers";

    /**
     * The characters before and after the '-' must be a positive integer.
     */
    public static final String VALIDATION_REGEX = "^[0-9]*[1-9][0-9]*[-][0-9]*[1-9][0-9]*$";

    public final String room;

    /**
     * Constructs a {@code Room}.
     *
     * @param room A valid room.
     */
    public Room(String room) {
        requireNonNull(room);
        checkArgument(isValidRoom(room), MESSAGE_CONSTRAINTS);
        this.room = room;
    }

    /**
     * Returns true if a given string is a valid room.
     */
    public static boolean isValidRoom(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return room;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Room // instanceof handles nulls
                && room.equals(((Room) other).room)); // state check
    }

    @Override
    public int hashCode() {
        return room.hashCode();
    }

}
