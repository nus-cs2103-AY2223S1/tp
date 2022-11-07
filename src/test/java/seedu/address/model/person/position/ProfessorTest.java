package seedu.address.model.person.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProfessorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Professor(null));
    }

    @Test
    public void constructor_invalidRole_throwsIllegalArgumentException() {
        String invalidRole = "mayor";
        assertThrows(IllegalArgumentException.class, () -> new Professor(invalidRole));
    }

    @Test
    public void isValidRole() {
        // null role
        assertThrows(NullPointerException.class, () -> Professor.isValidRole(null));

        // invalid role
        assertFalse(Professor.isValidRole("")); // empty string
        assertFalse(Professor.isValidRole(" ")); // spaces only
        assertFalse(Professor.isValidRole("string")); // random string
        assertFalse(Professor.isValidRole("123")); // numbers
        assertFalse(Professor.isValidRole("cooordinator")); // incorrect spelling
        assertFalse(Professor.isValidRole("ununassigned")); // incorrect spelling
        assertFalse(Professor.isValidRole(" tutor ")); // random spaces

        // valid role
        assertTrue(Professor.isValidRole("coordinator")); // small cases
        assertTrue(Professor.isValidRole("Lecturer")); // beginning with upper case
        assertTrue(Professor.isValidRole("unassIGNED")); // ending with upper case
        assertTrue(Professor.isValidRole("TUTOR")); // all upper cases
        assertTrue(Professor.isValidRole("aDvIsOr")); // mix of lower and upper cases

    }
}
