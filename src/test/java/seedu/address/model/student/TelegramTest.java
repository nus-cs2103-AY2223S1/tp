package seedu.address.model.student;

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
        // null name
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));

        // invalid name
        assertFalse(Telegram.isValidTelegram("")); // empty string
        assertFalse(Telegram.isValidTelegram(" ")); // spaces only
        assertFalse(Telegram.isValidTelegram("^")); // only non-alphanumeric characters
        assertFalse(Telegram.isValidTelegram("peter*")); // contains non-alphanumeric characters
        assertFalse(Telegram.isValidTelegram("peter")); // no @ before alphanumeric characters
        assertFalse(Telegram.isValidTelegram("@peter park")); // spaces in between telegram handle

        // valid name
        assertTrue(Telegram.isValidTelegram("@peterjack")); // alphabets only
        assertTrue(Telegram.isValidTelegram("@peter_jack")); //contains underscore
        assertTrue(Telegram.isValidTelegram("@12345")); // numbers only
        assertTrue(Telegram.isValidTelegram("@peterthe2nd")); // alphanumeric characters
        assertTrue(Telegram.isValidTelegram("@CapitalTan")); // with capital letters
        assertTrue(Telegram.isValidTelegram("@DavidRogerJacksonRayJr2nd")); // long names
    }
}
