package seedu.classify.model.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.classify.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ExamTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Exam(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidExam = "";
        assertThrows(IllegalArgumentException.class, () -> new Exam(invalidExam));
    }

    @Test
    public void isValidFormat() {
        // null test name
        assertThrows(NullPointerException.class, () -> Exam.isValidFormat(null));

        // invalid test format
        assertFalse(Exam.isValidFormat("CA 1 60"));
        assertFalse(Exam.isValidFormat("CA160"));

        // valid test format
        assertTrue(Exam.isValidFormat("CA1 60"));
        assertTrue(Exam.isValidFormat("CA1   60"));

    }

    @Test
    public void isValidName() {
        // null class name
        assertThrows(NullPointerException.class, () -> Exam.isValidName(null));

        // blank test name
        assertFalse(Exam.isValidName("")); // empty string
        assertFalse(Exam.isValidName(" ")); // blank spaces

        // invalid exam names
        assertFalse(Exam.isValidName("Class Test 1"));
        assertFalse(Exam.isValidName("ca1"));

        // valid exam names
        assertTrue(Exam.isValidName("CA1"));
        assertTrue(Exam.isValidName("SA1"));
    }

    @Test
    public void isValidScore() {
        // null score
        assertThrows(NullPointerException.class, () -> Exam.isValidScore(null));

        // score is not numerical
        assertFalse(Exam.isValidScore("TEST"));
        assertFalse(Exam.isValidScore("T9"));

        // score is out of range
        assertFalse(Exam.isValidScore("-1"));
        assertFalse(Exam.isValidScore("101"));

        // valid score
        assertTrue(Exam.isValidScore("0"));
        assertTrue(Exam.isValidScore("1"));
        assertTrue(Exam.isValidScore("99"));
        assertTrue(Exam.isValidScore("100"));
    }

    @Test
    public void getScore_success() {
        Exam test = new Exam("CA1 60");
        assertEquals(test.getScore(), 60);
    }

    @Test
    public void getScore_fail() {
        Exam test = new Exam("CA1 60");
        assertNotEquals(test.getScore(), 61);
    }

    @Test
    public void equals() {
        Exam test = new Exam("CA1 60");
        Exam sameTest = new Exam("CA1 60");

        // same values -> returns true
        assertTrue(test.equals(sameTest));

        // same object -> returns true
        assertTrue(test.equals(test));

        // different types -> returns false
        assertFalse(test.equals("CA1 60"));

        // different names -> return false
        assertFalse(test.equals(new Exam("SA1 60")));
    }

    @Test
    public void getStringValue() {
        Exam test = new Exam("CA1 60");
        assertEquals(test.toString(), "CA1 60");
    }

}

