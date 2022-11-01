package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class InputHistoryTest {

    @Test
    void get_historyEmpty_returnsBlank() {
        InputHistory test = new InputHistory();
        assertEquals("", test.get());
    }

    @Test
    void get_historyExists_returnsBlank() {
        InputHistory test = new InputHistory();
        test.add("test 0");
        test.add("test 1");
        test.add("test 2");
        assertEquals("", test.get());
    }

    @Test
    void up_emptyHistory_success() {
        InputHistory test = new InputHistory();
        assertEquals("", test.get());
        test.up();
        assertEquals("", test.get());
    }

    @Test
    void up_smallHistory_success() {
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
    void up_largeHistory_success() {
        InputHistory test = new InputHistory();
        String str = "test ";
        for (int i = 0; i < 50; i++) {
            test.add(str + i);
        }
        assertEquals("", test.get());

        for (int i = 49; i >= 0; i--) {
            test.up();
            assertEquals(str + i, test.get());
        }
    }

    @Test
    void down_emptyHistory_success() {
        InputHistory test = new InputHistory();
        assertEquals("", test.get());
        test.down();
        assertEquals("", test.get());
    }

    @Test
    void down_smallHistory_success() {
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

    @Test
    void down_largeHistory_success() {
        InputHistory test = new InputHistory();
        String str = "test ";
        for (int i = 0; i < 50; i++) {
            test.add(str + i);
        }

        for (int i = 0; i < 50; i++) {
            test.up();
        }

        for (int i = 0; i < 50; i++) {
            assertEquals(str + i, test.get());
            test.down();
        }
    }
}
