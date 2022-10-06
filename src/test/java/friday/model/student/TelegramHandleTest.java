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
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new TelegramHandle(invalidPhone));
    }

    @Test
    public void isValidTelegramHandle() {
        // null phone number
        assertThrows(NullPointerException.class, () -> TelegramHandle.isValidTelegramHandle(null));

        // invalid phone numbers
        assertFalse(TelegramHandle.isValidTelegramHandle("john+doe")); // symbols are not allowed

        // valid phone numbers
        assertTrue(TelegramHandle.isValidTelegramHandle("911"));
        assertTrue(TelegramHandle.isValidTelegramHandle("john123")); // letters and numbers
        assertTrue(TelegramHandle.isValidTelegramHandle("john_doe")); // with an underscore
    }
}
