package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

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
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Telegram.isValidATelegram(null));

        // invalid addresses
        assertFalse(Telegram.isValidATelegram("")); // empty string
        assertFalse(Telegram.isValidATelegram(" ")); // spaces only

        // valid addresses
        assertTrue(Telegram.isValidATelegram("fnnn"));
        assertTrue(Telegram.isValidATelegram("-")); // one character
        assertTrue(Telegram.isValidATelegram("jammy12")); // long address
    }
}
