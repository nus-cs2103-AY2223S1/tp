package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FloorNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FloorNumber(null));
    }

    @Test
    public void constructor_invalidFloorNumber_throwsIllegalArgumentException() {
        // 0th floor
        assertThrows(IllegalArgumentException.class, () -> new FloorNumber(0));

        // negative floors
        assertThrows(IllegalArgumentException.class, () -> new FloorNumber(-1));
        assertThrows(IllegalArgumentException.class, () -> new FloorNumber(-10));
        assertThrows(IllegalArgumentException.class, () -> new FloorNumber(-999));
        assertThrows(IllegalArgumentException.class, () -> new FloorNumber(Integer.MIN_VALUE));
    }

    @Test
    public void isValidFloorNumberTest() {
        // invalid Floor numbers
        assertFalse(FloorNumber.isValidFloorNumber(0));
        assertFalse(FloorNumber.isValidFloorNumber(-1));
        assertFalse(FloorNumber.isValidFloorNumber(-10));
        assertFalse(FloorNumber.isValidFloorNumber(-999));
        assertFalse(FloorNumber.isValidFloorNumber(Integer.MIN_VALUE));

        // valid Floor numbers
        assertTrue(FloorNumber.isValidFloorNumber(1));
        assertTrue(FloorNumber.isValidFloorNumber(5));
        assertTrue(FloorNumber.isValidFloorNumber(10));
        assertTrue(FloorNumber.isValidFloorNumber(999));
        assertTrue(FloorNumber.isValidFloorNumber(Integer.MAX_VALUE));
    }

    @Test
    public void toStringTest() {
        String expectedMessage1 = "Floor: 1";
        String expectedMessage2 = "Floor: 5";
        FloorNumber floorNumber1 = new FloorNumber(1);
        FloorNumber floorNumber2 = new FloorNumber(5);

        // same floor
        assertEquals(expectedMessage1, floorNumber1.toString());
        assertEquals(expectedMessage2, floorNumber2.toString());

        // different floor
        assertNotEquals(expectedMessage1, floorNumber2.toString());
        assertNotEquals(expectedMessage2, floorNumber1.toString());
    }

    @Test
    public void equals() {
        FloorNumber floorNumber = new FloorNumber(1);

        // same object -> returns true
        assertTrue(floorNumber.equals(floorNumber));

        // same values -> returns true
        FloorNumber floorNumberCopy = new FloorNumber(1);
        assertTrue(floorNumber.equals(floorNumberCopy));

        // different types -> returns false
        assertFalse(floorNumber.equals(1));
        assertFalse(floorNumber.equals("1"));

        // null -> returns false
        assertFalse(floorNumber.equals(null));

        // different floor number -> returns false
        FloorNumber differentFloorNumber = new FloorNumber(5);
        assertFalse(floorNumber.equals(differentFloorNumber));
    }

    @Test
    public void hashCodeTest() {
        FloorNumber floorNumber1 = new FloorNumber(1);
        FloorNumber floorNumber2 = new FloorNumber(1);
        FloorNumber floorNumber3 = new FloorNumber(5);

        // same object -> same hashcode
        assertEquals(floorNumber1.hashCode(), floorNumber1.hashCode());

        // same values -> same hashcode
        assertEquals(floorNumber1.hashCode(), floorNumber2.hashCode());

        // different values -> different hashcode
        assertNotEquals(floorNumber1.hashCode(), floorNumber3.hashCode());
        assertNotEquals(floorNumber2.hashCode(), floorNumber3.hashCode());
    }
}
