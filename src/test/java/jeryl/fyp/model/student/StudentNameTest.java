package jeryl.fyp.model.student;

import static jeryl.fyp.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StudentNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> StudentName.isValidStudentName(null));

        // invalid name
        assertFalse(StudentName.isValidStudentName("")); // empty string
        assertFalse(StudentName.isValidStudentName(" ")); // spaces only
        assertFalse(StudentName.isValidStudentName("^")); // only unsupported characters
        assertFalse(StudentName.isValidStudentName("Test2")); // numeric characters
        assertFalse(StudentName.isValidStudentName("/////")); // only supported non-alphabetic characters

        // valid name
        assertTrue(StudentName.isValidStudentName("peter jack")); // alphabets only
        assertTrue(StudentName.isValidStudentName("Raju S/O Muthu")); // contains non-alphabetic characters
        assertTrue(StudentName.isValidStudentName(
                "Raju S//O Muthu")); // contains consecutive non-alphabetic characters
        assertTrue(StudentName.isValidStudentName(
                "Bai Guo Zhang (Bagazang)")); // contains more non-alphabetic characters
        assertTrue(StudentName.isValidStudentName(
                "Raju S/O Muthu S/O Gopal")); // contains multiple non-alphabetic characters
        assertTrue(StudentName.isValidStudentName("Capital Tan")); // with capital letters
        assertTrue(StudentName.isValidStudentName("David Roger Jackson Ray Jr")); // long names
        assertTrue(StudentName.isValidStudentName(
                "small big QUICK FOX (actually) [maybe] two//three IS [[{equal] {to} four")); // combination
    }
}
