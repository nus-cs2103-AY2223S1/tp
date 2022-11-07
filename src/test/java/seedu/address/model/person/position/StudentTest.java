package seedu.address.model.person.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Assignment;



public class StudentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Student(null));
        assertThrows(NullPointerException.class, () -> new Student(null, "non_null",
                new ArrayList<>(), "non_null"));
        assertThrows(NullPointerException.class, () -> new Student("non_null", null,
                new ArrayList<>(), "non_null"));
        assertThrows(NullPointerException.class, () -> new Student("non_null", "non_null",
                null, "non_null"));
        assertThrows(NullPointerException.class, () -> new Student("non_null", "non_null",
                new ArrayList<>(), null));
    }

    @Test
    public void isValidAttendance() {
        // null attendance
        assertThrows(NullPointerException.class, () -> Student.isValidAttendance(null));

        // invalid attendance
        assertFalse(Student.isValidAttendance("")); // empty string
        assertFalse(Student.isValidAttendance(" ")); // spaces only
        assertFalse(Student.isValidAttendance("123")); // less than 3 numbers
        assertFalse(Student.isValidAttendance("abc")); // non-numeric
        assertFalse(Student.isValidAttendance("/ ")); // lack of numbers
        assertFalse(Student.isValidAttendance("9312/")); // lack of numbers
        assertFalse(Student.isValidAttendance("/12")); // lack of numbers
        assertFalse(Student.isValidAttendance("13/12")); // first number is bigger than the second
        assertFalse(Student.isValidAttendance("101/120")); // numbers are more than 100

        // valid attendance
        assertTrue(Student.isValidAttendance("1/2"));
        assertTrue(Student.isValidAttendance("0/100"));
        assertTrue(Student.isValidAttendance("100/100"));
    }

    @Test
    public void isValidGrade() {
        // null grade
        assertThrows(NullPointerException.class, () -> Assignment.isValidInputGrade(null));

        // invalid grade
        assertFalse(Assignment.isValidInputGrade("")); // empty string
        assertFalse(Assignment.isValidInputGrade(" ")); // spaces only
        assertFalse(Assignment.isValidInputGrade("123")); // less than 3 numbers
        assertFalse(Assignment.isValidWeightage("abc")); // non-numeric
        assertFalse(Assignment.isValidInputGrade("/ ")); // lack of numbers
        assertFalse(Assignment.isValidWeightage("9312/")); // lack of numbers
        assertFalse(Assignment.isValidInputGrade("/12")); // lack of numbers
        assertFalse(Assignment.isValidWeightage("13/12")); // first number is bigger than the second
        assertFalse(Assignment.isValidInputGrade("101/120")); // numbers are more than 100

        // valid grade
        assertTrue(Assignment.isValidInputGrade("1/2"));
        assertTrue(Assignment.isValidInputGrade("0/100"));
        assertTrue(Assignment.isValidInputGrade("100/100"));
    }

    @Test
    public void isValidAssignments() {
        // null assignments
        assertThrows(NullPointerException.class, () -> Student.isValidAssignments(null));

        // invalid assignments
        assertFalse(Student.isValidAssignments("")); // empty string
        assertFalse(Student.isValidAssignments(" ")); // spaces only
        assertFalse(Student.isValidAssignments("ca1 w/hundred")); // weight is not in right format
        assertFalse(Student.isValidAssignments("ca1 w/50")); // weight does not add up
        assertFalse(Student.isValidAssignments("ca1w/100")); // lack of spacing before w/
        assertFalse(Student.isValidAssignments("ca1 w/50,ca2 w/50")); // lack of spacing after comma
        assertFalse(Student.isValidAssignments("ca1 w/50, ca2 w/60")); // weights do not add up to 100
        assertFalse(Student.isValidAssignments("ca1 w/50 ca2 w/50")); // lack of comma
        assertFalse(Student.isValidAssignments("ca1 w/20 ca1 w/80")); // duplicate assignments
        assertFalse(Student.isValidAssignments("ca1 w/9, ca2 w/9, ca3 w/9, ca4 w/9, ca5 w/9, ca6 w/9, ca7 w/9,"
                + " ca8 w/9, ca9 w/9, ca10 w/9, ca11 w/10")); // too many assignments

        // valid assignments
        assertTrue(Student.isValidAssignments("ca1 w/100"));
        assertTrue(Student.isValidAssignments("ca1 w/10, ca2 w/90"));
        assertTrue(Student.isValidAssignments("ca1 w/9, ca2 w/9, ca3 w/9, ca4 w/9, ca5 w/9, ca6 w/9, ca7 w/9,"
                + " ca8 w/9, ca9 w/9, ca10 w/19")); // 10 assignments
    }
}
