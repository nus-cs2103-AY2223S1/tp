package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HomeworkTest {

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
}
