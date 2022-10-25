package seedu.address.model.person.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.testutil.ContactUtil;

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
    public void equals() {
        Telegram telegram1 = new Telegram("@test_hello");
        Telegram telegram2 = new Telegram("@test_hello");

        assertTrue(telegram1.equals(telegram1));
        assertTrue(telegram1.equals(telegram2));
    }

    @Test
    public void equals_invalidInstance_returnsFalse() {
        Telegram telegram = new Telegram("@test_hello");
        Name name = new Name("hello");
        assertFalse(telegram.equals(name));
    }

    @Test
    public void isValidTelegram() {
        // null address
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));

        // invalid addresses
        assertFalse(Telegram.isValidTelegram("")); // empty string
        assertFalse(Telegram.isValidTelegram(" ")); // spaces only
        assertFalse(Telegram.isValidTelegram("rachel")); // no @
        assertFalse(Telegram.isValidTelegram("@rach")); // too short
        assertFalse(Telegram.isValidTelegram("@rach!el")); // invalid characters

        // valid addresses
        assertTrue(Telegram.isValidTelegram("@rachel"));
        assertTrue(Telegram.isValidTelegram("@rach_el")); // with underscore
    }

    @Test
    public void isValidUrl() {
        Telegram validTelegram = new Telegram("@hello");
        assertTrue(ContactUtil.isValidUrl(validTelegram.getLink()));
    }
}
