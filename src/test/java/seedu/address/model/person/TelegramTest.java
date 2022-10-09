package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Telegram(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidAddress));
    }

    @Test
    public void isValidTelegram() {
        // null address
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));

        // invalid addresses
        assertFalse(Telegram.isValidTelegram("")); // empty string
        assertFalse(Telegram.isValidTelegram(" ")); // spaces only

        // valid addresses
        assertTrue(Telegram.isValidTelegram("fnnn"));
        assertTrue(Telegram.isValidTelegram("-")); // one character
        assertTrue(Telegram.isValidTelegram("jammy12")); // long address
    }
}
