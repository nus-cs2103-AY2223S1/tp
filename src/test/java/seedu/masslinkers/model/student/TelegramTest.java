package seedu.masslinkers.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.masslinkers.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramTest {

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
        // null telegram
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));

        // invalid telegram
        assertFalse(Telegram.isValidTelegram("")); // empty string
        assertFalse(Telegram.isValidTelegram(" ")); // spaces only
        assertFalse(Telegram.isValidTelegram("he")); // <5 chars
        assertFalse(Telegram.isValidTelegram("lo-pie")); // special chars that are not underscores
        assertFalse(Telegram.isValidTelegram("lopi&e")); // special chars that are not underscores

        // valid telegram
        assertTrue(Telegram.isValidTelegram("fnnnn")); // exactly 5 chars
        assertTrue(Telegram.isValidTelegram("jammy12")); // words and numbers
        assertTrue(Telegram.isValidTelegram("JAMMdfg")); // caps
        assertTrue(Telegram.isValidTelegram("12345")); // numbers
    }
}
