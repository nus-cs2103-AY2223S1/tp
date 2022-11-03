package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HomeworkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Homework(null));
    }

    @Test
    public void constructor_invalidHomework_throwsIllegalArgumentException() {
        String invalidHomework = "";
        assertThrows(IllegalArgumentException.class, () -> new Homework(invalidHomework));
    }

    @Test
    public void isValidHomework() {
        // null Homework number
        assertThrows(NullPointerException.class, () -> Homework.isValidHomework(null));

        // invalid Homework values
        assertFalse(Homework.isValidHomework("")); // empty string
        assertFalse(Homework.isValidHomework(" ")); // spaces only

        // valid Homework values
        assertTrue(Homework.isValidHomework("B")); // one letter
        assertTrue(Homework.isValidHomework("Biology")); // one word
        assertTrue(Homework.isValidHomework("Biology tutorial and readings")); // multiple words
    }

    @Test
    public void test_markAsDone() {
        Homework testHw = new Homework("test");
        testHw.markAsDone();
        assertEquals(testHw.getIsCompleted(), true);
    }

    @Test
    public void test_markAsUndone() {
        Homework testHw = new Homework("test");
        testHw.markAsUndone();
        assertEquals(testHw.getIsCompleted(), false);
    }

    @Test
    public void test_toString() {
        Homework testHw = new Homework("test");
        assertEquals(testHw.toString(), "test [ ]");
        testHw.markAsDone();
        assertEquals(testHw.toString(), "test [✓]");
        testHw.markAsUndone();
        assertEquals(testHw.toString(), "test [ ]");
    }

    @Test
    public void test_setIsCompleted() {
        Homework testHw = new Homework("test");
        testHw.setIsCompleted(true);
        assertTrue(testHw.getIsCompleted());
        testHw.setIsCompleted(false);
        assertFalse(testHw.getIsCompleted());
    }
}
