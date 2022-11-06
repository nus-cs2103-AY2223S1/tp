package seedu.address.model.tutorial;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GroupTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Group(null));
    }

    @Test
    public void constructor_invalidGroup_throwsIllegalArgumentException() {
        String invalidGroup = "";
        assertThrows(IllegalArgumentException.class, () -> new Group(invalidGroup));
    }

    @Test
    public void isValidGroup() {
        // null group
        assertThrows(NullPointerException.class, () -> Group.isValidGroup(null));

        // invalid group
        assertFalse(Group.isValidGroup("")); // empty string
        assertFalse(Group.isValidGroup(" ")); // spaces only
        assertFalse(Group.isValidGroup("^")); // only non-alphanumeric characters
        assertFalse(Group.isValidGroup("T08*")); // contains non-alphanumeric characters

        // valid group
        assertTrue(Group.isValidGroup("t")); // alphabets only
        assertTrue(Group.isValidGroup("12345")); // numbers only
        assertTrue(Group.isValidGroup("t08")); // alphanumeric characters
        assertTrue(Group.isValidGroup("T08")); // with capital letters
    }
}
