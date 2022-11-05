package friday.model.student;

import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TelegramHandleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TelegramHandle(null));
    }

    @Test
    public void constructor_invalidTelegramHandle_throwsIllegalArgumentException() {
        String invalidTelegramHandle = ".";
        assertThrows(IllegalArgumentException.class, () -> new TelegramHandle(invalidTelegramHandle));
    }

    @Test
    public void isValidTelegramHandle() {
        // null Telegram handle
        assertThrows(NullPointerException.class, () -> TelegramHandle.isValidTelegramHandle(null));

        // invalid Telegram handles
        assertFalse(TelegramHandle.isValidTelegramHandle("")); // empty handle is not allowed
        assertFalse(TelegramHandle.isValidTelegramHandle("john+doe")); // "+" symbols are not allowed
        assertFalse(TelegramHandle.isValidTelegramHandle("john")); // must be 5 characters or more

        // valid Telegram handles
        assertTrue(TelegramHandle.isValidTelegramHandle("john123")); // letters and numbers
        assertTrue(TelegramHandle.isValidTelegramHandle("john1")); // 5 characters length
        assertTrue(TelegramHandle.isValidTelegramHandle("john_doe")); // with an underscore
        assertTrue(TelegramHandle.isValidTelegramHandle("John_Doe")); // capital letters are allowed
    }

    @Test
    public void isEmpty() {
        assertFalse(new TelegramHandle("empty").isEmpty()); // not the empty instance

        assertTrue(TelegramHandle.EMPTY_TELEGRAMHANDLE.isEmpty()); // the empty instance
    }

    @Test
    public void compareTo() {
        // 89 comes after 80
        assertTrue(new TelegramHandle("89jimmy").compareTo(new TelegramHandle("80jimmy")) > 0);
        // Longer handles are larger
        assertTrue(new Name("Tommyyyy").compareTo(new Name("Tommy")) > 0);
        // All digits come before all alphabets
        assertTrue(new TelegramHandle("90000").compareTo(new TelegramHandle("aether")) < 0);
        // Comparison is case-insensitive
        assertTrue(new Name("Candice123").compareTo(new Name("bandice123")) > 0);
    }
}
