package seedu.rc4hdb.model.resident.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

import seedu.rc4hdb.model.StringField;

/**
 * Represents a Resident's Room in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidRoom(String)}
 */
public class Room extends StringField implements ResidentField {

    public static final String IDENTIFIER = "Room";

    public static final String MESSAGE_CONSTRAINTS =
            "Room Number should be in the format FLOOR-UNIT, where FLOOR and UNIT are both positive integers written " +
                    "in a two digit format e.g. 01-01";

    /**
     * The characters before and after the '-' must be a positive integer.
     */
    public static final String VALIDATION_REGEX = "^[0-2][0-9][-][0-9][0-9]$";

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

}
