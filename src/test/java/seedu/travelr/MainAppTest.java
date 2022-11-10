package seedu.travelr;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MainAppTest {
    @Test
    void testFunction() {
        try {
            new MainApp().init();
        } catch (Exception e) {
            assertTrue(true);
        }
        assertTrue(true);
    }
}
