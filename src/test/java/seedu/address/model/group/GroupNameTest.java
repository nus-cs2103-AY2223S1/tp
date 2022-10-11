package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.person.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GroupNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new GroupName(invalidName));
    }

    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> GroupName.isValidName(null));

        // invalid name
        assertFalse(GroupName.isValidName("")); //empty string
        assertFalse(GroupName.isValidName(" ")); // spaces only
        assertFalse(GroupName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(GroupName.isValidName("TP^")); // contains non-alphanumeric characters

        // valid name
        assertTrue(GroupName.isValidName("team project")); // alphabets only
        assertTrue(GroupName.isValidName("2103")); // numbers only
        assertTrue(GroupName.isValidName("CS2103T TP")); // alphanumeric characters
        assertTrue(GroupName.isValidName("CS2103T Team Project")); // with capital letters
        assertTrue(GroupName.isValidName("CS2103T Team Project TABS for Project Team Leaders")); // long names
    }
}
