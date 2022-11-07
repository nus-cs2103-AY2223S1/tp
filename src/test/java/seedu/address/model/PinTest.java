package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PinTest {

    private Pin testPinOne = new Pin(true);
    private Pin testPinTwo = new Pin(false);
    private Pin testPinThree = new Pin(true);
    private Pin testPinFour = new Pin(false);

    @Test
    public void isValidPin_error() {
        assertThrows(NullPointerException.class, () -> Pin.isValidPin(null));
    }

    @Test
    public void isValidPin_success() {
        assertTrue(Pin.isValidPin("true"));
        assertTrue(Pin.isValidPin("FALSE"));
        assertTrue(Pin.isValidPin("tRuE"));
    }

    @Test
    public void isValidPin_failure() {
        assertFalse(Pin.isValidPin("falsetrue"));
        assertFalse(Pin.isValidPin(""));
        assertFalse(Pin.isValidPin("abcdefg"));
        assertFalse(Pin.isValidPin("123"));
    }

    @Test
    public void testEquals_success() {
        assertTrue(testPinOne.equals(testPinThree));
        assertTrue(testPinOne.equals(testPinOne));
    }

    @Test
    public void testEquals_failure() {
        assertFalse(testPinOne.equals(testPinTwo));
        assertFalse(testPinOne.equals(""));
        assertFalse(testPinOne.equals(null));
    }

    @Test
    public void isPinned_success() {
        assertFalse(testPinFour.isPinned());
        testPinFour.togglePinned();
        assertTrue(testPinFour.isPinned());
    }
}
