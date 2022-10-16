package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SortDirectionTest {
    private static final SortDirection INCREASING = new SortDirection("+");
    private static final SortDirection DECREASING = new SortDirection("-");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortDirection(null));
    }

    @Test
    public void constructor_invalidSortDirection_throwsIllegalArgumentException() {
        String[] invalidDirections = new String[] {
            "",
            "yes",
            "decreasing"
        };
        for (String input : invalidDirections) {
            assertThrows(IllegalArgumentException.class, () -> new SortDirection(input));
        }
    }

    @Test
    public void testIsIncreasing() {
        assertTrue(INCREASING.isIncreasing());
        assertFalse(DECREASING.isIncreasing());
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(INCREASING, new SortDirection("+"));
        assertNotEquals(INCREASING, DECREASING);
        assertEquals(DECREASING, new SortDirection("-"));
        assertEquals(INCREASING.hashCode(), INCREASING.hashCode());
    }

    @Test
    public void testToString() {
        // same object -> returns true
        assertEquals("increasing", INCREASING.toString());
        assertEquals("decreasing", DECREASING.toString());
    }
}
