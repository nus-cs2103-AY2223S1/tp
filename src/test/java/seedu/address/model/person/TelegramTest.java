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
    public void constructor_invalidTelegram_throwsIllegalArgumentException() {
        String invalidTelegram = "";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidTelegram));
    }

    @Test
    public void isValidTelegram() {
        // Boundary Value Analysis and Equivalence Partitioning used in this test.
        // A valid telegram handle consists of lowercase letters, number and underscores and
        // must have at least 5 characters. It also starts with an @. Thus, the tests for invalid
        // parts consisted of handles with less than 5 characters, non-alphanumeric and uppercase handles.
        // The tests for valid handles consisted of handles with exactly 5 characters,
        // more than 5 characters and handles with underscores.

        // null telegram
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));

        // blank telegram
        assertFalse(Telegram.isValidTelegram("")); // empty string

        // missing parts
        assertFalse(Telegram.isValidTelegram("test")); // missing @
        assertFalse(Telegram.isValidTelegram("@")); // missing handle

        // invalid parts
        assertFalse(Telegram.isValidTelegram("@test")); // handle has less than 5 characters
        assertFalse(Telegram.isValidTelegram("@@")); // Non-alphanumeric
        assertFalse(Telegram.isValidTelegram("@TEST")); // Uppercase in handle

        // valid telegram
        assertTrue(Telegram.isValidTelegram("@test1")); // Valid handle with 5 characters
        assertTrue(Telegram.isValidTelegram("@bobmcghee")); // Valid handle with more than 5 characters
        assertTrue(Telegram.isValidTelegram("@bob_mc_ghee")); // Valid handle with underscores
    }
}
