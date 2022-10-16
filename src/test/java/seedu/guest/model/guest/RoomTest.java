package seedu.guest.model.guest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoomTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Room(null));
    }

    @Test
    public void constructor_invalidRoom_throwsIllegalArgumentException() {
        String invalidRoom = "$50-23";
        assertThrows(IllegalArgumentException.class, () -> new Room(invalidRoom));
    }

    @Test
    public void isValidRoom() {
        // null room
        assertThrows(NullPointerException.class, () -> Room.isValidRoom(null));

        // invalid room
        assertFalse(Room.isValidRoom("")); // empty string
        assertFalse(Room.isValidRoom(" ")); // spaces only
        assertFalse(Room.isValidRoom("-50-23")); // not allowed to start with a hyphen
        assertFalse(Room.isValidRoom("$50")); // '$' is not allowed
        assertFalse(Room.isValidRoom("^&*")); // special symbols only are not allowed
        assertFalse(Room.isValidRoom("aloha-5()")); // '(' or ')' is not allowed

        // valid room
        assertTrue(Room.isValidRoom("05-73")); // only alphanumeric characters and hyphen
        assertTrue(Room.isValidRoom("Aloha-5")); // only alphanumeric characters and hyphen

    }
}
