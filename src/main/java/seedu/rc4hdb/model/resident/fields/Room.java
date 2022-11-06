package seedu.rc4hdb.model.resident.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

import seedu.rc4hdb.model.StringField;

//@@ alvinjiang1
/**
 * Represents a Resident's Room in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidRoom(String)}
 */
public class Room extends StringField implements ResidentField {

    public static final String IDENTIFIER = "Room";

    public static final String MESSAGE_CONSTRAINTS =
            "Room Number should be in the format FLOOR-UNIT, where FLOOR and UNIT are both positive integers and FLOOR"
                    + " should be below 30";

    /**
     * The characters before and after the '-' must be a positive integer.
     */
    public static final String VALIDATION_REGEX = "^[0]*[0-2]?[1-9][-][0-9]*[1-9][0-9]*$";

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
        String[] splitString = test.split("-");
        if (splitString.length != 2) {
            return false;
        }
        try {
            int floorNo = Integer.parseInt(splitString[0]);
            int roomNo = Integer.parseInt(splitString[1]);
            return floorNo > 0 && floorNo < 30
                    && roomNo > 0 && roomNo < 30;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
