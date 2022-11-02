package seedu.address.commons.core.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class UniqueIdTest {
    @Test
    public void compareTo() {
        String firstString = "first";
        String secondString = "second";

        UniqueId firstId = new UniqueId(firstString);
        UniqueId secondId = new UniqueId(secondString);

        //same object -> 0
        assertEquals(firstId.compareTo(firstId), 0);

        //same fields -> 0
        UniqueId firstIdCopy = new UniqueId(firstString);
        assertEquals(firstId.compareTo(firstIdCopy), 0);

        //different strings
        int expected = firstString.compareTo(secondString);
        assertEquals(firstId.compareTo(secondId), expected);
    }

    @Test
    public void equals() {
        String firstString = "first";
        String secondString = "second";

        UniqueId firstId = new UniqueId(firstString);
        UniqueId secondId = new UniqueId(secondString);

        //same object -> returns true
        assertEquals(firstId, firstId);

        //same fields -> returns true
        UniqueId firstIdCopy = new UniqueId(firstString);
        assertEquals(firstId, firstIdCopy);

        //different types -> returns false
        assertNotEquals(firstId, 1);
        assertNotEquals(firstId, firstString);
        assertNotEquals(firstId, null);

        //different fields -> returns false
        assertNotEquals(firstId, secondId);
    }
}
