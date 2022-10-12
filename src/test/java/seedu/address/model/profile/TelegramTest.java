package seedu.address.model.profile;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Telegram(null));
    }

    @Test
    public void constructor_invalidTelegramUsername_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Telegram("abc")); // less than 5 chars
        assertThrows(IllegalArgumentException.class, () -> new Telegram("@_hello")); // starts with underscore
        assertThrows(IllegalArgumentException.class, () -> new Telegram("hello")); // does not start with '@'
        assertThrows(IllegalArgumentException.class, () -> new Telegram("@99999")); // numbers only
        assertThrows(IllegalArgumentException.class, () -> new Telegram("@user_")); // ends with underscore
        assertThrows(IllegalArgumentException.class, () -> new Telegram("@a___b")); // consecutive underscores
        assertThrows(IllegalArgumentException.class, () -> new Telegram("@user.1")); // invalid character '.'
    }

    @Test
    public void isValidTelegramUsername() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));
    }
}
