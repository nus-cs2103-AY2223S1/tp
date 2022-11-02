package jarvis.model.student;

import static jarvis.logic.commands.CommandTestUtil.VALID_MATRIC_NUM_AMY;
import static jarvis.logic.commands.CommandTestUtil.VALID_MATRIC_NUM_BOB;
import static jarvis.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jarvis.model.MatricNum;

public class StudentMatricNumTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MatricNum(null));
    }

    @Test
    public void constructor_invalidMatric_throwsIllegalArgumentException() {
        String invalidMatric = "";
        assertThrows(IllegalArgumentException.class, () -> new MatricNum(invalidMatric));
    }

    @Test
    public void isValidMatricNum() {
        // null matric number
        assertThrows(NullPointerException.class, () -> MatricNum.isValidMatricNum(null));

        // invalid matric number
        assertFalse(MatricNum.isValidMatricNum("")); // empty string
        assertFalse(MatricNum.isValidMatricNum(" ")); // spaces only
        assertFalse(MatricNum.isValidMatricNum("B1234567U")); // not start with A
        assertFalse(MatricNum.isValidMatricNum("a1234567U")); // starts with small letter A
        assertFalse(MatricNum.isValidMatricNum("A1234567u")); // ends with small letter
        assertFalse(MatricNum.isValidMatricNum("A123456U")); // 6 digits

        // valid matric number
        assertTrue(MatricNum.isValidMatricNum("A1234567U"));
        assertTrue(MatricNum.isValidMatricNum("A1234567A"));
    }

    @Test
    public void equals() {
        MatricNum matricNum1 = new MatricNum(VALID_MATRIC_NUM_AMY);
        MatricNum matricNum2 = new MatricNum(VALID_MATRIC_NUM_BOB);

        //same values -> returns true
        MatricNum matricNum1Copy = new MatricNum(VALID_MATRIC_NUM_AMY);
        assertTrue(matricNum1.equals(matricNum1Copy));

        //same object -> returns true
        assertTrue(matricNum1.equals(matricNum1));

        // null -> returns false
        assertFalse(matricNum1.equals(null));

        // different type -> returns false
        assertFalse(matricNum1.equals(5));

        //different values -> returns false
        assertFalse(matricNum1.equals(matricNum2));
    }
}
