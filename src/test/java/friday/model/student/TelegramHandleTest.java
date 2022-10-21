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
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = ".";
        assertThrows(IllegalArgumentException.class, () -> new TelegramHandle(invalidPhone));
    }

    @Test
    public void isValidTelegramHandle() {
        // null Telegram handle
        assertThrows(NullPointerException.class, () -> TelegramHandle.isValidTelegramHandle(null));

        // invalid Telegram handles
        assertFalse(TelegramHandle.isValidTelegramHandle("")); // empty handle is not allowed
        assertFalse(TelegramHandle.isValidTelegramHandle("john+doe")); // symbols are not allowed

        // valid Telegram handles
        assertTrue(TelegramHandle.isValidTelegramHandle("john123")); // letters and numbers
        assertTrue(TelegramHandle.isValidTelegramHandle("john_doe")); // with an underscore
        assertTrue(TelegramHandle.isValidTelegramHandle("John_Doe")); // capital letters are allowed
    }

    @Test
    public void isEmpty() {
        assertFalse(new TelegramHandle("empty").isEmpty()); // not the empty instance

        assertTrue(TelegramHandle.EMPTY_TELEGRAMHANDLE.isEmpty()); // the empty instance
    }
}
