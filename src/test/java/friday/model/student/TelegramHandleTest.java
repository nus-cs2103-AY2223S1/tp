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
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> TelegramHandle.isValidTelegramHandle(null));

        // invalid phone numbers
        assertFalse(TelegramHandle.isValidTelegramHandle("")); // empty string
        assertFalse(TelegramHandle.isValidTelegramHandle(" ")); // spaces only
        assertFalse(TelegramHandle.isValidTelegramHandle("91")); // less than 3 numbers
        assertFalse(TelegramHandle.isValidTelegramHandle("phone")); // non-numeric
        assertFalse(TelegramHandle.isValidTelegramHandle("9011p041")); // alphabets within digits
        assertFalse(TelegramHandle.isValidTelegramHandle("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(TelegramHandle.isValidTelegramHandle("911")); // exactly 3 numbers
        assertTrue(TelegramHandle.isValidTelegramHandle("93121534"));
        assertTrue(TelegramHandle.isValidTelegramHandle("124293842033123")); // long phone numbers
    }
}
