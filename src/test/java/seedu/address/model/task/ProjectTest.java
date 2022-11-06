package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void validToString() {
        assertTrue(new Project("project").toString().equals("project"));
        assertTrue(Project.UNSPECIFIED.toString().equals(Project.UNSPECIFIED_PROJECT_IDENTIFIER));
    }

    @Test
    public void equals() {
        Project p1 = new Project("Project");
        Project p2 = new Project("project");

        // same object -> return true
        assertEquals(p1, p1);
        assertEquals(p1.hashCode(), p1.hashCode());

        // same title -> return true
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

}
