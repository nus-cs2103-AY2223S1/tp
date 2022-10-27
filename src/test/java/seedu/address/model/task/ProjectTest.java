package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProjectTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Project(null));
    }

    @Test
    public void constructor_invalidProject_throwsIllegalArgumentException() {
        String invalidProject = "";
        assertThrows(IllegalArgumentException.class, () -> new Project(invalidProject));
    }

    @Test
    public void isValidProject() {
        // null title
        assertThrows(NullPointerException.class, () -> Project.isValidProjectName(null));

        // invalid titles
        assertFalse(Project.isValidProjectName("")); // empty string
        assertFalse(Project.isValidProjectName(" ")); // spaces only
        assertFalse(Project.isValidProjectName("*")); // invalid character

        // valid titles
        assertTrue(Project.isValidProjectName("CS2103T"));
        assertTrue(Project.isValidProjectName("A")); // one character
        assertTrue(Project.isValidProjectName("This is a very long project name")); // long project name
    }
}
