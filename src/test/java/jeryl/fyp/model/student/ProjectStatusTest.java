package jeryl.fyp.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ProjectStatusTest {

    @Test
    public void equals() {
        ProjectStatus status = new ProjectStatus("IP");

        // same object -> returns true
        assertTrue(status.equals(status));

        // same values -> returns true
        ProjectStatus statusCopy = new ProjectStatus("IP");
        assertTrue(status.equals(statusCopy));

        // different types -> returns false
        assertFalse(status.equals(1));

        // null -> returns false
        assertFalse(status.equals(null));

        // different remark -> returns false
        ProjectStatus differentStatus = new ProjectStatus("DONE");
        assertFalse(status.equals(differentStatus));
    }
}
