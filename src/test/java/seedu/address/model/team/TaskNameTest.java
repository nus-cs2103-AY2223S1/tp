package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class TaskNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskName(invalidName));
    }

    @Test
    public void isValidTaskName() {
        // null name
        assertThrows(NullPointerException.class, () -> TaskName.isValidTaskName(null));

        // invalid name
        assertFalse(TaskName.isValidTaskName("")); // empty string
        assertFalse(TaskName.isValidTaskName(" ")); // spaces only

        // valid name
        assertTrue(TaskName.isValidTaskName("peter jack")); // alphabets only
        assertTrue(TaskName.isValidTaskName("12345")); // numbers only
        assertTrue(TaskName.isValidTaskName("peter the 2nd")); // alphanumeric characters
        assertTrue(TaskName.isValidTaskName("Capital Tan")); // with capital letters
        assertTrue(TaskName.isValidTaskName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(TaskName.isValidTaskName("peter*")); // contains non-alphanumeric characters
        assertTrue(TaskName.isValidTaskName("^")); // only non-alphanumeric characters
    }

}