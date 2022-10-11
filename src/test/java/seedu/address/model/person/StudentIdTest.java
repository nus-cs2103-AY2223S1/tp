package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentId(null));
    }

    @Test
    public void constructor_invalidStudentID_throwsIllegalArgumentException() {
        String invalidStudentID = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidStudentID));
    }

    @Test
    public void isValidStudentID() {
        // null Student ID
        assertThrows(NullPointerException.class, () -> StudentId.isValidStudentID(null));

        // invalid Student IDs
        assertFalse(StudentId.isValidStudentID("")); // empty string
        assertFalse(StudentId.isValidStudentID(" ")); // spaces only
        assertFalse(StudentId.isValidStudentID("A02384")); // less than 9 characters
        assertFalse(StudentId.isValidStudentID("AB123454A")); // wrong format
        assertFalse(StudentId.isValidStudentID("A011p047W")); // alphabets within digits
        assertFalse(StudentId.isValidStudentID("A312 153W")); // spaces within digits
        assertFalse(StudentId.isValidStudentID(" A3128153W")); // spaces before ID
        assertFalse(StudentId.isValidStudentID("A3128153W ")); // spaces after ID
        assertFalse(StudentId.isValidStudentID(" A3128153W ")); // spaces before and after ID

        // valid Student IDs
        assertTrue(StudentId.isValidStudentID("A0233839W"));
        assertTrue(StudentId.isValidStudentID("U0233839U"));
        assertTrue(StudentId.isValidStudentID("c0273809t"));
    }
}

