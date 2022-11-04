package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OccupationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Occupation(null));
    }

    @Test
    public void constructor_invalidOccupation_throwsIllegalArgumentException() {
        String invalidOccupation = "";
        assertThrows(IllegalArgumentException.class, () -> new Occupation(invalidOccupation));
    }

    @Test
    public void isValidOccupation() {
        // null Occupation
        assertThrows(NullPointerException.class, () -> Occupation.isValidOccupation(null));

        // invalid Occupation
        assertFalse(Occupation.isValidOccupation("")); // empty string
        assertFalse(Occupation.isValidOccupation(" ")); // spaces only
        assertFalse(Occupation.isValidOccupation("^")); // only non-alphanumeric characters
        assertFalse(Occupation.isValidOccupation("peter*")); // contains non-alphanumeric characters
        assertFalse(Occupation.isValidOccupation(("hello"))); // not student, ta or professor
        assertFalse(Occupation.isValidOccupation(("TA1"))); // not student, ta or professor
        assertFalse(Occupation.isValidOccupation(("TAT"))); // not student, ta or professor
        assertFalse(Occupation.isValidOccupation(("student2"))); // preceded by white space
        assertFalse(Occupation.isValidOccupation(("students"))); // not student, ta or professor
        assertFalse(Occupation.isValidOccupation(("student s"))); // not student, ta or professor
        assertFalse(Occupation.isValidOccupation(("tas"))); // not student, ta or professor
        assertFalse(Occupation.isValidOccupation(("ta1"))); // not student, ta or professor
        assertFalse(Occupation.isValidOccupation(("ta s"))); // not student, ta or professor
        assertFalse(Occupation.isValidOccupation(("professors"))); // not student, ta or professor
        assertFalse(Occupation.isValidOccupation(("profesor"))); // not student, ta or professor
        assertFalse(Occupation.isValidOccupation(("professor2"))); // not student, ta or professor
        assertFalse(Occupation.isValidOccupation(("professor 2"))); // not student, ta or professor

        // valid Occupation
        assertTrue(Occupation.isValidOccupation("student"));
        assertTrue(Occupation.isValidOccupation("ta"));
        assertTrue(Occupation.isValidOccupation("professor"));
        assertTrue(Occupation.isValidOccupation("STUDENT"));
        assertTrue(Occupation.isValidOccupation("TA"));
        assertTrue(Occupation.isValidOccupation("PROFESSOR"));
    }
}
