package seedu.rc4hdb.model.resident.fields;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link House}.
 */
public class HouseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new House(null));
    }

    @Test
    public void constructor_invalidHouse_throwsIllegalArgumentException() {
        String invalidHouse = "q";
        assertThrows(IllegalArgumentException.class, () -> new House(invalidHouse));
    }

    @Test
    public void constructor_validHouse_constructHouse() {
        assertTrue(new House("D") instanceof House);
    }

    @Test
    public void isValidHouse() {
        // null house
        assertThrows(NullPointerException.class, () -> House.isValidHouse(null));

        // invalid house
        assertFalse(House.isValidHouse("")); // empty string
        assertFalse(House.isValidHouse(" ")); // spaces only
        assertFalse(House.isValidHouse("^")); // only non-alphanumeric characters
        assertFalse(House.isValidHouse("D*")); // contains non-alphanumeric characters
        assertFalse(House.isValidHouse("f")); // only D, U, L, A or N

        // valid house
        assertTrue(House.isValidHouse("D"));
        assertTrue(House.isValidHouse("U"));
        assertTrue(House.isValidHouse("L"));
        assertTrue(House.isValidHouse("A"));
        assertTrue(House.isValidHouse("N"));
    }

}
