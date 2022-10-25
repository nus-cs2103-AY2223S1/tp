package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class TelegramTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Telegram(null));
    }

    @Test
    public void constructor_invalidTelegram_throwsIllegalArgumentException() {
        String invalidTelegram = "";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidTelegram));
    }

    @Test
    public void isValidTelegram() {
        // null address
        assertThrows(NullPointerException.class, () -> Telegram.isValidTele(null));

        // invalid addresses
        assertFalse(Telegram.isValidTele("")); // empty string
        assertFalse(Telegram.isValidTele(" ")); // spaces only
        assertFalse(Telegram.isValidTele("ben wong")); // more than one word

        // valid github names
        assertTrue(Telegram.isValidTele("benwong"));
        assertTrue(Telegram.isValidTele("ben-wong")); // special character inside
        assertTrue(Telegram.isValidTele("b")); // one character

    }
}
