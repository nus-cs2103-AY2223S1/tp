package swift.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swift.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskNameTest {

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
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> TaskName.isValidName(null));

        // invalid name
        assertFalse(TaskName.isValidName("")); // empty string
        assertFalse(TaskName.isValidName(" ")); // spaces only
        assertFalse(TaskName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(TaskName.isValidName("milk*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(TaskName.isValidName("buy milk")); // alphabets only
        assertTrue(TaskName.isValidName("12345")); // numbers only
        assertTrue(TaskName.isValidName("buy milk for the 2nd time")); // alphanumeric characters
        assertTrue(TaskName.isValidName("Buy Milk")); // with capital letters
        assertTrue(TaskName.isValidName("Buy Milk for the 2nd time")); // long names
    }

    @Test
    public void equals_sameObject_true() {
        TaskName name = new TaskName("Buy milk");
        assertEquals(name, name);
    }

    @Test
    public void equals_sameTaskName_true() {
        assertEquals(new TaskName("Buy milk"), new TaskName("Buy milk"));
    }

    @Test
    public void equals_differentTaskName_false() {
        assertNotEquals(new TaskName("Buy milk"), new TaskName("CS2103T"));
    }
}
