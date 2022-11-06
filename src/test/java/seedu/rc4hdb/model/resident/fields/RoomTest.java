package seedu.rc4hdb.model.resident.fields;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Room}.
 */
public class RoomTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Room(null));
    }

    @Test
    public void constructor_invalidRoom_throwsIllegalArgumentException() {
        String invalidRoom = "01_10";
        assertThrows(IllegalArgumentException.class, () -> new Room(invalidRoom));
    }

    @Test
    public void constructor_validRoom_constructRoom() {
        assertTrue(new Room("1-12") instanceof Room);
    }

    @Test
    public void isValidRoom() {
        // null room
        assertThrows(NullPointerException.class, () -> Room.isValidRoom(null));

        // invalid room
        assertFalse(Room.isValidRoom("")); // empty string
        assertFalse(Room.isValidRoom(" ")); // spaces only
        assertFalse(Room.isValidRoom("^")); // only non-alphanumeric characters
        assertFalse(Room.isValidRoom("m")); // no alphabet
        assertFalse(Room.isValidRoom("1-0")); // unit is zero
        assertFalse(Room.isValidRoom("0-1")); // floor is zero
        assertFalse(Room.isValidRoom("0-0")); // both floor and unit are zero
        assertFalse(Room.isValidRoom("-5-1")); // negative floor number
        assertFalse(Room.isValidRoom("3--5")); // negative unit number
        assertFalse(Room.isValidRoom("3a1-3")); // non-contiguous floor number
        assertFalse(Room.isValidRoom("3-3a7")); // non-contiguous unit number

        // valid room
        assertTrue(Room.isValidRoom("01-1")); // with zero in front of floor number
        assertTrue(Room.isValidRoom("5-07")); // with zero in front of unit number
        assertTrue(Room.isValidRoom("03-01")); // with zero in front of both floor and unit number
        assertTrue(Room.isValidRoom("1-7")); // no zero in front of both floor and unit number
    }

}
