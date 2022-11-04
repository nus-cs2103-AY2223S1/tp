package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TelegramHandle(null));
    }

    @Test
    public void isValidTelegram() {
        assertThrows(NullPointerException.class, () -> TelegramHandle.isValidTelegram(null)); // null telegram name

        assertFalse(TelegramHandle.isValidTelegram("o.k")); // symbols
        assertFalse(TelegramHandle.isValidTelegram("o!k")); // symbols
        assertFalse(TelegramHandle.isValidTelegram("?")); // symbols
        assertFalse(TelegramHandle.isValidTelegram(":)")); // symbols

        // valid telegram
        assertTrue(TelegramHandle.isValidTelegram("ABCD"));
        assertTrue(TelegramHandle.isValidTelegram("1234"));
        assertTrue(TelegramHandle.isValidTelegram("ABCD1234"));
        assertTrue(TelegramHandle.isValidTelegram("ABCD_1234"));
    }
}
