package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class InputHistoryTest {

    @Test
    void get() {
        InputHistory test = new InputHistory();
        test.add("test 0");
        test.add("test 1");
        test.add("test 2");
        assertEquals("", test.get());
    }

    @Test
    void up() {
        InputHistory test = new InputHistory();
        test.add("test 0");
        test.add("test 1");
        test.add("test 2");
        assertEquals("", test.get());
        test.up();
        assertEquals("test 2", test.get());
        test.up();
        assertEquals("test 1", test.get());
        test.up();
        assertEquals("test 0", test.get());
    }

    @Test
    void down() {
        InputHistory test = new InputHistory();
        test.add("test 0");
        test.add("test 1");
        test.add("test 2");
        test.up();
        test.up();
        test.up();
        assertEquals("test 0", test.get());
        test.down();
        assertEquals("test 1", test.get());
        test.down();
        assertEquals("test 2", test.get());
    }
}
